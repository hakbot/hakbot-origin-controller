
package io.hakbot.providers.appspider.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CRAWLCONFIG complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CRAWLCONFIG">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BinaryExt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TextExt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BinaryContentTypes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="HtmlContentTypes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TextContentTypes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BlackListExt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GrayListExt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DefaultPagesList" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DefaultTlds" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LockedCookieList" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MaxLinks" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="PerWebsiteMaxLinks" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="SsoMaxLinks" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="LinksPerSubCrawl" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Form" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ActiveX" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Applet" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="QueryCheck" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="DirectoryCheck" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="IFrameCheck" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ScriptCheck" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Comment" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Mailid" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Cookie" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="HttpHeaderCheck" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Flash" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="External" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="StayWithin" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="RestrictToPage" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="RestrictToMacro" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="CrawlRecordedOnly" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="HomogeneousProtocol" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="AssumeGoodLogin" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Ajax" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="PrivilegeEscalation" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="DefaultPages" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="RobotsCheck" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Initial302" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="FormWalkWhileDiscoverWhitelist" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="OutOfDomainWhileDiscoverWhitelist" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="DiscoverWsdl" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="RegetCookiesOnLogin" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="NoMemoryCheck" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="LockAllCookies" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="TrainFromRecordedTraffic" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="SessionHijacking" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="LoginMacro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LogoutLinkRegex" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LoginLinkRegex" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DomainCrawling" type="{http://ntobjectives.com/webservices/}DOMAINMODE"/>
 *         &lt;element name="SsoDomainCrawling" type="{http://ntobjectives.com/webservices/}DOMAINMODE"/>
 *         &lt;element name="DoNotDeleteJSFilesOnError" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="JavaScriptMode" type="{http://ntobjectives.com/webservices/}JAVASCRIPTENGINE"/>
 *         &lt;element name="BlackList" type="{http://ntobjectives.com/webservices/}ArrayOfString" minOccurs="0"/>
 *         &lt;element name="WhiteList" type="{http://ntobjectives.com/webservices/}ArrayOfString" minOccurs="0"/>
 *         &lt;element name="SingleSignonUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UserDefined404Page" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UsernameForm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PasswordForm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UsernameNtlm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PasswordNtlm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UsernameBasic" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PasswordBasic" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UserNameFormLowerPrivilege" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PassWordFormLowerPrivilege" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NtlmDomain" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="WsdlUrlList" type="{http://ntobjectives.com/webservices/}ArrayOfString" minOccurs="0"/>
 *         &lt;element name="PrivacyUrlList" type="{http://ntobjectives.com/webservices/}ArrayOfString" minOccurs="0"/>
 *         &lt;element name="RepetitionTolerance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FormRepetitionTolerance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TrafficFilePath" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TrafficFilePassword" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LoginScript" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Comprehensiveness" type="{http://ntobjectives.com/webservices/}COMPREHENSIVENESS"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CRAWLCONFIG", propOrder = {
    "binaryExt",
    "textExt",
    "binaryContentTypes",
    "htmlContentTypes",
    "textContentTypes",
    "blackListExt",
    "grayListExt",
    "defaultPagesList",
    "defaultTlds",
    "lockedCookieList",
    "maxLinks",
    "perWebsiteMaxLinks",
    "ssoMaxLinks",
    "linksPerSubCrawl",
    "form",
    "activeX",
    "applet",
    "queryCheck",
    "directoryCheck",
    "iFrameCheck",
    "scriptCheck",
    "comment",
    "mailid",
    "cookie",
    "httpHeaderCheck",
    "flash",
    "external",
    "stayWithin",
    "restrictToPage",
    "restrictToMacro",
    "crawlRecordedOnly",
    "homogeneousProtocol",
    "assumeGoodLogin",
    "ajax",
    "privilegeEscalation",
    "defaultPages",
    "robotsCheck",
    "initial302",
    "formWalkWhileDiscoverWhitelist",
    "outOfDomainWhileDiscoverWhitelist",
    "discoverWsdl",
    "regetCookiesOnLogin",
    "noMemoryCheck",
    "lockAllCookies",
    "trainFromRecordedTraffic",
    "sessionHijacking",
    "loginMacro",
    "logoutLinkRegex",
    "loginLinkRegex",
    "domainCrawling",
    "ssoDomainCrawling",
    "doNotDeleteJSFilesOnError",
    "javaScriptMode",
    "blackList",
    "whiteList",
    "singleSignonUrl",
    "userDefined404Page",
    "usernameForm",
    "passwordForm",
    "usernameNtlm",
    "passwordNtlm",
    "usernameBasic",
    "passwordBasic",
    "userNameFormLowerPrivilege",
    "passWordFormLowerPrivilege",
    "ntlmDomain",
    "wsdlUrlList",
    "privacyUrlList",
    "repetitionTolerance",
    "formRepetitionTolerance",
    "trafficFilePath",
    "trafficFilePassword",
    "loginScript",
    "comprehensiveness"
})
public class CRAWLCONFIG {

