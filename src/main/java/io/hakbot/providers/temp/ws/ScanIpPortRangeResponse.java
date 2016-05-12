
package io.hakbot.providers.temp.ws;

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
 *         &lt;element name="ScanIpPortRangeResult" type="{http://ntobjectives.com/webservices/}Result"/>
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
    "scanIpPortRangeResult"
})
@XmlRootElement(name = "ScanIpPortRangeResponse")
public class ScanIpPortRangeResponse {

    @XmlElement(name = "ScanIpPortRangeResult", required = true)
    protected Result scanIpPortRangeResult;

    /**
     * Gets the value of the scanIpPortRangeResult property.
     * 
     * @return
     *     possible object is
     *     {@link Result }
     *     
     */
    public Result getScanIpPortRangeResult() {
        return scanIpPortRangeResult;
    }

    /**
     * Sets the value of the scanIpPortRangeResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link Result }
     *     
     */
    public void setScanIpPortRangeResult(Result value) {
        this.scanIpPortRangeResult = value;
    }

}
