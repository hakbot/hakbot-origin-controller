
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
 *         &lt;element name="GetStatus2Result" type="{http://ntobjectives.com/webservices/}SCANSTATUS2" minOccurs="0"/>
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
    "getStatus2Result"
})
@XmlRootElement(name = "GetStatus2Response")
public class GetStatus2Response {

    @XmlElement(name = "GetStatus2Result")
    protected SCANSTATUS2 getStatus2Result;

    /**
     * Gets the value of the getStatus2Result property.
     * 
     * @return
     *     possible object is
     *     {@link SCANSTATUS2 }
     *     
     */
    public SCANSTATUS2 getGetStatus2Result() {
        return getStatus2Result;
    }

    /**
     * Sets the value of the getStatus2Result property.
     * 
     * @param value
     *     allowed object is
     *     {@link SCANSTATUS2 }
     *     
     */
    public void setGetStatus2Result(SCANSTATUS2 value) {
        this.getStatus2Result = value;
    }

}