    @XmlElement(name = "BinaryExt")
    protected String binaryExt;
    @XmlElement(name = "TextExt")
    protected String textExt;
    @XmlElement(name = "BinaryContentTypes")
    protected String binaryContentTypes;
    @XmlElement(name = "HtmlContentTypes")
    protected String htmlContentTypes;
    @XmlElement(name = "TextContentTypes")
    protected String textContentTypes;
    @XmlElement(name = "BlackListExt")
    protected String blackListExt;
    @XmlElement(name = "GrayListExt")
    protected String grayListExt;
    @XmlElement(name = "DefaultPagesList")
    protected String defaultPagesList;
    @XmlElement(name = "DefaultTlds")
    protected String defaultTlds;
    @XmlElement(name = "LockedCookieList")
    protected String lockedCookieList;
    @XmlElement(name = "MaxLinks")
    protected int maxLinks;
    @XmlElement(name = "PerWebsiteMaxLinks")
    protected int perWebsiteMaxLinks;
    @XmlElement(name = "SsoMaxLinks")
    protected int ssoMaxLinks;
    @XmlElement(name = "LinksPerSubCrawl")
    protected int linksPerSubCrawl;
    @XmlElement(name = "Form")
    protected boolean form;
    @XmlElement(name = "ActiveX")
    protected boolean activeX;
    @XmlElement(name = "Applet")
    protected boolean applet;
    @XmlElement(name = "QueryCheck")
    protected boolean queryCheck;
    @XmlElement(name = "DirectoryCheck")
    protected boolean directoryCheck;
    @XmlElement(name = "IFrameCheck")
    protected boolean iFrameCheck;
    @XmlElement(name = "ScriptCheck")
    protected boolean scriptCheck;
    @XmlElement(name = "Comment")
    protected boolean comment;
    @XmlElement(name = "Mailid")
    protected boolean mailid;
    @XmlElement(name = "Cookie")
    protected boolean cookie;
    @XmlElement(name = "HttpHeaderCheck")
    protected boolean httpHeaderCheck;
    @XmlElement(name = "Flash")
    protected boolean flash;
    @XmlElement(name = "External")
    protected boolean external;
    @XmlElement(name = "StayWithin")
    protected boolean stayWithin;
    @XmlElement(name = "RestrictToPage")
    protected boolean restrictToPage;
    @XmlElement(name = "RestrictToMacro")
    protected boolean restrictToMacro;
    @XmlElement(name = "CrawlRecordedOnly")
    protected boolean crawlRecordedOnly;
    @XmlElement(name = "HomogeneousProtocol")
    protected boolean homogeneousProtocol;
    @XmlElement(name = "AssumeGoodLogin")
    protected boolean assumeGoodLogin;
    @XmlElement(name = "Ajax")
    protected boolean ajax;
    @XmlElement(name = "PrivilegeEscalation")
    protected boolean privilegeEscalation;
    @XmlElement(name = "DefaultPages")
    protected boolean defaultPages;
    @XmlElement(name = "RobotsCheck")
    protected boolean robotsCheck;
    @XmlElement(name = "Initial302")
    protected boolean initial302;
    @XmlElement(name = "FormWalkWhileDiscoverWhitelist")
    protected boolean formWalkWhileDiscoverWhitelist;
    @XmlElement(name = "OutOfDomainWhileDiscoverWhitelist")
    protected boolean outOfDomainWhileDiscoverWhitelist;
    @XmlElement(name = "DiscoverWsdl")
    protected boolean discoverWsdl;
    @XmlElement(name = "RegetCookiesOnLogin")
    protected boolean regetCookiesOnLogin;
    @XmlElement(name = "NoMemoryCheck")
    protected boolean noMemoryCheck;
    @XmlElement(name = "LockAllCookies")
    protected boolean lockAllCookies;
    @XmlElement(name = "TrainFromRecordedTraffic")
    protected boolean trainFromRecordedTraffic;
    @XmlElement(name = "SessionHijacking")
    protected boolean sessionHijacking;
    @XmlElement(name = "LoginMacro")
    protected String loginMacro;
    @XmlElement(name = "LogoutLinkRegex")
    protected String logoutLinkRegex;
    @XmlElement(name = "LoginLinkRegex")
    protected String loginLinkRegex;
    @XmlElement(name = "DomainCrawling", required = true)
    protected DOMAINMODE domainCrawling;
    @XmlElement(name = "SsoDomainCrawling", required = true)
    protected DOMAINMODE ssoDomainCrawling;
    @XmlElement(name = "DoNotDeleteJSFilesOnError")
    protected boolean doNotDeleteJSFilesOnError;
    @XmlElement(name = "JavaScriptMode", required = true)
    protected JAVASCRIPTENGINE javaScriptMode;
    @XmlElement(name = "BlackList")
    protected ArrayOfString blackList;
    @XmlElement(name = "WhiteList")
    protected ArrayOfString whiteList;
    @XmlElement(name = "SingleSignonUrl")
    protected String singleSignonUrl;
    @XmlElement(name = "UserDefined404Page")
    protected String userDefined404Page;
    @XmlElement(name = "UsernameForm")
    protected String usernameForm;
    @XmlElement(name = "PasswordForm")
    protected String passwordForm;
    @XmlElement(name = "UsernameNtlm")
    protected String usernameNtlm;
    @XmlElement(name = "PasswordNtlm")
    protected String passwordNtlm;
    @XmlElement(name = "UsernameBasic")
    protected String usernameBasic;
    @XmlElement(name = "PasswordBasic")
    protected String passwordBasic;
    @XmlElement(name = "UserNameFormLowerPrivilege")
    protected String userNameFormLowerPrivilege;
    @XmlElement(name = "PassWordFormLowerPrivilege")
    protected String passWordFormLowerPrivilege;
    @XmlElement(name = "NtlmDomain")
    protected String ntlmDomain;
    @XmlElement(name = "WsdlUrlList")
    protected ArrayOfString wsdlUrlList;
    @XmlElement(name = "PrivacyUrlList")
    protected ArrayOfString privacyUrlList;
    @XmlElement(name = "RepetitionTolerance")
    protected String repetitionTolerance;
    @XmlElement(name = "FormRepetitionTolerance")
    protected String formRepetitionTolerance;
    @XmlElement(name = "TrafficFilePath")
    protected String trafficFilePath;
    @XmlElement(name = "TrafficFilePassword")
    protected String trafficFilePassword;
    @XmlElement(name = "LoginScript")
    protected String loginScript;
    @XmlElement(name = "Comprehensiveness", required = true)
    protected COMPREHENSIVENESS comprehensiveness;

