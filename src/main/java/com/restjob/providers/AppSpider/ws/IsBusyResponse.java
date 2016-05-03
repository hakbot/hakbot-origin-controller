
package com.restjob.providers.appspider.ws;

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
 *         &lt;element name="IsBusyResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "isBusyResult"
})
@XmlRootElement(name = "IsBusyResponse")
public class IsBusyResponse {

    @XmlElement(name = "IsBusyResult")
    protected boolean isBusyResult;

    /**
     * Gets the value of the isBusyResult property.
     * 
     */
    public boolean isIsBusyResult() {
        return isBusyResult;
    }

    /**
     * Sets the value of the isBusyResult property.
     * 
     */
    public void setIsBusyResult(boolean value) {
        this.isBusyResult = value;
    }

}
