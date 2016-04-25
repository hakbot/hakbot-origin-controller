
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
 *         &lt;element name="DecryptStringsResult" type="{http://ntobjectives.com/webservices/}ThreeStrings" minOccurs="0"/>
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
    "decryptStringsResult"
})
@XmlRootElement(name = "DecryptStringsResponse")
public class DecryptStringsResponse {

    @XmlElement(name = "DecryptStringsResult")
    protected ThreeStrings decryptStringsResult;

    /**
     * Gets the value of the decryptStringsResult property.
     * 
     * @return
     *     possible object is
     *     {@link ThreeStrings }
     *     
     */
    public ThreeStrings getDecryptStringsResult() {
        return decryptStringsResult;
    }

    /**
     * Sets the value of the decryptStringsResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ThreeStrings }
     *     
     */
    public void setDecryptStringsResult(ThreeStrings value) {
        this.decryptStringsResult = value;
    }

}