    /**
     * Gets the value of the binaryExt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBinaryExt() {
        return binaryExt;
    }

    /**
     * Sets the value of the binaryExt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBinaryExt(String value) {
        this.binaryExt = value;
    }

    /**
     * Gets the value of the textExt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTextExt() {
        return textExt;
    }

    /**
     * Sets the value of the textExt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTextExt(String value) {
        this.textExt = value;
    }

    /**
     * Gets the value of the binaryContentTypes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBinaryContentTypes() {
        return binaryContentTypes;
    }

    /**
     * Sets the value of the binaryContentTypes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBinaryContentTypes(String value) {
        this.binaryContentTypes = value;
    }

    /**
     * Gets the value of the htmlContentTypes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHtmlContentTypes() {
        return htmlContentTypes;
    }

    /**
     * Sets the value of the htmlContentTypes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHtmlContentTypes(String value) {
        this.htmlContentTypes = value;
    }

    /**
     * Gets the value of the textContentTypes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTextContentTypes() {
        return textContentTypes;
    }

    /**
     * Sets the value of the textContentTypes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTextContentTypes(String value) {
        this.textContentTypes = value;
    }

    /**
     * Gets the value of the blackListExt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBlackListExt() {
        return blackListExt;
    }

    /**
     * Sets the value of the blackListExt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBlackListExt(String value) {
        this.blackListExt = value;
    }

    /**
     * Gets the value of the grayListExt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGrayListExt() {
        return grayListExt;
    }

    /**
     * Sets the value of the grayListExt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGrayListExt(String value) {
        this.grayListExt = value;
    }

    /**
     * Gets the value of the defaultPagesList property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultPagesList() {
        return defaultPagesList;
    }

    /**
     * Sets the value of the defaultPagesList property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultPagesList(String value) {
        this.defaultPagesList = value;
    }

    /**
     * Gets the value of the defaultTlds property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultTlds() {
        return defaultTlds;
    }

    /**
     * Sets the value of the defaultTlds property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultTlds(String value) {
        this.defaultTlds = value;
    }

    /**
     * Gets the value of the lockedCookieList property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLockedCookieList() {
        return lockedCookieList;
    }

    /**
     * Sets the value of the lockedCookieList property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLockedCookieList(String value) {
        this.lockedCookieList = value;
    }

    /**
     * Gets the value of the maxLinks property.
     * 
     */
    public int getMaxLinks() {
        return maxLinks;
    }

