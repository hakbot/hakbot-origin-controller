
package io.hakbot.providers.AppSpider.ws;

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
 *         &lt;element name="GetIpScanResultResult" type="{http://ntobjectives.com/webservices/}IPSCANRESULT" minOccurs="0"/>
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
    "getIpScanResultResult"
})
@XmlRootElement(name = "GetIpScanResultResponse")
public class GetIpScanResultResponse {

    @XmlElement(name = "GetIpScanResultResult")
    protected IPSCANRESULT getIpScanResultResult;

    /**
     * Gets the value of the getIpScanResultResult property.
     * 
     * @return
     *     possible object is
     *     {@link IPSCANRESULT }
     *     
     */
    public IPSCANRESULT getGetIpScanResultResult() {
        return getIpScanResultResult;
    }

    /**
     * Sets the value of the getIpScanResultResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link IPSCANRESULT }
     *     
     */
    public void setGetIpScanResultResult(IPSCANRESULT value) {
        this.getIpScanResultResult = value;
    }

}
