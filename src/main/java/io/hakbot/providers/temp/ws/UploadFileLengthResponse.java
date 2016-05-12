
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
 *         &lt;element name="UploadFileLengthResult" type="{http://ntobjectives.com/webservices/}Result"/>
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
    "uploadFileLengthResult"
})
@XmlRootElement(name = "UploadFileLengthResponse")
public class UploadFileLengthResponse {

    @XmlElement(name = "UploadFileLengthResult", required = true)
    protected Result uploadFileLengthResult;

    /**
     * Gets the value of the uploadFileLengthResult property.
     * 
     * @return
     *     possible object is
     *     {@link Result }
     *     
     */
    public Result getUploadFileLengthResult() {
        return uploadFileLengthResult;
    }

    /**
     * Sets the value of the uploadFileLengthResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link Result }
     *     
     */
    public void setUploadFileLengthResult(Result value) {
        this.uploadFileLengthResult = value;
    }

}