    /**
     * Sets the value of the maxLinks property.
     * 
     */
    public void setMaxLinks(int value) {
        this.maxLinks = value;
    }

    /**
     * Gets the value of the perWebsiteMaxLinks property.
     * 
     */
    public int getPerWebsiteMaxLinks() {
        return perWebsiteMaxLinks;
    }

    /**
     * Sets the value of the perWebsiteMaxLinks property.
     * 
     */
    public void setPerWebsiteMaxLinks(int value) {
        this.perWebsiteMaxLinks = value;
    }

    /**
     * Gets the value of the ssoMaxLinks property.
     * 
     */
    public int getSsoMaxLinks() {
        return ssoMaxLinks;
    }

    /**
     * Sets the value of the ssoMaxLinks property.
     * 
     */
    public void setSsoMaxLinks(int value) {
        this.ssoMaxLinks = value;
    }

    /**
     * Gets the value of the linksPerSubCrawl property.
     * 
     */
    public int getLinksPerSubCrawl() {
        return linksPerSubCrawl;
    }

    /**
     * Sets the value of the linksPerSubCrawl property.
     * 
     */
    public void setLinksPerSubCrawl(int value) {
        this.linksPerSubCrawl = value;
    }

    /**
     * Gets the value of the form property.
     * 
     */
    public boolean isForm() {
        return form;
    }

    /**
     * Sets the value of the form property.
     * 
     */
    public void setForm(boolean value) {
        this.form = value;
    }

    /**
     * Gets the value of the activeX property.
     * 
     */
    public boolean isActiveX() {
        return activeX;
    }

    /**
     * Sets the value of the activeX property.
     * 
     */
    public void setActiveX(boolean value) {
        this.activeX = value;
    }

    /**
     * Gets the value of the applet property.
     * 
     */
    public boolean isApplet() {
        return applet;
    }

    /**
     * Sets the value of the applet property.
     * 
     */
    public void setApplet(boolean value) {
        this.applet = value;
    }

    /**
     * Gets the value of the queryCheck property.
     * 
     */
    public boolean isQueryCheck() {
        return queryCheck;
    }

    /**
     * Sets the value of the queryCheck property.
     * 
     */
    public void setQueryCheck(boolean value) {
        this.queryCheck = value;
    }

    /**
     * Gets the value of the directoryCheck property.
     * 
     */
    public boolean isDirectoryCheck() {
        return directoryCheck;
    }

    /**
     * Sets the value of the directoryCheck property.
     * 
     */
    public void setDirectoryCheck(boolean value) {
        this.directoryCheck = value;
    }

    /**
     * Gets the value of the iFrameCheck property.
     * 
     */
    public boolean isIFrameCheck() {
        return iFrameCheck;
    }

