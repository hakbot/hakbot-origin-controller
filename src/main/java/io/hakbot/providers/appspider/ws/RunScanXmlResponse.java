
package io.hakbot.providers.appspider.ws;

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
 *         &lt;element name="RunScanXmlResult" type="{http://ntobjectives.com/webservices/}Result"/>
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
    "runScanXmlResult"
})
@XmlRootElement(name = "RunScanXmlResponse")
public class RunScanXmlResponse {

    @XmlElement(name = "RunScanXmlResult", required = true)
    protected Result runScanXmlResult;

    /**
     * Gets the value of the runScanXmlResult property.
     * 
     * @return
     *     possible object is
     *     {@link Result }
     *     
     */
    public Result getRunScanXmlResult() {
        return runScanXmlResult;
    }

    /**
     * Sets the value of the runScanXmlResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link Result }
     *     
     */
    public void setRunScanXmlResult(Result value) {
        this.runScanXmlResult = value;
    }

}
