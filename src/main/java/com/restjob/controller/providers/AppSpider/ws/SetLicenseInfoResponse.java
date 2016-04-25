
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
 *         &lt;element name="SetLicenseInfoResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "setLicenseInfoResult"
})
@XmlRootElement(name = "SetLicenseInfoResponse")
public class SetLicenseInfoResponse {

    @XmlElement(name = "SetLicenseInfoResult")
    protected String setLicenseInfoResult;

    /**
     * Gets the value of the setLicenseInfoResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSetLicenseInfoResult() {
        return setLicenseInfoResult;
    }

    /**
     * Sets the value of the setLicenseInfoResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSetLicenseInfoResult(String value) {
        this.setLicenseInfoResult = value;
    }

}
