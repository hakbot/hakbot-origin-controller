
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
 *         &lt;element name="GetFreeSpace2Result" type="{http://ntobjectives.com/webservices/}Result"/>
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
    "getFreeSpace2Result"
})
@XmlRootElement(name = "GetFreeSpace2Response")
public class GetFreeSpace2Response {

    @XmlElement(name = "GetFreeSpace2Result", required = true)
    protected Result getFreeSpace2Result;

    /**
     * Gets the value of the getFreeSpace2Result property.
     * 
     * @return
     *     possible object is
     *     {@link Result }
     *     
     */
    public Result getGetFreeSpace2Result() {
        return getFreeSpace2Result;
    }

    /**
     * Sets the value of the getFreeSpace2Result property.
     * 
     * @param value
     *     allowed object is
     *     {@link Result }
     *     
     */
    public void setGetFreeSpace2Result(Result value) {
        this.getFreeSpace2Result = value;
    }

}
