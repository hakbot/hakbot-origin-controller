
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
 *         &lt;element name="SetAdministrativeOfflineResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "setAdministrativeOfflineResult"
})
@XmlRootElement(name = "SetAdministrativeOfflineResponse")
public class SetAdministrativeOfflineResponse {

    @XmlElement(name = "SetAdministrativeOfflineResult")
    protected String setAdministrativeOfflineResult;

    /**
     * Gets the value of the setAdministrativeOfflineResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSetAdministrativeOfflineResult() {
        return setAdministrativeOfflineResult;
    }

    /**
     * Sets the value of the setAdministrativeOfflineResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSetAdministrativeOfflineResult(String value) {
        this.setAdministrativeOfflineResult = value;
    }

}
