
package io.hakbot.providers.AppSpider.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SCANSTATUSEmbeded complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SCANSTATUSEmbeded">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="m_iScanProgress" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="m_szTimeRemaining" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="m_szTimeElapsed" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="szScanName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="szReserved1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="szReserved2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="szReserved3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SCANSTATUSEmbeded", propOrder = {
    "miScanProgress",
    "mSzTimeRemaining",
    "mSzTimeElapsed",
    "szScanName",
    "szReserved1",
    "szReserved2",
    "szReserved3"
})
public class SCANSTATUSEmbeded {

    @XmlElement(name = "m_iScanProgress")
    protected int miScanProgress;
    @XmlElement(name = "m_szTimeRemaining")
    protected String mSzTimeRemaining;
    @XmlElement(name = "m_szTimeElapsed")
    protected String mSzTimeElapsed;
    protected String szScanName;
    protected String szReserved1;
    protected String szReserved2;
    protected String szReserved3;

    /**
     * Gets the value of the miScanProgress property.
     * 
     */
    public int getMIScanProgress() {
        return miScanProgress;
    }

    /**
     * Sets the value of the miScanProgress property.
     * 
     */
    public void setMIScanProgress(int value) {
        this.miScanProgress = value;
    }

    /**
     * Gets the value of the mSzTimeRemaining property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMSzTimeRemaining() {
        return mSzTimeRemaining;
    }

    /**
     * Sets the value of the mSzTimeRemaining property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMSzTimeRemaining(String value) {
        this.mSzTimeRemaining = value;
    }

    /**
     * Gets the value of the mSzTimeElapsed property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMSzTimeElapsed() {
        return mSzTimeElapsed;
    }

    /**
     * Sets the value of the mSzTimeElapsed property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMSzTimeElapsed(String value) {
        this.mSzTimeElapsed = value;
    }

    /**
     * Gets the value of the szScanName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSzScanName() {
        return szScanName;
    }

    /**
     * Sets the value of the szScanName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSzScanName(String value) {
        this.szScanName = value;
    }

    /**
     * Gets the value of the szReserved1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSzReserved1() {
        return szReserved1;
    }

    /**
     * Sets the value of the szReserved1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSzReserved1(String value) {
        this.szReserved1 = value;
    }

    /**
     * Gets the value of the szReserved2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSzReserved2() {
        return szReserved2;
    }

    /**
     * Sets the value of the szReserved2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSzReserved2(String value) {
        this.szReserved2 = value;
    }

    /**
     * Gets the value of the szReserved3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSzReserved3() {
        return szReserved3;
    }

    /**
     * Sets the value of the szReserved3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSzReserved3(String value) {
        this.szReserved3 = value;
    }

}
