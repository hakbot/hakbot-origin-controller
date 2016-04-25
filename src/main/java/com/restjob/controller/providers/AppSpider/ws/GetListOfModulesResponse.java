
package com.restjob.controller.providers.AppSpider.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GetListOfModulesResult" type="{http://ntobjectives.com/webservices/}Result"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "getListOfModulesResult"
})
@XmlRootElement(name = "GetListOfModulesResponse")
public class GetListOfModulesResponse {

    @XmlElement(name = "GetListOfModulesResult", required = true)
    protected Result getListOfModulesResult;

    /**
     * Gets the value of the getListOfModulesResult property.
     * 
     * @return
     *     possible object is
     *     {@link Result }
     *     
     */
    public Result getGetListOfModulesResult() {
        return getListOfModulesResult;
    }

    /**
     * Sets the value of the getListOfModulesResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link Result }
     *     
     */
    public void setGetListOfModulesResult(Result value) {
        this.getListOfModulesResult = value;
    }

}
