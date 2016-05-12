
package io.hakbot.providers.AppSpider.ws;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SYSTEMINFO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SYSTEMINFO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CPU" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Cores" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *         &lt;element name="TotalRAM" type="{http://www.w3.org/2001/XMLSchema}unsignedLong"/>
 *         &lt;element name="FreeRAM" type="{http://www.w3.org/2001/XMLSchema}unsignedLong"/>
 *         &lt;element name="TotalSystemDiskSpace" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="FreeSystemDiskSpace" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="TotalDataDiskSpace" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="FreeDataDiskSpace" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SYSTEMINFO", propOrder = {
    "cpu",
    "cores",
    "totalRAM",
    "freeRAM",
    "totalSystemDiskSpace",
    "freeSystemDiskSpace",
    "totalDataDiskSpace",
    "freeDataDiskSpace"
})
public class SYSTEMINFO {

    @XmlElement(name = "CPU")
    protected String cpu;
    @XmlElement(name = "Cores")
    @XmlSchemaType(name = "unsignedInt")
    protected long cores;
    @XmlElement(name = "TotalRAM", required = true)
    @XmlSchemaType(name = "unsignedLong")
    protected BigInteger totalRAM;
    @XmlElement(name = "FreeRAM", required = true)
    @XmlSchemaType(name = "unsignedLong")
    protected BigInteger freeRAM;
    @XmlElement(name = "TotalSystemDiskSpace")
    protected long totalSystemDiskSpace;
    @XmlElement(name = "FreeSystemDiskSpace")
    protected long freeSystemDiskSpace;
    @XmlElement(name = "TotalDataDiskSpace")
    protected long totalDataDiskSpace;
    @XmlElement(name = "FreeDataDiskSpace")
    protected long freeDataDiskSpace;

    /**
     * Gets the value of the cpu property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCPU() {
        return cpu;
    }

    /**
     * Sets the value of the cpu property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCPU(String value) {
        this.cpu = value;
    }

    /**
     * Gets the value of the cores property.
     * 
     */
    public long getCores() {
        return cores;
    }

    /**
     * Sets the value of the cores property.
     * 
     */
    public void setCores(long value) {
        this.cores = value;
    }

    /**
     * Gets the value of the totalRAM property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTotalRAM() {
        return totalRAM;
    }

    /**
     * Sets the value of the totalRAM property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTotalRAM(BigInteger value) {
        this.totalRAM = value;
    }

    /**
     * Gets the value of the freeRAM property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getFreeRAM() {
        return freeRAM;
    }

    /**
     * Sets the value of the freeRAM property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setFreeRAM(BigInteger value) {
        this.freeRAM = value;
    }

    /**
     * Gets the value of the totalSystemDiskSpace property.
     * 
     */
    public long getTotalSystemDiskSpace() {
        return totalSystemDiskSpace;
    }

    /**
     * Sets the value of the totalSystemDiskSpace property.
     * 
     */
    public void setTotalSystemDiskSpace(long value) {
        this.totalSystemDiskSpace = value;
    }

    /**
     * Gets the value of the freeSystemDiskSpace property.
     * 
     */
    public long getFreeSystemDiskSpace() {
        return freeSystemDiskSpace;
    }

    /**
     * Sets the value of the freeSystemDiskSpace property.
     * 
     */
    public void setFreeSystemDiskSpace(long value) {
        this.freeSystemDiskSpace = value;
    }

    /**
     * Gets the value of the totalDataDiskSpace property.
     * 
     */
    public long getTotalDataDiskSpace() {
        return totalDataDiskSpace;
    }

    /**
     * Sets the value of the totalDataDiskSpace property.
     * 
     */
    public void setTotalDataDiskSpace(long value) {
        this.totalDataDiskSpace = value;
    }

    /**
     * Gets the value of the freeDataDiskSpace property.
     * 
     */
    public long getFreeDataDiskSpace() {
        return freeDataDiskSpace;
    }

    /**
     * Sets the value of the freeDataDiskSpace property.
     * 
     */
    public void setFreeDataDiskSpace(long value) {
        this.freeDataDiskSpace = value;
    }

}
