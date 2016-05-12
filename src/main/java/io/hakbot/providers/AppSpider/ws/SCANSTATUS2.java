
package io.hakbot.providers.AppSpider.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SCANSTATUS2 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SCANSTATUS2">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ntobjectives.com/webservices/}SCANSTATUS">
 *       &lt;sequence>
 *         &lt;element name="status2" type="{http://ntobjectives.com/webservices/}SCANSTATUSEmbeded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SCANSTATUS2", propOrder = {
    "status2"
})
public class SCANSTATUS2
    extends SCANSTATUS
{

    protected SCANSTATUSEmbeded status2;

    /**
     * Gets the value of the status2 property.
     * 
     * @return
     *     possible object is
     *     {@link SCANSTATUSEmbeded }
     *     
     */
    public SCANSTATUSEmbeded getStatus2() {
        return status2;
    }

    /**
     * Sets the value of the status2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link SCANSTATUSEmbeded }
     *     
     */
    public void setStatus2(SCANSTATUSEmbeded value) {
        this.status2 = value;
    }

}
