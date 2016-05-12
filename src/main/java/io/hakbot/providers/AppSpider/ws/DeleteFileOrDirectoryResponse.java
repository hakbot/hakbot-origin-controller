
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
 *         &lt;element name="DeleteFileOrDirectoryResult" type="{http://ntobjectives.com/webservices/}Result"/>
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
    "deleteFileOrDirectoryResult"
})
@XmlRootElement(name = "DeleteFileOrDirectoryResponse")
public class DeleteFileOrDirectoryResponse {

    @XmlElement(name = "DeleteFileOrDirectoryResult", required = true)
    protected Result deleteFileOrDirectoryResult;

    /**
     * Gets the value of the deleteFileOrDirectoryResult property.
     * 
     * @return
     *     possible object is
     *     {@link Result }
     *     
     */
    public Result getDeleteFileOrDirectoryResult() {
        return deleteFileOrDirectoryResult;
    }

    /**
     * Sets the value of the deleteFileOrDirectoryResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link Result }
     *     
     */
    public void setDeleteFileOrDirectoryResult(Result value) {
        this.deleteFileOrDirectoryResult = value;
    }

}
