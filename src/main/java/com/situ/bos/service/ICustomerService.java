package com.situ.bos.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import com.situ.bos.pojo.Customer;

/**
 * This class was generated by Apache CXF 2.4.2
 * 2017-12-06T01:23:56.764+08:00
 * Generated source version: 2.4.2
 * 
 */
@WebService(targetNamespace = "http://service.bos.situ.com/", name = "ICustomerService")
@XmlSeeAlso({})
public interface ICustomerService {

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "findAll", targetNamespace = "http://service.bos.situ.com/", className = "com.situ.bos.service.FindAll")
    @WebMethod
    @ResponseWrapper(localName = "findAllResponse", targetNamespace = "http://service.bos.situ.com/", className = "com.situ.bos.service.FindAllResponse")
    public java.util.List<Customer> findAll();

    @RequestWrapper(localName = "assigncustomerstodecidedzone", targetNamespace = "http://service.bos.situ.com/", className = "com.situ.bos.service.Assigncustomerstodecidedzone")
    @WebMethod
    @ResponseWrapper(localName = "assigncustomerstodecidedzoneResponse", targetNamespace = "http://service.bos.situ.com/", className = "com.situ.bos.service.AssigncustomerstodecidedzoneResponse")
    public void assigncustomerstodecidedzone(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.util.List<java.lang.Integer> arg1
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "findListNotAssociation", targetNamespace = "http://service.bos.situ.com/", className = "com.situ.bos.service.FindListNotAssociation")
    @WebMethod
    @ResponseWrapper(localName = "findListNotAssociationResponse", targetNamespace = "http://service.bos.situ.com/", className = "com.situ.bos.service.FindListNotAssociationResponse")
    public java.util.List<Customer> findListNotAssociation();

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "findListHasAssociation", targetNamespace = "http://service.bos.situ.com/", className = "com.situ.bos.service.FindListHasAssociation")
    @WebMethod
    @ResponseWrapper(localName = "findListHasAssociationResponse", targetNamespace = "http://service.bos.situ.com/", className = "com.situ.bos.service.FindListHasAssociationResponse")
    public java.util.List<Customer> findListHasAssociation(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );
}
