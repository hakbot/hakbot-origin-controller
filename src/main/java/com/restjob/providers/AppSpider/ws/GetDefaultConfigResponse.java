
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
 *         &lt;element name="CrawlConfig" type="{http://ntobjectives.com/webservices/}CRAWLCONFIG" minOccurs="0"/>
 *         &lt;element name="SpiderConfig" type="{http://ntobjectives.com/webservices/}SPIDERCONFIG" minOccurs="0"/>
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
    "crawlConfig",
    "spiderConfig"
})
@XmlRootElement(name = "GetDefaultConfigResponse")
public class GetDefaultConfigResponse {

    @XmlElement(name = "CrawlConfig")
    protected CRAWLCONFIG crawlConfig;
    @XmlElement(name = "SpiderConfig")
    protected SPIDERCONFIG spiderConfig;

    /**
     * Gets the value of the crawlConfig property.
     * 
     * @return
     *     possible object is
     *     {@link CRAWLCONFIG }
     *     
     */
    public CRAWLCONFIG getCrawlConfig() {
        return crawlConfig;
    }

    /**
     * Sets the value of the crawlConfig property.
     * 
     * @param value
     *     allowed object is
     *     {@link CRAWLCONFIG }
     *     
     */
    public void setCrawlConfig(CRAWLCONFIG value) {
        this.crawlConfig = value;
    }

    /**
     * Gets the value of the spiderConfig property.
     * 
     * @return
     *     possible object is
     *     {@link SPIDERCONFIG }
     *     
     */
    public SPIDERCONFIG getSpiderConfig() {
        return spiderConfig;
    }

    /**
     * Sets the value of the spiderConfig property.
     * 
     * @param value
     *     allowed object is
     *     {@link SPIDERCONFIG }
     *     
     */
    public void setSpiderConfig(SPIDERCONFIG value) {
        this.spiderConfig = value;
    }

}
