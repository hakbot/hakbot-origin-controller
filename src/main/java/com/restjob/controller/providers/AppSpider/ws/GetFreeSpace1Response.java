
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
 *         &lt;element name="GetFreeSpace1Result" type="{http://ntobjectives.com/webservices/}Result"/>
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
    "getFreeSpace1Result"
})
@XmlRootElement(name = "GetFreeSpace1Response")
public class GetFreeSpace1Response {

    @XmlElement(name = "GetFreeSpace1Result", required = true)
    protected Result getFreeSpace1Result;

    /**
     * Gets the value of the getFreeSpace1Result property.
     * 
     * @return
     *     possible object is
     *     {@link Result }
     *     
     */
    public Result getGetFreeSpace1Result() {
        return getFreeSpace1Result;
    }

    /**
     * Sets the value of the getFreeSpace1Result property.
     * 
     * @param value
     *     allowed object is
     *     {@link Result }
     *     
     */
    public void setGetFreeSpace1Result(Result value) {
        this.getFreeSpace1Result = value;
    }

}
