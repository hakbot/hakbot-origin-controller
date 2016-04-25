
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
 *         &lt;element name="UpdateResultResult" type="{http://ntobjectives.com/webservices/}Result"/>
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
    "updateResultResult"
})
@XmlRootElement(name = "UpdateResultResponse")
public class UpdateResultResponse {

    @XmlElement(name = "UpdateResultResult", required = true)
    protected Result updateResultResult;

    /**
     * Gets the value of the updateResultResult property.
     * 
     * @return
     *     possible object is
     *     {@link Result }
     *     
     */
    public Result getUpdateResultResult() {
        return updateResultResult;
    }

    /**
     * Sets the value of the updateResultResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link Result }
     *     
     */
    public void setUpdateResultResult(Result value) {
        this.updateResultResult = value;
    }

}
