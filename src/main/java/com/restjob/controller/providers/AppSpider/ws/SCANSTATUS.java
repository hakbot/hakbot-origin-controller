
package com.restjob.controller.providers.AppSpider.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for SCANSTATUS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SCANSTATUS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Token" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Running" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Crashed" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Scanned" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="LoggedIn" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="LinksInQueue" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="LinksCrawled" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="AttacksInQueue" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Attacked" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Vulnerable" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Requests" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="FailedRequests" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="NetworkSpeed" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="DripDelay" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="AvgRespTime" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="StartTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="EndTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="Elapsed" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ModuleStatusList" type="{http://ntobjectives.com/webservices/}ArrayOfMODULESTATUS" minOccurs="0"/>
 *         &lt;element name="EventList" type="{http://ntobjectives.com/webservices/}ArrayOfEVENT" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SCANSTATUS", propOrder = {
    "token",
    "running",
    "crashed",
    "scanned",
    "loggedIn",
    "linksInQueue",
    "linksCrawled",
    "attacksInQueue",
    "attacked",
    "vulnerable",
    "requests",
    "failedRequests",
    "networkSpeed",
    "dripDelay",
    "avgRespTime",
    "startTime",
    "endTime",
    "elapsed",
    "moduleStatusList",
    "eventList"
})
@XmlSeeAlso({
    SCANSTATUS2 .class
})
public class SCANSTATUS {