    /**
     * Sets the value of the iFrameCheck property.
     * 
     */
    public void setIFrameCheck(boolean value) {
        this.iFrameCheck = value;
    }

    /**
     * Gets the value of the scriptCheck property.
     * 
     */
    public boolean isScriptCheck() {
        return scriptCheck;
    }

    /**
     * Sets the value of the scriptCheck property.
     * 
     */
    public void setScriptCheck(boolean value) {
        this.scriptCheck = value;
    }

    /**
     * Gets the value of the comment property.
     * 
     */
    public boolean isComment() {
        return comment;
    }

    /**
     * Sets the value of the comment property.
     * 
     */
    public void setComment(boolean value) {
        this.comment = value;
    }

    /**
     * Gets the value of the mailid property.
     * 
     */
    public boolean isMailid() {
        return mailid;
    }

    /**
     * Sets the value of the mailid property.
     * 
     */
    public void setMailid(boolean value) {
        this.mailid = value;
    }

    /**
     * Gets the value of the cookie property.
     * 
     */
    public boolean isCookie() {
        return cookie;
    }

    /**
     * Sets the value of the cookie property.
     * 
     */
    public void setCookie(boolean value) {
        this.cookie = value;
    }

    /**
     * Gets the value of the httpHeaderCheck property.
     * 
     */
    public boolean isHttpHeaderCheck() {
        return httpHeaderCheck;
    }

    /**
     * Sets the value of the httpHeaderCheck property.
     * 
     */
    public void setHttpHeaderCheck(boolean value) {
        this.httpHeaderCheck = value;
    }

    /**
     * Gets the value of the flash property.
     * 
     */
    public boolean isFlash() {
        return flash;
    }

    /**
     * Sets the value of the flash property.
     * 
     */
    public void setFlash(boolean value) {
        this.flash = value;
    }

    /**
     * Gets the value of the external property.
     * 
     */
    public boolean isExternal() {
        return external;
    }

    /**
     * Sets the value of the external property.
     * 
     */
    public void setExternal(boolean value) {
        this.external = value;
    }

    /**
     * Gets the value of the stayWithin property.
     * 
     */
    public boolean isStayWithin() {
        return stayWithin;
    }

    /**
     * Sets the value of the stayWithin property.
     * 
     */
    public void setStayWithin(boolean value) {
        this.stayWithin = value;
    }

    /**
     * Gets the value of the restrictToPage property.
     * 
     */
    public boolean isRestrictToPage() {
        return restrictToPage;
    }

    /**
     * Sets the value of the restrictToPage property.
     * 
     */
    public void setRestrictToPage(boolean value) {
        this.restrictToPage = value;
    }

    /**
     * Gets the value of the restrictToMacro property.
     * 
     */
    public boolean isRestrictToMacro() {
        return restrictToMacro;
    }

    /**
     * Sets the value of the restrictToMacro property.
     * 
     */
    public void setRestrictToMacro(boolean value) {
        this.restrictToMacro = value;
    }

    /**
     * Gets the value of the crawlRecordedOnly property.
     * 
     */
    public boolean isCrawlRecordedOnly() {
        return crawlRecordedOnly;
    }

    /**
     * Sets the value of the crawlRecordedOnly property.
     * 
     */
    public void setCrawlRecordedOnly(boolean value) {
        this.crawlRecordedOnly = value;
    }

    /**
     * Gets the value of the homogeneousProtocol property.
     * 
     */
    public boolean isHomogeneousProtocol() {
        return homogeneousProtocol;
    }

    /**
     * Sets the value of the homogeneousProtocol property.
     * 
     */
    public void setHomogeneousProtocol(boolean value) {
        this.homogeneousProtocol = value;
    }

    /**
     * Gets the value of the assumeGoodLogin property.
     * 
     */
    public boolean isAssumeGoodLogin() {
        return assumeGoodLogin;
    }

    /**
     * Sets the value of the assumeGoodLogin property.
     * 
     */
    public void setAssumeGoodLogin(boolean value) {
        this.assumeGoodLogin = value;
    }

    /**
     * Gets the value of the ajax property.
     * 
     */
    public boolean isAjax() {
        return ajax;
    }

    /**
     * Sets the value of the ajax property.
     * 
     */
    public void setAjax(boolean value) {
        this.ajax = value;
    }

