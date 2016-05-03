
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
 *         &lt;element name="GetDirectoryFilesResult" type="{http://ntobjectives.com/webservices/}ArrayOfString" minOccurs="0"/>
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
    "getDirectoryFilesResult"
})
@XmlRootElement(name = "GetDirectoryFilesResponse")
public class GetDirectoryFilesResponse {

    @XmlElement(name = "GetDirectoryFilesResult")
    protected ArrayOfString getDirectoryFilesResult;

    /**
     * Gets the value of the getDirectoryFilesResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getGetDirectoryFilesResult() {
        return getDirectoryFilesResult;
    }

    /**
     * Sets the value of the getDirectoryFilesResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setGetDirectoryFilesResult(ArrayOfString value) {
        this.getDirectoryFilesResult = value;
    }

}
