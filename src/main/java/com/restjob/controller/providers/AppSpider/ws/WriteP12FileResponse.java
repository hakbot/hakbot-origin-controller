
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
 *         &lt;element name="WriteP12FileResult" type="{http://ntobjectives.com/webservices/}Result"/>
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
    "writeP12FileResult"
})
@XmlRootElement(name = "WriteP12FileResponse")
public class WriteP12FileResponse {

    @XmlElement(name = "WriteP12FileResult", required = true)
    protected Result writeP12FileResult;

    /**
     * Gets the value of the writeP12FileResult property.
     * 
     * @return
     *     possible object is
     *     {@link Result }
     *     
     */
    public Result getWriteP12FileResult() {
        return writeP12FileResult;
    }

    /**
     * Sets the value of the writeP12FileResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link Result }
     *     
     */
    public void setWriteP12FileResult(Result value) {
        this.writeP12FileResult = value;
    }

}
