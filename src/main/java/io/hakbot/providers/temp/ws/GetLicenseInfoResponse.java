
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
 *         &lt;element name="GetLicenseInfoResult" type="{http://ntobjectives.com/webservices/}LICENSEINFO" minOccurs="0"/>
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
    "getLicenseInfoResult"
})
@XmlRootElement(name = "GetLicenseInfoResponse")
public class GetLicenseInfoResponse {

    @XmlElement(name = "GetLicenseInfoResult")
    protected LICENSEINFO getLicenseInfoResult;

    /**
     * Gets the value of the getLicenseInfoResult property.
     * 
     * @return
     *     possible object is
     *     {@link LICENSEINFO }
     *     
     */
    public LICENSEINFO getGetLicenseInfoResult() {
        return getLicenseInfoResult;
    }

    /**
     * Sets the value of the getLicenseInfoResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link LICENSEINFO }
     *     
     */
    public void setGetLicenseInfoResult(LICENSEINFO value) {
        this.getLicenseInfoResult = value;
    }

}
