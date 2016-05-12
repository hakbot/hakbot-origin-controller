
package io.hakbot.providers.temp.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ThreeStrings complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ThreeStrings">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Str1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Str2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Str3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ThreeStrings", propOrder = {
    "str1",
    "str2",
    "str3"
})
public class ThreeStrings {

    @XmlElement(name = "Str1")
    protected String str1;
    @XmlElement(name = "Str2")
    protected String str2;
    @XmlElement(name = "Str3")
    protected String str3;

    /**
     * Gets the value of the str1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStr1() {
        return str1;
    }

    /**
     * Sets the value of the str1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStr1(String value) {
        this.str1 = value;
    }

    /**
     * Gets the value of the str2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStr2() {
        return str2;
    }

    /**
     * Sets the value of the str2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStr2(String value) {
        this.str2 = value;
    }

    /**
     * Gets the value of the str3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStr3() {
        return str3;
    }

    /**
     * Sets the value of the str3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStr3(String value) {
        this.str3 = value;
    }

}
