
package com.restjob.controller.providers.AppSpider.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MODULESTATUS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MODULESTATUS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ModuleName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PassiveAnalysis" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Attempted" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Vulnerable" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MODULESTATUS", propOrder = {
    "moduleName",
    "passiveAnalysis",
    "attempted",
    "vulnerable"
})
public class MODULESTATUS {

    @XmlElement(name = "ModuleName")
    protected String moduleName;
    @XmlElement(name = "PassiveAnalysis")
    protected boolean passiveAnalysis;
    @XmlElement(name = "Attempted")
    protected int attempted;
    @XmlElement(name = "Vulnerable")
    protected int vulnerable;

    /**
     * Gets the value of the moduleName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModuleName() {
        return moduleName;
    }

    /**
     * Sets the value of the moduleName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModuleName(String value) {
        this.moduleName = value;
    }

    /**
     * Gets the value of the passiveAnalysis property.
     * 
     */
    public boolean isPassiveAnalysis() {
        return passiveAnalysis;
    }

    /**
     * Sets the value of the passiveAnalysis property.
     * 
     */
    public void setPassiveAnalysis(boolean value) {
        this.passiveAnalysis = value;
    }

    /**
     * Gets the value of the attempted property.
     * 
     */
    public int getAttempted() {
        return attempted;
    }

    /**
     * Sets the value of the attempted property.
     * 
     */
    public void setAttempted(int value) {
        this.attempted = value;
    }

    /**
     * Gets the value of the vulnerable property.
     * 
     */
    public int getVulnerable() {
        return vulnerable;
    }

    /**
     * Sets the value of the vulnerable property.
     * 
     */
    public void setVulnerable(int value) {
        this.vulnerable = value;
    }

}