    /**
     * Gets the value of the privilegeEscalation property.
     * 
     */
    public boolean isPrivilegeEscalation() {
        return privilegeEscalation;
    }

    /**
     * Sets the value of the privilegeEscalation property.
     * 
     */
    public void setPrivilegeEscalation(boolean value) {
        this.privilegeEscalation = value;
    }

    /**
     * Gets the value of the defaultPages property.
     * 
     */
    public boolean isDefaultPages() {
        return defaultPages;
    }

    /**
     * Sets the value of the defaultPages property.
     * 
     */
    public void setDefaultPages(boolean value) {
        this.defaultPages = value;
    }

    /**
     * Gets the value of the robotsCheck property.
     * 
     */
    public boolean isRobotsCheck() {
        return robotsCheck;
    }

    /**
     * Sets the value of the robotsCheck property.
     * 
     */
    public void setRobotsCheck(boolean value) {
        this.robotsCheck = value;
    }

    /**
     * Gets the value of the initial302 property.
     * 
     */
    public boolean isInitial302() {
        return initial302;
    }

    /**
     * Sets the value of the initial302 property.
     * 
     */
    public void setInitial302(boolean value) {
        this.initial302 = value;
    }

    /**
     * Gets the value of the formWalkWhileDiscoverWhitelist property.
     * 
     */
    public boolean isFormWalkWhileDiscoverWhitelist() {
        return formWalkWhileDiscoverWhitelist;
    }

    /**
     * Sets the value of the formWalkWhileDiscoverWhitelist property.
     * 
     */
    public void setFormWalkWhileDiscoverWhitelist(boolean value) {
        this.formWalkWhileDiscoverWhitelist = value;
    }

    /**
     * Gets the value of the outOfDomainWhileDiscoverWhitelist property.
     * 
     */
    public boolean isOutOfDomainWhileDiscoverWhitelist() {
        return outOfDomainWhileDiscoverWhitelist;
    }

    /**
     * Sets the value of the outOfDomainWhileDiscoverWhitelist property.
     * 
     */
    public void setOutOfDomainWhileDiscoverWhitelist(boolean value) {
        this.outOfDomainWhileDiscoverWhitelist = value;
    }

    /**
     * Gets the value of the discoverWsdl property.
     * 
     */
    public boolean isDiscoverWsdl() {
        return discoverWsdl;
    }

    /**
     * Sets the value of the discoverWsdl property.
     * 
     */
    public void setDiscoverWsdl(boolean value) {
        this.discoverWsdl = value;
    }

    /**
     * Gets the value of the regetCookiesOnLogin property.
     * 
     */
    public boolean isRegetCookiesOnLogin() {
        return regetCookiesOnLogin;
    }

    /**
     * Sets the value of the regetCookiesOnLogin property.
     * 
     */
    public void setRegetCookiesOnLogin(boolean value) {
        this.regetCookiesOnLogin = value;
    }

    /**
     * Gets the value of the noMemoryCheck property.
     * 
     */
    public boolean isNoMemoryCheck() {
        return noMemoryCheck;
    }

    /**
     * Sets the value of the noMemoryCheck property.
     * 
     */
    public void setNoMemoryCheck(boolean value) {
        this.noMemoryCheck = value;
    }

    /**
     * Gets the value of the lockAllCookies property.
     * 
     */
    public boolean isLockAllCookies() {
        return lockAllCookies;
    }

    /**
     * Sets the value of the lockAllCookies property.
     * 
     */
    public void setLockAllCookies(boolean value) {
        this.lockAllCookies = value;
    }

    /**
     * Gets the value of the trainFromRecordedTraffic property.
     * 
     */
    public boolean isTrainFromRecordedTraffic() {
        return trainFromRecordedTraffic;
    }

    /**
     * Sets the value of the trainFromRecordedTraffic property.
     * 
     */
    public void setTrainFromRecordedTraffic(boolean value) {
        this.trainFromRecordedTraffic = value;
    }

    /**
     * Gets the value of the sessionHijacking property.
     * 
     */
    public boolean isSessionHijacking() {
        return sessionHijacking;
    }

    /**
     * Sets the value of the sessionHijacking property.
     * 
     */
    public void setSessionHijacking(boolean value) {
        this.sessionHijacking = value;
    }