    @XmlElement(name = "Token")
    protected String token;
    @XmlElement(name = "Running")
    protected boolean running;
    @XmlElement(name = "Crashed")
    protected boolean crashed;
    @XmlElement(name = "Scanned")
    protected boolean scanned;
    @XmlElement(name = "LoggedIn")
    protected boolean loggedIn;
    @XmlElement(name = "LinksInQueue")
    protected int linksInQueue;
    @XmlElement(name = "LinksCrawled")
    protected int linksCrawled;
    @XmlElement(name = "AttacksInQueue")
    protected int attacksInQueue;
    @XmlElement(name = "Attacked")
    protected int attacked;
    @XmlElement(name = "Vulnerable")
    protected int vulnerable;
    @XmlElement(name = "Requests")
    protected int requests;
    @XmlElement(name = "FailedRequests")
    protected int failedRequests;
    @XmlElement(name = "NetworkSpeed")
    protected int networkSpeed;
    @XmlElement(name = "DripDelay")
    protected int dripDelay;
    @XmlElement(name = "AvgRespTime")
    protected int avgRespTime;
    @XmlElement(name = "StartTime", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startTime;
    @XmlElement(name = "EndTime", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar endTime;
    @XmlElement(name = "Elapsed")
    protected String elapsed;
    @XmlElement(name = "ModuleStatusList")
    protected ArrayOfMODULESTATUS moduleStatusList;
    @XmlElement(name = "EventList")
    protected ArrayOfEVENT eventList;

    /**
     * Gets the value of the token property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets the value of the token property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToken(String value) {
        this.token = value;
    }

    /**
     * Gets the value of the running property.
     * 
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Sets the value of the running property.
     * 
     */
    public void setRunning(boolean value) {
        this.running = value;
    }

    /**
     * Gets the value of the crashed property.
     * 
     */
    public boolean isCrashed() {
        return crashed;
    }

    /**
     * Sets the value of the crashed property.
     * 
     */
    public void setCrashed(boolean value) {
        this.crashed = value;
    }

    /**
     * Gets the value of the scanned property.
     * 
     */
    public boolean isScanned() {
        return scanned;
    }

    /**
     * Sets the value of the scanned property.
     * 
     */
    public void setScanned(boolean value) {
        this.scanned = value;
    }

    /**
     * Gets the value of the loggedIn property.
     * 
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     * Sets the value of the loggedIn property.
     * 
     */
    public void setLoggedIn(boolean value) {
        this.loggedIn = value;
    }

    /**
     * Gets the value of the linksInQueue property.
     * 
     */
    public int getLinksInQueue() {
        return linksInQueue;
    }

    /**
     * Sets the value of the linksInQueue property.
     * 
     */
    public void setLinksInQueue(int value) {
        this.linksInQueue = value;
    }

    /**
     * Gets the value of the linksCrawled property.
     * 
     */
    public int getLinksCrawled() {
        return linksCrawled;
    }

    /**
     * Sets the value of the linksCrawled property.
     * 
     */
    public void setLinksCrawled(int value) {
        this.linksCrawled = value;
    }

    /**
     * Gets the value of the attacksInQueue property.
     * 
     */
    public int getAttacksInQueue() {
        return attacksInQueue;
    }

    /**
     * Sets the value of the attacksInQueue property.
     * 
     */
    public void setAttacksInQueue(int value) {
        this.attacksInQueue = value;
    }

    /**
     * Gets the value of the attacked property.
     * 
     */
    public int getAttacked() {
        return attacked;
    }

    /**
     * Sets the value of the attacked property.
     * 
     */
    public void setAttacked(int value) {
        this.attacked = value;
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

    /**
     * Gets the value of the requests property.
     * 
     */
    public int getRequests() {
        return requests;
    }

    /**
     * Sets the value of the requests property.
     * 
     */
    public void setRequests(int value) {
        this.requests = value;
    }

    /**
     * Gets the value of the failedRequests property.
     * 
     */
    public int getFailedRequests() {
        return failedRequests;
    }

    /**
     * Sets the value of the failedRequests property.
     * 
     */
    public void setFailedRequests(int value) {
        this.failedRequests = value;
    }

    /**
     * Gets the value of the networkSpeed property.
     * 
     */
    public int getNetworkSpeed() {
        return networkSpeed;
    }

    /**
     * Sets the value of the networkSpeed property.
     * 
     */
    public void setNetworkSpeed(int value) {
        this.networkSpeed = value;
    }

    /**
     * Gets the value of the dripDelay property.
     * 
     */
    public int getDripDelay() {
        return dripDelay;
    }

    /**
     * Sets the value of the dripDelay property.
     * 
     */
    public void setDripDelay(int value) {
        this.dripDelay = value;
    }

    /**
     * Gets the value of the avgRespTime property.
     * 
     */
    public int getAvgRespTime() {
        return avgRespTime;
    }

    /**
     * Sets the value of the avgRespTime property.
     * 
     */
    public void setAvgRespTime(int value) {
        this.avgRespTime = value;
    }

    /**
     * Gets the value of the startTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartTime() {
        return startTime;
    }

    /**
     * Sets the value of the startTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartTime(XMLGregorianCalendar value) {
        this.startTime = value;
    }

    /**
     * Gets the value of the endTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndTime() {
        return endTime;
    }

    /**
     * Sets the value of the endTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndTime(XMLGregorianCalendar value) {
        this.endTime = value;
    }

    /**
     * Gets the value of the elapsed property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getElapsed() {
        return elapsed;
    }

    /**
     * Sets the value of the elapsed property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setElapsed(String value) {
        this.elapsed = value;
    }

    /**
     * Gets the value of the moduleStatusList property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfMODULESTATUS }
     *     
     */
    public ArrayOfMODULESTATUS getModuleStatusList() {
        return moduleStatusList;
    }

    /**
     * Sets the value of the moduleStatusList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfMODULESTATUS }
     *     
     */
    public void setModuleStatusList(ArrayOfMODULESTATUS value) {
        this.moduleStatusList = value;
    }

    /**
     * Gets the value of the eventList property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfEVENT }
     *     
     */
    public ArrayOfEVENT getEventList() {
        return eventList;
    }

    /**
     * Sets the value of the eventList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfEVENT }
     *     
     */
    public void setEventList(ArrayOfEVENT value) {
        this.eventList = value;
    }

}
