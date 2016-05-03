
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
 *         &lt;element name="GetSysInfoResult" type="{http://ntobjectives.com/webservices/}Result"/>
 *         &lt;element name="data" type="{http://ntobjectives.com/webservices/}SYSTEMINFO"/>
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
    "getSysInfoResult",
    "data"
})
@XmlRootElement(name = "GetSysInfoResponse")
public class GetSysInfoResponse {

    @XmlElement(name = "GetSysInfoResult", required = true)
    protected Result getSysInfoResult;
    @XmlElement(required = true)
    protected SYSTEMINFO data;

    /**
     * Gets the value of the getSysInfoResult property.
     * 
     * @return
     *     possible object is
     *     {@link Result }
     *     
     */
    public Result getGetSysInfoResult() {
        return getSysInfoResult;
    }

    /**
     * Sets the value of the getSysInfoResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link Result }
     *     
     */
    public void setGetSysInfoResult(Result value) {
        this.getSysInfoResult = value;
    }

    /**
     * Gets the value of the data property.
     * 
     * @return
     *     possible object is
     *     {@link SYSTEMINFO }
     *     
     */
    public SYSTEMINFO getData() {
        return data;
    }

    /**
     * Sets the value of the data property.
     * 
     * @param value
     *     allowed object is
     *     {@link SYSTEMINFO }
     *     
     */
    public void setData(SYSTEMINFO value) {
        this.data = value;
    }

}