    /**
     * Gets the value of the loginMacro property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoginMacro() {
        return loginMacro;
    }

    /**
     * Sets the value of the loginMacro property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoginMacro(String value) {
        this.loginMacro = value;
    }

    /**
     * Gets the value of the logoutLinkRegex property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogoutLinkRegex() {
        return logoutLinkRegex;
    }

    /**
     * Sets the value of the logoutLinkRegex property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogoutLinkRegex(String value) {
        this.logoutLinkRegex = value;
    }

    /**
     * Gets the value of the loginLinkRegex property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoginLinkRegex() {
        return loginLinkRegex;
    }

    /**
     * Sets the value of the loginLinkRegex property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoginLinkRegex(String value) {
        this.loginLinkRegex = value;
    }

    /**
     * Gets the value of the domainCrawling property.
     * 
     * @return
     *     possible object is
     *     {@link DOMAINMODE }
     *     
     */
    public DOMAINMODE getDomainCrawling() {
        return domainCrawling;
    }

    /**
     * Sets the value of the domainCrawling property.
     * 
     * @param value
     *     allowed object is
     *     {@link DOMAINMODE }
     *     
     */
    public void setDomainCrawling(DOMAINMODE value) {
        this.domainCrawling = value;
    }

    /**
     * Gets the value of the ssoDomainCrawling property.
     * 
     * @return
     *     possible object is
     *     {@link DOMAINMODE }
     *     
     */
    public DOMAINMODE getSsoDomainCrawling() {
        return ssoDomainCrawling;
    }

    /**
     * Sets the value of the ssoDomainCrawling property.
     * 
     * @param value
     *     allowed object is
     *     {@link DOMAINMODE }
     *     
     */
    public void setSsoDomainCrawling(DOMAINMODE value) {
        this.ssoDomainCrawling = value;
    }

    /**
     * Gets the value of the doNotDeleteJSFilesOnError property.
     * 
     */
    public boolean isDoNotDeleteJSFilesOnError() {
        return doNotDeleteJSFilesOnError;
    }

    /**
     * Sets the value of the doNotDeleteJSFilesOnError property.
     * 
     */
    public void setDoNotDeleteJSFilesOnError(boolean value) {
        this.doNotDeleteJSFilesOnError = value;
    }

    /**
     * Gets the value of the javaScriptMode property.
     * 
     * @return
     *     possible object is
     *     {@link JAVASCRIPTENGINE }
     *     
     */
    public JAVASCRIPTENGINE getJavaScriptMode() {
        return javaScriptMode;
    }

    /**
     * Sets the value of the javaScriptMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAVASCRIPTENGINE }
     *     
     */
    public void setJavaScriptMode(JAVASCRIPTENGINE value) {
        this.javaScriptMode = value;
    }

