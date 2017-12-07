package com.situ.bos.service.impl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.situ.bos.dao.IDecidedzoneDao;
import com.situ.bos.dao.INoticebillDao;
import com.situ.bos.dao.IWorkbillDao;
import com.situ.bos.pojo.Decidedzone;
import com.situ.bos.pojo.Noticebill;
import com.situ.bos.pojo.Staff;
import com.situ.bos.pojo.User;
import com.situ.bos.pojo.Workbill;
import com.situ.bos.service.ICustomerService;
import com.situ.bos.service.INoticebillService;
import com.situ.bos.util.BosUtils;
@Service
@Transactional
public class NoticebillServiceImpl implements INoticebillService {

	@Autowired
	private INoticebillDao noticebillDao;
	
	@Autowired
	private ICustomerService customerService;
	
	@Autowired
	private IDecidedzoneDao decidedzoneDao;
	
	@Autowired
	private IWorkbillDao workbillDao;
	/**
	 * 保存业务通知单，尝试自动分单
	 */
	@Override
	public void save(Noticebill model) {
		User user = BosUtils.getLoginUser();
		model.setUser(user);//设置当前登录用户
		noticebillDao.save(model);
		//获取客户的取件地址
		String pickaddress = model.getPickaddress();
		//远程调用crm服务，根据地址查找定区的id
		String decidedzoneId = customerService.findDecidedzoneIdByAddress(pickaddress);
		if (decidedzoneId != null) {
			//查询到id，可以进行自动分单
			Decidedzone decidedzone = decidedzoneDao.findById(decidedzoneId);
			Staff staff = decidedzone.getStaff();
			//设置为自动分单
			model.setOrdertype(Noticebill.ORDERTYPE_AUTO);
			//为取派员设置一个工单
			Workbill workbill = new Workbill();
			workbill.setAttachbilltimes(0);//追单次数
			workbill.setBuildtime(new Timestamp(System.currentTimeMillis()));//设置时间
			workbill.setNoticebill(model);//工单关联页面通知单
			workbill.setPickstate(Workbill.PICKSTATE_NO);//取件状态
			workbill.setRemark(model.getRemark());//设置备注信息
			workbill.setStaff(staff);//关联取派员
			workbill.setType(Workbill.TYPE_1);//工单类型
			workbillDao.save(workbill);
			
			//调用短信平台，发送短信
			
		} else {
			//，没有查询定区id的不能进行自动分单，需要进行人工分单
			model.setOrdertype(Noticebill.ORDERTYPE_MAN);
		}
		
	}

}