    /**
     * Gets the value of the blackList property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getBlackList() {
        return blackList;
    }

    /**
     * Sets the value of the blackList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setBlackList(ArrayOfString value) {
        this.blackList = value;
    }

    /**
     * Gets the value of the whiteList property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getWhiteList() {
        return whiteList;
    }

    /**
     * Sets the value of the whiteList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setWhiteList(ArrayOfString value) {
        this.whiteList = value;
    }

    /**
     * Gets the value of the singleSignonUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSingleSignonUrl() {
        return singleSignonUrl;
    }

    /**
     * Sets the value of the singleSignonUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSingleSignonUrl(String value) {
        this.singleSignonUrl = value;
    }

    /**
     * Gets the value of the userDefined404Page property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserDefined404Page() {
        return userDefined404Page;
    }

    /**
     * Sets the value of the userDefined404Page property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserDefined404Page(String value) {
        this.userDefined404Page = value;
    }

    /**
     * Gets the value of the usernameForm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsernameForm() {
        return usernameForm;
    }

    /**
     * Sets the value of the usernameForm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsernameForm(String value) {
        this.usernameForm = value;
    }

    /**
     * Gets the value of the passwordForm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPasswordForm() {
        return passwordForm;
    }

    /**
     * Sets the value of the passwordForm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPasswordForm(String value) {
        this.passwordForm = value;
    }

    /**
     * Gets the value of the usernameNtlm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsernameNtlm() {
        return usernameNtlm;
    }

    /**
     * Sets the value of the usernameNtlm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsernameNtlm(String value) {
        this.usernameNtlm = value;
    }

    /**
     * Gets the value of the passwordNtlm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPasswordNtlm() {
        return passwordNtlm;
    }

    /**
     * Sets the value of the passwordNtlm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPasswordNtlm(String value) {
        this.passwordNtlm = value;
    }

    /**
     * Gets the value of the usernameBasic property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsernameBasic() {
        return usernameBasic;
    }

    /**
     * Sets the value of the usernameBasic property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsernameBasic(String value) {
        this.usernameBasic = value;
    }

    /**
     * Gets the value of the passwordBasic property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPasswordBasic() {
        return passwordBasic;
    }

    /**
     * Sets the value of the passwordBasic property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPasswordBasic(String value) {
        this.passwordBasic = value;
    }

    /**
     * Gets the value of the userNameFormLowerPrivilege property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserNameFormLowerPrivilege() {
        return userNameFormLowerPrivilege;
    }

    /**
     * Sets the value of the userNameFormLowerPrivilege property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserNameFormLowerPrivilege(String value) {
        this.userNameFormLowerPrivilege = value;
    }

    /**
     * Gets the value of the passWordFormLowerPrivilege property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassWordFormLowerPrivilege() {
        return passWordFormLowerPrivilege;
    }

    /**
     * Sets the value of the passWordFormLowerPrivilege property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassWordFormLowerPrivilege(String value) {
        this.passWordFormLowerPrivilege = value;
    }

    /**
     * Gets the value of the ntlmDomain property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNtlmDomain() {
        return ntlmDomain;
    }

    /**
     * Sets the value of the ntlmDomain property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNtlmDomain(String value) {
        this.ntlmDomain = value;
    }

    /**
     * Gets the value of the wsdlUrlList property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getWsdlUrlList() {
        return wsdlUrlList;
    }

    /**
     * Sets the value of the wsdlUrlList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setWsdlUrlList(ArrayOfString value) {
        this.wsdlUrlList = value;
    }

    /**
     * Gets the value of the privacyUrlList property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getPrivacyUrlList() {
        return privacyUrlList;
    }

    /**
     * Sets the value of the privacyUrlList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setPrivacyUrlList(ArrayOfString value) {
        this.privacyUrlList = value;
    }

    /**
     * Gets the value of the repetitionTolerance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRepetitionTolerance() {
        return repetitionTolerance;
    }

    /**
     * Sets the value of the repetitionTolerance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRepetitionTolerance(String value) {
        this.repetitionTolerance = value;
    }

    /**
     * Gets the value of the formRepetitionTolerance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormRepetitionTolerance() {
        return formRepetitionTolerance;
    }

    /**
     * Sets the value of the formRepetitionTolerance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormRepetitionTolerance(String value) {
        this.formRepetitionTolerance = value;
    }

    /**
     * Gets the value of the trafficFilePath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrafficFilePath() {
        return trafficFilePath;
    }

    /**
     * Sets the value of the trafficFilePath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrafficFilePath(String value) {
        this.trafficFilePath = value;
    }

    /**
     * Gets the value of the trafficFilePassword property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrafficFilePassword() {
        return trafficFilePassword;
    }

    /**
     * Sets the value of the trafficFilePassword property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrafficFilePassword(String value) {
        this.trafficFilePassword = value;
    }

    /**
     * Gets the value of the loginScript property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoginScript() {
        return loginScript;
    }

    /**
     * Sets the value of the loginScript property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoginScript(String value) {
        this.loginScript = value;
    }

    /**
     * Gets the value of the comprehensiveness property.
     * 
     * @return
     *     possible object is
     *     {@link COMPREHENSIVENESS }
     *     
     */
    public COMPREHENSIVENESS getComprehensiveness() {
        return comprehensiveness;
    }

    /**
     * Sets the value of the comprehensiveness property.
     * 
     * @param value
     *     allowed object is
     *     {@link COMPREHENSIVENESS }
     *     
     */
    public void setComprehensiveness(COMPREHENSIVENESS value) {
        this.comprehensiveness = value;
    }

}
