
package com.restjob.providers.appspider.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SPIDERCONFIG complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SPIDERCONFIG">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Cookies" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ExHeader" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UserDoNotAttackParameters" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BenchHost" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SqlInjection" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="BlindSqlInjection" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="RFI" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="FileUpload" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="HttpResponeSplitting" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="CmdInjection" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="SCodeDisclosure" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="WebServiceAnalysis" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ActiveXAnalysis" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ResourceFinder" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ResourceFinderNikto" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="DirectoryBrowse" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="AuthTest" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="XSS" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ReverseProxy" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="TraceDetection" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="JavaGrinder" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="SessionStrength" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ParameterAnalysis" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="SSLStrength" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Compliance" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ServerConfiguration" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Reflection" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="MaliciousScript" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="MaliciousIFrame" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Advanced" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="SSLLinksOnly" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="RandomizeModules" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="SingleFibered" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="LogFile" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="MoreLogFile" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="DumpTrafficToUI" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ReportXmlOnly" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="FingerprintWebsites" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="WantAttackPointResults" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="SaveInDatabase" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="GenerateReportAnalysis" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="GenerateReportAnalysisOnly" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="WindowsLogCriticalErrors" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="StayOnPort" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="UniqueUrlsAcrossWebSites" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="UseSystemDSN" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="GenerateParamVulnsSummaryXml" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="GenerateImpervaXml" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="GenerateModSecurity" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="GenerateSnort" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="LogoutDetection" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="PreFlight" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="CookieAnalysis" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="DoNotAttackLoginLinks" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="AutoThrottle" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ImportedTraffic" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ZipReport" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="LogoutDetectionUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LogoutDetectionRegex" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ExecuteCommandUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CommandLine" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AutoSaveFileName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FileName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LogFileName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Protocol" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UserAgent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Accept" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ContentType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AttackFile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AttackFileAugment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UseSslCertification" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="SSLClientSideCertFilename" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SSLClientSideCertPassword" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SSLClientSideCertSubjectName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CustomReportZapFile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ExternalReportConstantsFile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="WebServiceContentType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="URLList" type="{http://ntobjectives.com/webservices/}ArrayOfString" minOccurs="0"/>
 *         &lt;element name="LocalPath" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NetworkPath" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NotifyScanDoneURL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DataBaseUserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DataBasePassWord" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AesEncryptPassword" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MaxRetries" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *         &lt;element name="ReadTimeOut" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *         &lt;element name="ConnectTimeOut" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *         &lt;element name="CloseConnections" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="WaitForServerToCloseSocket" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="UseWinHTTP" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="SessionProbes" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *         &lt;element name="DripDelayMilliseconds" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *         &lt;element name="LogoutDetectionFrequency" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *         &lt;element name="MaxHackAppLimit" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *         &lt;element name="MaxVulnPerModuleLimit" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *         &lt;element name="MaxOutstandingRequests" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *         &lt;element name="MaxBandwidthKB" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *         &lt;element name="StartOfQueryInDirectory" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *         &lt;element name="SnortMode" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *         &lt;element name="IEProxySettings" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Proxy" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ProxyHost" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ProxyIpPort" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *         &lt;element name="ProxyUsername" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ProxyPassword" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ProxyUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ProxyHttpsHost" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ProxyHttpsIpPort" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *         &lt;element name="UserAgentProxy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SafeHosts" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="StartTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EndTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Duration" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ReEstablishSessionAfterThisPauseTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PauseAfterThisTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TrainingConfig" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TrainingConfigLogin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DollarsPerHourAppDev" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="DollarsPerHourServerAdmin" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="SetupHoursAppDev" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="SetupHoursServerAdmin" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="MinHoursPerIssueAppDev" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="MaxHoursPerIssueAppDev" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="MinHoursPerIssueServerAdmin" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="MaxHoursPerIssueServerAdmin" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="AttackLinkBlacklist" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SPIDERCONFIG", propOrder = {
    "name",
    "cookies",
    "exHeader",
    "userDoNotAttackParameters",
    "benchHost",
    "sqlInjection",
    "blindSqlInjection",
    "rfi",
    "fileUpload",
    "httpResponeSplitting",
    "cmdInjection",
    "sCodeDisclosure",
    "webServiceAnalysis",
    "activeXAnalysis",
    "resourceFinder",
    "resourceFinderNikto",
    "directoryBrowse",
    "authTest",
    "xss",
    "reverseProxy",
    "traceDetection",
    "javaGrinder",
    "sessionStrength",
    "parameterAnalysis",
    "sslStrength",
    "compliance",
    "serverConfiguration",
    "reflection",
    "maliciousScript",
    "maliciousIFrame",
    "advanced",
    "sslLinksOnly",
    "randomizeModules",
    "singleFibered",
    "logFile",
    "moreLogFile",
    "dumpTrafficToUI",
    "reportXmlOnly",
    "fingerprintWebsites",
    "wantAttackPointResults",
    "saveInDatabase",
    "generateReportAnalysis",
    "generateReportAnalysisOnly",
    "windowsLogCriticalErrors",
    "stayOnPort",
    "uniqueUrlsAcrossWebSites",
    "useSystemDSN",
    "generateParamVulnsSummaryXml",
    "generateImpervaXml",
    "generateModSecurity",
    "generateSnort",
    "logoutDetection",
    "preFlight",
    "cookieAnalysis",
    "doNotAttackLoginLinks",
    "autoThrottle",
    "importedTraffic",
    "zipReport",
    "logoutDetectionUrl",
    "logoutDetectionRegex",
    "executeCommandUrl",
    "commandLine",
    "autoSaveFileName",
    "fileName",
    "logFileName",
    "protocol",
    "userAgent",
    "accept",
    "contentType",
    "attackFile",
    "attackFileAugment",
    "useSslCertification",
    "sslClientSideCertFilename",
    "sslClientSideCertPassword",
    "sslClientSideCertSubjectName",
    "customReportZapFile",
    "externalReportConstantsFile",
    "webServiceContentType",
    "urlList",
    "localPath",
    "networkPath",
    "notifyScanDoneURL",
    "dataBaseUserName",
    "dataBasePassWord",
    "aesEncryptPassword",
    "maxRetries",
    "readTimeOut",
    "connectTimeOut",
    "closeConnections",
    "waitForServerToCloseSocket",
    "useWinHTTP",
    "sessionProbes",
    "dripDelayMilliseconds",
    "logoutDetectionFrequency",
    "maxHackAppLimit",
    "maxVulnPerModuleLimit",
    "maxOutstandingRequests",
    "maxBandwidthKB",
    "startOfQueryInDirectory",
    "snortMode",
    "ieProxySettings",
    "proxy",
    "proxyHost",
    "proxyIpPort",
    "proxyUsername",
    "proxyPassword",
    "proxyUrl",
    "proxyHttpsHost",
    "proxyHttpsIpPort",
    "userAgentProxy",
    "safeHosts",
    "startTime",
    "endTime",
    "duration",
    "reEstablishSessionAfterThisPauseTime",
    "pauseAfterThisTime",
    "trainingConfig",
    "trainingConfigLogin",
    "dollarsPerHourAppDev",
    "dollarsPerHourServerAdmin",
    "setupHoursAppDev",
    "setupHoursServerAdmin",
    "minHoursPerIssueAppDev",
    "maxHoursPerIssueAppDev",
    "minHoursPerIssueServerAdmin",
    "maxHoursPerIssueServerAdmin",
    "attackLinkBlacklist"
})
public class SPIDERCONFIG {

    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "Cookies")
    protected String cookies;
    @XmlElement(name = "ExHeader")
    protected String exHeader;
    @XmlElement(name = "UserDoNotAttackParameters")
    protected String userDoNotAttackParameters;
    @XmlElement(name = "BenchHost")
    protected String benchHost;
    @XmlElement(name = "SqlInjection")
    protected boolean sqlInjection;
    @XmlElement(name = "BlindSqlInjection")
    protected boolean blindSqlInjection;
    @XmlElement(name = "RFI")
    protected boolean rfi;
    @XmlElement(name = "FileUpload")
    protected boolean fileUpload;
    @XmlElement(name = "HttpResponeSplitting")
    protected boolean httpResponeSplitting;
    @XmlElement(name = "CmdInjection")
    protected boolean cmdInjection;
    @XmlElement(name = "SCodeDisclosure")
    protected boolean sCodeDisclosure;
    @XmlElement(name = "WebServiceAnalysis")
    protected boolean webServiceAnalysis;
    @XmlElement(name = "ActiveXAnalysis")
    protected boolean activeXAnalysis;
    @XmlElement(name = "ResourceFinder")
    protected boolean resourceFinder;
    @XmlElement(name = "ResourceFinderNikto")
    protected boolean resourceFinderNikto;
    @XmlElement(name = "DirectoryBrowse")
    protected boolean directoryBrowse;
    @XmlElement(name = "AuthTest")
    protected boolean authTest;
    @XmlElement(name = "XSS")
    protected boolean xss;
    @XmlElement(name = "ReverseProxy")
    protected boolean reverseProxy;
    @XmlElement(name = "TraceDetection")
    protected boolean traceDetection;
    @XmlElement(name = "JavaGrinder")
    protected boolean javaGrinder;
    @XmlElement(name = "SessionStrength")
    protected boolean sessionStrength;
    @XmlElement(name = "ParameterAnalysis")
    protected boolean parameterAnalysis;
    @XmlElement(name = "SSLStrength")
    protected boolean sslStrength;
    @XmlElement(name = "Compliance")
    protected boolean compliance;
    @XmlElement(name = "ServerConfiguration")
    protected boolean serverConfiguration;
    @XmlElement(name = "Reflection")
    protected boolean reflection;
    @XmlElement(name = "MaliciousScript")
    protected boolean maliciousScript;
    @XmlElement(name = "MaliciousIFrame")
    protected boolean maliciousIFrame;
    @XmlElement(name = "Advanced")
    protected boolean advanced;
    @XmlElement(name = "SSLLinksOnly")
    protected boolean sslLinksOnly;
    @XmlElement(name = "RandomizeModules")
    protected boolean randomizeModules;
    @XmlElement(name = "SingleFibered")
    protected boolean singleFibered;
    @XmlElement(name = "LogFile")
    protected boolean logFile;
    @XmlElement(name = "MoreLogFile")
    protected boolean moreLogFile;
    @XmlElement(name = "DumpTrafficToUI")
    protected boolean dumpTrafficToUI;
    @XmlElement(name = "ReportXmlOnly")
    protected boolean reportXmlOnly;
    @XmlElement(name = "FingerprintWebsites")
    protected boolean fingerprintWebsites;
    @XmlElement(name = "WantAttackPointResults")
    protected boolean wantAttackPointResults;
    @XmlElement(name = "SaveInDatabase")
    protected boolean saveInDatabase;
    @XmlElement(name = "GenerateReportAnalysis")
    protected boolean generateReportAnalysis;
    @XmlElement(name = "GenerateReportAnalysisOnly")
    protected boolean generateReportAnalysisOnly;
    @XmlElement(name = "WindowsLogCriticalErrors")
    protected boolean windowsLogCriticalErrors;
    @XmlElement(name = "StayOnPort")
    protected boolean stayOnPort;
    @XmlElement(name = "UniqueUrlsAcrossWebSites")
    protected boolean uniqueUrlsAcrossWebSites;
    @XmlElement(name = "UseSystemDSN")
    protected boolean useSystemDSN;
    @XmlElement(name = "GenerateParamVulnsSummaryXml")
    protected boolean generateParamVulnsSummaryXml;
    @XmlElement(name = "GenerateImpervaXml")
    protected boolean generateImpervaXml;
    @XmlElement(name = "GenerateModSecurity")
    protected boolean generateModSecurity;
    @XmlElement(name = "GenerateSnort")
    protected boolean generateSnort;
    @XmlElement(name = "LogoutDetection")
    protected boolean logoutDetection;
    @XmlElement(name = "PreFlight")
    protected boolean preFlight;
    @XmlElement(name = "CookieAnalysis")
    protected boolean cookieAnalysis;
    @XmlElement(name = "DoNotAttackLoginLinks")
    protected boolean doNotAttackLoginLinks;
    @XmlElement(name = "AutoThrottle")
    protected boolean autoThrottle;
    @XmlElement(name = "ImportedTraffic")
    protected boolean importedTraffic;
    @XmlElement(name = "ZipReport")
    protected boolean zipReport;
    @XmlElement(name = "LogoutDetectionUrl")
    protected String logoutDetectionUrl;
    @XmlElement(name = "LogoutDetectionRegex")
    protected String logoutDetectionRegex;
    @XmlElement(name = "ExecuteCommandUrl")
    protected String executeCommandUrl;
    @XmlElement(name = "CommandLine")
    protected String commandLine;
    @XmlElement(name = "AutoSaveFileName")
    protected String autoSaveFileName;
    @XmlElement(name = "FileName")
    protected String fileName;
    @XmlElement(name = "LogFileName")
    protected String logFileName;
    @XmlElement(name = "Protocol")
    protected String protocol;
    @XmlElement(name = "UserAgent")
    protected String userAgent;
    @XmlElement(name = "Accept")
    protected String accept;
    @XmlElement(name = "ContentType")
    protected String contentType;
    @XmlElement(name = "AttackFile")
    protected String attackFile;
    @XmlElement(name = "AttackFileAugment")
    protected String attackFileAugment;
    @XmlElement(name = "UseSslCertification")
    protected boolean useSslCertification;
    @XmlElement(name = "SSLClientSideCertFilename")
    protected String sslClientSideCertFilename;
    @XmlElement(name = "SSLClientSideCertPassword")
    protected String sslClientSideCertPassword;
    @XmlElement(name = "SSLClientSideCertSubjectName")
    protected String sslClientSideCertSubjectName;
    @XmlElement(name = "CustomReportZapFile")
    protected String customReportZapFile;
    @XmlElement(name = "ExternalReportConstantsFile")
    protected String externalReportConstantsFile;
    @XmlElement(name = "WebServiceContentType")
    protected String webServiceContentType;
    @XmlElement(name = "URLList")
    protected ArrayOfString urlList;
    @XmlElement(name = "LocalPath")
    protected String localPath;
    @XmlElement(name = "NetworkPath")
    protected String networkPath;
    @XmlElement(name = "NotifyScanDoneURL")
    protected String notifyScanDoneURL;
    @XmlElement(name = "DataBaseUserName")
    protected String dataBaseUserName;
    @XmlElement(name = "DataBasePassWord")
    protected String dataBasePassWord;
    @XmlElement(name = "AesEncryptPassword")
    protected String aesEncryptPassword;
    @XmlElement(name = "MaxRetries")
    @XmlSchemaType(name = "unsignedInt")
    protected long maxRetries;
    @XmlElement(name = "ReadTimeOut")
    @XmlSchemaType(name = "unsignedInt")
    protected long readTimeOut;
    @XmlElement(name = "ConnectTimeOut")
    @XmlSchemaType(name = "unsignedInt")
    protected long connectTimeOut;
    @XmlElement(name = "CloseConnections")
    protected boolean closeConnections;
    @XmlElement(name = "WaitForServerToCloseSocket")
    protected boolean waitForServerToCloseSocket;
    @XmlElement(name = "UseWinHTTP")
    protected boolean useWinHTTP;
    @XmlElement(name = "SessionProbes")
    @XmlSchemaType(name = "unsignedInt")
    protected long sessionProbes;
    @XmlElement(name = "DripDelayMilliseconds")
    @XmlSchemaType(name = "unsignedInt")
    protected long dripDelayMilliseconds;
    @XmlElement(name = "LogoutDetectionFrequency")
    @XmlSchemaType(name = "unsignedInt")
    protected long logoutDetectionFrequency;
    @XmlElement(name = "MaxHackAppLimit")
    @XmlSchemaType(name = "unsignedInt")
    protected long maxHackAppLimit;
    @XmlElement(name = "MaxVulnPerModuleLimit")
    @XmlSchemaType(name = "unsignedInt")
    protected long maxVulnPerModuleLimit;
    @XmlElement(name = "MaxOutstandingRequests")
    @XmlSchemaType(name = "unsignedInt")
    protected long maxOutstandingRequests;
    @XmlElement(name = "MaxBandwidthKB")
    @XmlSchemaType(name = "unsignedInt")
    protected long maxBandwidthKB;
    @XmlElement(name = "StartOfQueryInDirectory")
    @XmlSchemaType(name = "unsignedInt")
    protected long startOfQueryInDirectory;
    @XmlElement(name = "SnortMode")
    @XmlSchemaType(name = "unsignedInt")
    protected long snortMode;
    @XmlElement(name = "IEProxySettings")
    protected boolean ieProxySettings;
    @XmlElement(name = "Proxy")
    protected boolean proxy;
    @XmlElement(name = "ProxyHost")
    protected String proxyHost;
    @XmlElement(name = "ProxyIpPort")
    @XmlSchemaType(name = "unsignedInt")
    protected long proxyIpPort;
    @XmlElement(name = "ProxyUsername")
    protected String proxyUsername;
    @XmlElement(name = "ProxyPassword")
    protected String proxyPassword;
    @XmlElement(name = "ProxyUrl")
    protected String proxyUrl;
    @XmlElement(name = "ProxyHttpsHost")
    protected String proxyHttpsHost;
    @XmlElement(name = "ProxyHttpsIpPort")
    @XmlSchemaType(name = "unsignedInt")
    protected long proxyHttpsIpPort;
    @XmlElement(name = "UserAgentProxy")
    protected String userAgentProxy;
    @XmlElement(name = "SafeHosts")
    protected String safeHosts;
    @XmlElement(name = "StartTime")
    protected String startTime;
    @XmlElement(name = "EndTime")
    protected String endTime;
    @XmlElement(name = "Duration")
    protected String duration;
    @XmlElement(name = "ReEstablishSessionAfterThisPauseTime")
    protected String reEstablishSessionAfterThisPauseTime;
    @XmlElement(name = "PauseAfterThisTime")
    protected String pauseAfterThisTime;
    @XmlElement(name = "TrainingConfig")
    protected String trainingConfig;
    @XmlElement(name = "TrainingConfigLogin")
    protected String trainingConfigLogin;
    @XmlElement(name = "DollarsPerHourAppDev")
    protected double dollarsPerHourAppDev;
    @XmlElement(name = "DollarsPerHourServerAdmin")
    protected double dollarsPerHourServerAdmin;
    @XmlElement(name = "SetupHoursAppDev")
    protected double setupHoursAppDev;
    @XmlElement(name = "SetupHoursServerAdmin")
    protected double setupHoursServerAdmin;
    @XmlElement(name = "MinHoursPerIssueAppDev")
    protected double minHoursPerIssueAppDev;
    @XmlElement(name = "MaxHoursPerIssueAppDev")
    protected double maxHoursPerIssueAppDev;
    @XmlElement(name = "MinHoursPerIssueServerAdmin")
    protected double minHoursPerIssueServerAdmin;
    @XmlElement(name = "MaxHoursPerIssueServerAdmin")
    protected double maxHoursPerIssueServerAdmin;
    @XmlElement(name = "AttackLinkBlacklist")
    protected String attackLinkBlacklist;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the cookies property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCookies() {
        return cookies;
    }

    /**
     * Sets the value of the cookies property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCookies(String value) {
        this.cookies = value;
    }

    /**
     * Gets the value of the exHeader property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExHeader() {
        return exHeader;
    }

    /**
     * Sets the value of the exHeader property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExHeader(String value) {
        this.exHeader = value;
    }

    /**
     * Gets the value of the userDoNotAttackParameters property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserDoNotAttackParameters() {
        return userDoNotAttackParameters;
    }

    /**
     * Sets the value of the userDoNotAttackParameters property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserDoNotAttackParameters(String value) {
        this.userDoNotAttackParameters = value;
    }

    /**
     * Gets the value of the benchHost property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBenchHost() {
        return benchHost;
    }

    /**
     * Sets the value of the benchHost property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBenchHost(String value) {
        this.benchHost = value;
    }

    /**
     * Gets the value of the sqlInjection property.
     * 
     */
    public boolean isSqlInjection() {
        return sqlInjection;
    }

    /**
     * Sets the value of the sqlInjection property.
     * 
     */
    public void setSqlInjection(boolean value) {
        this.sqlInjection = value;
    }

    /**
     * Gets the value of the blindSqlInjection property.
     * 
     */
    public boolean isBlindSqlInjection() {
        return blindSqlInjection;
    }

    /**
     * Sets the value of the blindSqlInjection property.
     * 
     */
    public void setBlindSqlInjection(boolean value) {
        this.blindSqlInjection = value;
    }

    /**
     * Gets the value of the rfi property.
     * 
     */
    public boolean isRFI() {
        return rfi;
    }

    /**
     * Sets the value of the rfi property.
     * 
     */
    public void setRFI(boolean value) {
        this.rfi = value;
    }

    /**
     * Gets the value of the fileUpload property.
     * 
     */
    public boolean isFileUpload() {
        return fileUpload;
    }

    /**
     * Sets the value of the fileUpload property.
     * 
     */
    public void setFileUpload(boolean value) {
        this.fileUpload = value;
    }

    /**
     * Gets the value of the httpResponeSplitting property.
     * 
     */
    public boolean isHttpResponeSplitting() {
        return httpResponeSplitting;
    }

    /**
     * Sets the value of the httpResponeSplitting property.
     * 
     */
    public void setHttpResponeSplitting(boolean value) {
        this.httpResponeSplitting = value;
    }

    /**
     * Gets the value of the cmdInjection property.
     * 
     */
    public boolean isCmdInjection() {
        return cmdInjection;
    }

    /**
     * Sets the value of the cmdInjection property.
     * 
     */
    public void setCmdInjection(boolean value) {
        this.cmdInjection = value;
    }

    /**
     * Gets the value of the sCodeDisclosure property.
     * 
     */
    public boolean isSCodeDisclosure() {
        return sCodeDisclosure;
    }

    /**
     * Sets the value of the sCodeDisclosure property.
     * 
     */
    public void setSCodeDisclosure(boolean value) {
        this.sCodeDisclosure = value;
    }

    /**
     * Gets the value of the webServiceAnalysis property.
     * 
     */
    public boolean isWebServiceAnalysis() {
        return webServiceAnalysis;
    }

    /**
     * Sets the value of the webServiceAnalysis property.
     * 
     */
    public void setWebServiceAnalysis(boolean value) {
        this.webServiceAnalysis = value;
    }

    /**
     * Gets the value of the activeXAnalysis property.
     * 
     */
    public boolean isActiveXAnalysis() {
        return activeXAnalysis;
    }

    /**
     * Sets the value of the activeXAnalysis property.
     * 
     */
    public void setActiveXAnalysis(boolean value) {
        this.activeXAnalysis = value;
    }

    /**
     * Gets the value of the resourceFinder property.
     * 
     */
    public boolean isResourceFinder() {
        return resourceFinder;
    }

    /**
     * Sets the value of the resourceFinder property.
     * 
     */
    public void setResourceFinder(boolean value) {
        this.resourceFinder = value;
    }

    /**
     * Gets the value of the resourceFinderNikto property.
     * 
     */
    public boolean isResourceFinderNikto() {
        return resourceFinderNikto;
    }

    /**
     * Sets the value of the resourceFinderNikto property.
     * 
     */
    public void setResourceFinderNikto(boolean value) {
        this.resourceFinderNikto = value;
    }

    /**
     * Gets the value of the directoryBrowse property.
     * 
     */
    public boolean isDirectoryBrowse() {
        return directoryBrowse;
    }

    /**
     * Sets the value of the directoryBrowse property.
     * 
     */
    public void setDirectoryBrowse(boolean value) {
        this.directoryBrowse = value;
    }

    /**
     * Gets the value of the authTest property.
     * 
     */
    public boolean isAuthTest() {
        return authTest;
    }

    /**
     * Sets the value of the authTest property.
     * 
     */
    public void setAuthTest(boolean value) {
        this.authTest = value;
    }

    /**
     * Gets the value of the xss property.
     * 
     */
    public boolean isXSS() {
        return xss;
    }

    /**
     * Sets the value of the xss property.
     * 
     */
    public void setXSS(boolean value) {
        this.xss = value;
    }

    /**
     * Gets the value of the reverseProxy property.
     * 
     */
    public boolean isReverseProxy() {
        return reverseProxy;
    }

    /**
     * Sets the value of the reverseProxy property.
     * 
     */
    public void setReverseProxy(boolean value) {
        this.reverseProxy = value;
    }

    /**
     * Gets the value of the traceDetection property.
     * 
     */
    public boolean isTraceDetection() {
        return traceDetection;
    }

    /**
     * Sets the value of the traceDetection property.
     * 
     */
    public void setTraceDetection(boolean value) {
        this.traceDetection = value;
    }

    /**
     * Gets the value of the javaGrinder property.
     * 
     */
    public boolean isJavaGrinder() {
        return javaGrinder;
    }

    /**
     * Sets the value of the javaGrinder property.
     * 
     */
    public void setJavaGrinder(boolean value) {
        this.javaGrinder = value;
    }

    /**
     * Gets the value of the sessionStrength property.
     * 
     */
    public boolean isSessionStrength() {
        return sessionStrength;
    }

    /**
     * Sets the value of the sessionStrength property.
     * 
     */
    public void setSessionStrength(boolean value) {
        this.sessionStrength = value;
    }

    /**
     * Gets the value of the parameterAnalysis property.
     * 
     */
    public boolean isParameterAnalysis() {
        return parameterAnalysis;
    }

    /**
     * Sets the value of the parameterAnalysis property.
     * 
     */
    public void setParameterAnalysis(boolean value) {
        this.parameterAnalysis = value;
    }

    /**
     * Gets the value of the sslStrength property.
     * 
     */
    public boolean isSSLStrength() {
        return sslStrength;
    }

    /**
     * Sets the value of the sslStrength property.
     * 
     */
    public void setSSLStrength(boolean value) {
        this.sslStrength = value;
    }

    /**
     * Gets the value of the compliance property.
     * 
     */
    public boolean isCompliance() {
        return compliance;
    }

    /**
     * Sets the value of the compliance property.
     * 
     */
    public void setCompliance(boolean value) {
        this.compliance = value;
    }

    /**
     * Gets the value of the serverConfiguration property.
     * 
     */
    public boolean isServerConfiguration() {
        return serverConfiguration;
    }

    /**
     * Sets the value of the serverConfiguration property.
     * 
     */
    public void setServerConfiguration(boolean value) {
        this.serverConfiguration = value;
    }

    /**
     * Gets the value of the reflection property.
     * 
     */
    public boolean isReflection() {
        return reflection;
    }

    /**
     * Sets the value of the reflection property.
     * 
     */
    public void setReflection(boolean value) {
        this.reflection = value;
    }

    /**
     * Gets the value of the maliciousScript property.
     * 
     */
    public boolean isMaliciousScript() {
        return maliciousScript;
    }

    /**
     * Sets the value of the maliciousScript property.
     * 
     */
    public void setMaliciousScript(boolean value) {
        this.maliciousScript = value;
    }

    /**
     * Gets the value of the maliciousIFrame property.
     * 
     */
    public boolean isMaliciousIFrame() {
        return maliciousIFrame;
    }

    /**
     * Sets the value of the maliciousIFrame property.
     * 
     */
    public void setMaliciousIFrame(boolean value) {
        this.maliciousIFrame = value;
    }

    /**
     * Gets the value of the advanced property.
     * 
     */
    public boolean isAdvanced() {
        return advanced;
    }

    /**
     * Sets the value of the advanced property.
     * 
     */
    public void setAdvanced(boolean value) {
        this.advanced = value;
    }

    /**
     * Gets the value of the sslLinksOnly property.
     * 
     */
    public boolean isSSLLinksOnly() {
        return sslLinksOnly;
    }

    /**
     * Sets the value of the sslLinksOnly property.
     * 
     */
    public void setSSLLinksOnly(boolean value) {
        this.sslLinksOnly = value;
    }

    /**
     * Gets the value of the randomizeModules property.
     * 
     */
    public boolean isRandomizeModules() {
        return randomizeModules;
    }

    /**
     * Sets the value of the randomizeModules property.
     * 
     */
    public void setRandomizeModules(boolean value) {
        this.randomizeModules = value;
    }

    /**
     * Gets the value of the singleFibered property.
     * 
     */
    public boolean isSingleFibered() {
        return singleFibered;
    }

    /**
     * Sets the value of the singleFibered property.
     * 
     */
    public void setSingleFibered(boolean value) {
        this.singleFibered = value;
    }

    /**
     * Gets the value of the logFile property.
     * 
     */
    public boolean isLogFile() {
        return logFile;
    }

    /**
     * Sets the value of the logFile property.
     * 
     */
    public void setLogFile(boolean value) {
        this.logFile = value;
    }

    /**
     * Gets the value of the moreLogFile property.
     * 
     */
    public boolean isMoreLogFile() {
        return moreLogFile;
    }

    /**
     * Sets the value of the moreLogFile property.
     * 
     */
    public void setMoreLogFile(boolean value) {
        this.moreLogFile = value;
    }

    /**
     * Gets the value of the dumpTrafficToUI property.
     * 
     */
    public boolean isDumpTrafficToUI() {
        return dumpTrafficToUI;
    }

    /**
     * Sets the value of the dumpTrafficToUI property.
     * 
     */
    public void setDumpTrafficToUI(boolean value) {
        this.dumpTrafficToUI = value;
    }

    /**
     * Gets the value of the reportXmlOnly property.
     * 
     */
    public boolean isReportXmlOnly() {
        return reportXmlOnly;
    }

    /**
     * Sets the value of the reportXmlOnly property.
     * 
     */
    public void setReportXmlOnly(boolean value) {
        this.reportXmlOnly = value;
    }

    /**
     * Gets the value of the fingerprintWebsites property.
     * 
     */
    public boolean isFingerprintWebsites() {
        return fingerprintWebsites;
    }

    /**
     * Sets the value of the fingerprintWebsites property.
     * 
     */
    public void setFingerprintWebsites(boolean value) {
        this.fingerprintWebsites = value;
    }

    /**
     * Gets the value of the wantAttackPointResults property.
     * 
     */
    public boolean isWantAttackPointResults() {
        return wantAttackPointResults;
    }

    /**
     * Sets the value of the wantAttackPointResults property.
     * 
     */
    public void setWantAttackPointResults(boolean value) {
        this.wantAttackPointResults = value;
    }

    /**
     * Gets the value of the saveInDatabase property.
     * 
     */
    public boolean isSaveInDatabase() {
        return saveInDatabase;
    }

    /**
     * Sets the value of the saveInDatabase property.
     * 
     */
    public void setSaveInDatabase(boolean value) {
        this.saveInDatabase = value;
    }

    /**
     * Gets the value of the generateReportAnalysis property.
     * 
     */
    public boolean isGenerateReportAnalysis() {
        return generateReportAnalysis;
    }

    /**
     * Sets the value of the generateReportAnalysis property.
     * 
     */
    public void setGenerateReportAnalysis(boolean value) {
        this.generateReportAnalysis = value;
    }

    /**
     * Gets the value of the generateReportAnalysisOnly property.
     * 
     */
    public boolean isGenerateReportAnalysisOnly() {
        return generateReportAnalysisOnly;
    }

    /**
     * Sets the value of the generateReportAnalysisOnly property.
     * 
     */
    public void setGenerateReportAnalysisOnly(boolean value) {
        this.generateReportAnalysisOnly = value;
    }

    /**
     * Gets the value of the windowsLogCriticalErrors property.
     * 
     */
    public boolean isWindowsLogCriticalErrors() {
        return windowsLogCriticalErrors;
    }

    /**
     * Sets the value of the windowsLogCriticalErrors property.
     * 
     */
    public void setWindowsLogCriticalErrors(boolean value) {
        this.windowsLogCriticalErrors = value;
    }

    /**
     * Gets the value of the stayOnPort property.
     * 
     */
    public boolean isStayOnPort() {
        return stayOnPort;
    }

    /**
     * Sets the value of the stayOnPort property.
     * 
     */
    public void setStayOnPort(boolean value) {
        this.stayOnPort = value;
    }

    /**
     * Gets the value of the uniqueUrlsAcrossWebSites property.
     * 
     */
    public boolean isUniqueUrlsAcrossWebSites() {
        return uniqueUrlsAcrossWebSites;
    }

    /**
     * Sets the value of the uniqueUrlsAcrossWebSites property.
     * 
     */
    public void setUniqueUrlsAcrossWebSites(boolean value) {
        this.uniqueUrlsAcrossWebSites = value;
    }

    /**
     * Gets the value of the useSystemDSN property.
     * 
     */
    public boolean isUseSystemDSN() {
        return useSystemDSN;
    }

    /**
     * Sets the value of the useSystemDSN property.
     * 
     */
    public void setUseSystemDSN(boolean value) {
        this.useSystemDSN = value;
    }

    /**
     * Gets the value of the generateParamVulnsSummaryXml property.
     * 
     */
    public boolean isGenerateParamVulnsSummaryXml() {
        return generateParamVulnsSummaryXml;
    }

    /**
     * Sets the value of the generateParamVulnsSummaryXml property.
     * 
     */
    public void setGenerateParamVulnsSummaryXml(boolean value) {
        this.generateParamVulnsSummaryXml = value;
    }

    /**
     * Gets the value of the generateImpervaXml property.
     * 
     */
    public boolean isGenerateImpervaXml() {
        return generateImpervaXml;
    }

    /**
     * Sets the value of the generateImpervaXml property.
     * 
     */
    public void setGenerateImpervaXml(boolean value) {
        this.generateImpervaXml = value;
    }

    /**
     * Gets the value of the generateModSecurity property.
     * 
     */
    public boolean isGenerateModSecurity() {
        return generateModSecurity;
    }

    /**
     * Sets the value of the generateModSecurity property.
     * 
     */
    public void setGenerateModSecurity(boolean value) {
        this.generateModSecurity = value;
    }

    /**
     * Gets the value of the generateSnort property.
     * 
     */
    public boolean isGenerateSnort() {
        return generateSnort;
    }

    /**
     * Sets the value of the generateSnort property.
     * 
     */
    public void setGenerateSnort(boolean value) {
        this.generateSnort = value;
    }

    /**
     * Gets the value of the logoutDetection property.
     * 
     */
    public boolean isLogoutDetection() {
        return logoutDetection;
    }

    /**
     * Sets the value of the logoutDetection property.
     * 
     */
    public void setLogoutDetection(boolean value) {
        this.logoutDetection = value;
    }

    /**
     * Gets the value of the preFlight property.
     * 
     */
    public boolean isPreFlight() {
        return preFlight;
    }

    /**
     * Sets the value of the preFlight property.
     * 
     */
    public void setPreFlight(boolean value) {
        this.preFlight = value;
    }

    /**
     * Gets the value of the cookieAnalysis property.
     * 
     */
    public boolean isCookieAnalysis() {
        return cookieAnalysis;
    }

    /**
     * Sets the value of the cookieAnalysis property.
     * 
     */
    public void setCookieAnalysis(boolean value) {
        this.cookieAnalysis = value;
    }

    /**
     * Gets the value of the doNotAttackLoginLinks property.
     * 
     */
    public boolean isDoNotAttackLoginLinks() {
        return doNotAttackLoginLinks;
    }

    /**
     * Sets the value of the doNotAttackLoginLinks property.
     * 
     */
    public void setDoNotAttackLoginLinks(boolean value) {
        this.doNotAttackLoginLinks = value;
    }

    /**
     * Gets the value of the autoThrottle property.
     * 
     */
    public boolean isAutoThrottle() {
        return autoThrottle;
    }

    /**
     * Sets the value of the autoThrottle property.
     * 
     */
    public void setAutoThrottle(boolean value) {
        this.autoThrottle = value;
    }

    /**
     * Gets the value of the importedTraffic property.
     * 
     */
    public boolean isImportedTraffic() {
        return importedTraffic;
    }

    /**
     * Sets the value of the importedTraffic property.
     * 
     */
    public void setImportedTraffic(boolean value) {
        this.importedTraffic = value;
    }

    /**
     * Gets the value of the zipReport property.
     * 
     */
    public boolean isZipReport() {
        return zipReport;
    }

    /**
     * Sets the value of the zipReport property.
     * 
     */
    public void setZipReport(boolean value) {
        this.zipReport = value;
    }

    /**
     * Gets the value of the logoutDetectionUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogoutDetectionUrl() {
        return logoutDetectionUrl;
    }

    /**
     * Sets the value of the logoutDetectionUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogoutDetectionUrl(String value) {
        this.logoutDetectionUrl = value;
    }

    /**
     * Gets the value of the logoutDetectionRegex property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogoutDetectionRegex() {
        return logoutDetectionRegex;
    }

    /**
     * Sets the value of the logoutDetectionRegex property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogoutDetectionRegex(String value) {
        this.logoutDetectionRegex = value;
    }

    /**
     * Gets the value of the executeCommandUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExecuteCommandUrl() {
        return executeCommandUrl;
    }

    /**
     * Sets the value of the executeCommandUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExecuteCommandUrl(String value) {
        this.executeCommandUrl = value;
    }

    /**
     * Gets the value of the commandLine property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommandLine() {
        return commandLine;
    }

    /**
     * Sets the value of the commandLine property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommandLine(String value) {
        this.commandLine = value;
    }

    /**
     * Gets the value of the autoSaveFileName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAutoSaveFileName() {
        return autoSaveFileName;
    }

    /**
     * Sets the value of the autoSaveFileName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAutoSaveFileName(String value) {
        this.autoSaveFileName = value;
    }

    /**
     * Gets the value of the fileName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Sets the value of the fileName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileName(String value) {
        this.fileName = value;
    }

    /**
     * Gets the value of the logFileName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogFileName() {
        return logFileName;
    }

    /**
     * Sets the value of the logFileName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogFileName(String value) {
        this.logFileName = value;
    }

    /**
     * Gets the value of the protocol property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     * Sets the value of the protocol property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProtocol(String value) {
        this.protocol = value;
    }

    /**
     * Gets the value of the userAgent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * Sets the value of the userAgent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserAgent(String value) {
        this.userAgent = value;
    }

    /**
     * Gets the value of the accept property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccept() {
        return accept;
    }

    /**
     * Sets the value of the accept property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccept(String value) {
        this.accept = value;
    }

    /**
     * Gets the value of the contentType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * Sets the value of the contentType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContentType(String value) {
        this.contentType = value;
    }

    /**
     * Gets the value of the attackFile property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttackFile() {
        return attackFile;
    }

    /**
     * Sets the value of the attackFile property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttackFile(String value) {
        this.attackFile = value;
    }

    /**
     * Gets the value of the attackFileAugment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttackFileAugment() {
        return attackFileAugment;
    }

    /**
     * Sets the value of the attackFileAugment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttackFileAugment(String value) {
        this.attackFileAugment = value;
    }

    /**
     * Gets the value of the useSslCertification property.
     * 
     */
    public boolean isUseSslCertification() {
        return useSslCertification;
    }

    /**
     * Sets the value of the useSslCertification property.
     * 
     */
    public void setUseSslCertification(boolean value) {
        this.useSslCertification = value;
    }

    /**
     * Gets the value of the sslClientSideCertFilename property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSSLClientSideCertFilename() {
        return sslClientSideCertFilename;
    }

    /**
     * Sets the value of the sslClientSideCertFilename property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSSLClientSideCertFilename(String value) {
        this.sslClientSideCertFilename = value;
    }

    /**
     * Gets the value of the sslClientSideCertPassword property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSSLClientSideCertPassword() {
        return sslClientSideCertPassword;
    }

    /**
     * Sets the value of the sslClientSideCertPassword property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSSLClientSideCertPassword(String value) {
        this.sslClientSideCertPassword = value;
    }

    /**
     * Gets the value of the sslClientSideCertSubjectName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSSLClientSideCertSubjectName() {
        return sslClientSideCertSubjectName;
    }

    /**
     * Sets the value of the sslClientSideCertSubjectName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSSLClientSideCertSubjectName(String value) {
        this.sslClientSideCertSubjectName = value;
    }

    /**
     * Gets the value of the customReportZapFile property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomReportZapFile() {
        return customReportZapFile;
    }

    /**
     * Sets the value of the customReportZapFile property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomReportZapFile(String value) {
        this.customReportZapFile = value;
    }

    /**
     * Gets the value of the externalReportConstantsFile property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalReportConstantsFile() {
        return externalReportConstantsFile;
    }

    /**
     * Sets the value of the externalReportConstantsFile property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalReportConstantsFile(String value) {
        this.externalReportConstantsFile = value;
    }

    /**
     * Gets the value of the webServiceContentType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWebServiceContentType() {
        return webServiceContentType;
    }

    /**
     * Sets the value of the webServiceContentType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWebServiceContentType(String value) {
        this.webServiceContentType = value;
    }

    /**
     * Gets the value of the urlList property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getURLList() {
        return urlList;
    }

    /**
     * Sets the value of the urlList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setURLList(ArrayOfString value) {
        this.urlList = value;
    }

    /**
     * Gets the value of the localPath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocalPath() {
        return localPath;
    }

    /**
     * Sets the value of the localPath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocalPath(String value) {
        this.localPath = value;
    }

    /**
     * Gets the value of the networkPath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNetworkPath() {
        return networkPath;
    }

    /**
     * Sets the value of the networkPath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNetworkPath(String value) {
        this.networkPath = value;
    }

    /**
     * Gets the value of the notifyScanDoneURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotifyScanDoneURL() {
        return notifyScanDoneURL;
    }

    /**
     * Sets the value of the notifyScanDoneURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotifyScanDoneURL(String value) {
        this.notifyScanDoneURL = value;
    }

    /**
     * Gets the value of the dataBaseUserName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataBaseUserName() {
        return dataBaseUserName;
    }

    /**
     * Sets the value of the dataBaseUserName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataBaseUserName(String value) {
        this.dataBaseUserName = value;
    }

    /**
     * Gets the value of the dataBasePassWord property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataBasePassWord() {
        return dataBasePassWord;
    }

    /**
     * Sets the value of the dataBasePassWord property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataBasePassWord(String value) {
        this.dataBasePassWord = value;
    }

    /**
     * Gets the value of the aesEncryptPassword property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAesEncryptPassword() {
        return aesEncryptPassword;
    }

    /**
     * Sets the value of the aesEncryptPassword property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAesEncryptPassword(String value) {
        this.aesEncryptPassword = value;
    }

    /**
     * Gets the value of the maxRetries property.
     * 
     */
    public long getMaxRetries() {
        return maxRetries;
    }

    /**
     * Sets the value of the maxRetries property.
     * 
     */
    public void setMaxRetries(long value) {
        this.maxRetries = value;
    }

    /**
     * Gets the value of the readTimeOut property.
     * 
     */
    public long getReadTimeOut() {
        return readTimeOut;
    }

    /**
     * Sets the value of the readTimeOut property.
     * 
     */
    public void setReadTimeOut(long value) {
        this.readTimeOut = value;
    }

    /**
     * Gets the value of the connectTimeOut property.
     * 
     */
    public long getConnectTimeOut() {
        return connectTimeOut;
    }

    /**
     * Sets the value of the connectTimeOut property.
     * 
     */
    public void setConnectTimeOut(long value) {
        this.connectTimeOut = value;
    }

    /**
     * Gets the value of the closeConnections property.
     * 
     */
    public boolean isCloseConnections() {
        return closeConnections;
    }

    /**
     * Sets the value of the closeConnections property.
     * 
     */
    public void setCloseConnections(boolean value) {
        this.closeConnections = value;
    }

    /**
     * Gets the value of the waitForServerToCloseSocket property.
     * 
     */
    public boolean isWaitForServerToCloseSocket() {
        return waitForServerToCloseSocket;
    }

    /**
     * Sets the value of the waitForServerToCloseSocket property.
     * 
     */
    public void setWaitForServerToCloseSocket(boolean value) {
        this.waitForServerToCloseSocket = value;
    }

    /**
     * Gets the value of the useWinHTTP property.
     * 
     */
    public boolean isUseWinHTTP() {
        return useWinHTTP;
    }

    /**
     * Sets the value of the useWinHTTP property.
     * 
     */
    public void setUseWinHTTP(boolean value) {
        this.useWinHTTP = value;
    }

    /**
     * Gets the value of the sessionProbes property.
     * 
     */
    public long getSessionProbes() {
        return sessionProbes;
    }

    /**
     * Sets the value of the sessionProbes property.
     * 
     */
    public void setSessionProbes(long value) {
        this.sessionProbes = value;
    }

    /**
     * Gets the value of the dripDelayMilliseconds property.
     * 
     */
    public long getDripDelayMilliseconds() {
        return dripDelayMilliseconds;
    }

    /**
     * Sets the value of the dripDelayMilliseconds property.
     * 
     */
    public void setDripDelayMilliseconds(long value) {
        this.dripDelayMilliseconds = value;
    }

    /**
     * Gets the value of the logoutDetectionFrequency property.
     * 
     */
    public long getLogoutDetectionFrequency() {
        return logoutDetectionFrequency;
    }

    /**
     * Sets the value of the logoutDetectionFrequency property.
     * 
     */
    public void setLogoutDetectionFrequency(long value) {
        this.logoutDetectionFrequency = value;
    }

    /**
     * Gets the value of the maxHackAppLimit property.
     * 
     */
    public long getMaxHackAppLimit() {
        return maxHackAppLimit;
    }

    /**
     * Sets the value of the maxHackAppLimit property.
     * 
     */
    public void setMaxHackAppLimit(long value) {
        this.maxHackAppLimit = value;
    }

    /**
     * Gets the value of the maxVulnPerModuleLimit property.
     * 
     */
    public long getMaxVulnPerModuleLimit() {
        return maxVulnPerModuleLimit;
    }

    /**
     * Sets the value of the maxVulnPerModuleLimit property.
     * 
     */
    public void setMaxVulnPerModuleLimit(long value) {
        this.maxVulnPerModuleLimit = value;
    }

    /**
     * Gets the value of the maxOutstandingRequests property.
     * 
     */
    public long getMaxOutstandingRequests() {
        return maxOutstandingRequests;
    }

    /**
     * Sets the value of the maxOutstandingRequests property.
     * 
     */
    public void setMaxOutstandingRequests(long value) {
        this.maxOutstandingRequests = value;
    }

    /**
     * Gets the value of the maxBandwidthKB property.
     * 
     */
    public long getMaxBandwidthKB() {
        return maxBandwidthKB;
    }

    /**
     * Sets the value of the maxBandwidthKB property.
     * 
     */
    public void setMaxBandwidthKB(long value) {
        this.maxBandwidthKB = value;
    }

    /**
     * Gets the value of the startOfQueryInDirectory property.
     * 
     */
    public long getStartOfQueryInDirectory() {
        return startOfQueryInDirectory;
    }

    /**
     * Sets the value of the startOfQueryInDirectory property.
     * 
     */
    public void setStartOfQueryInDirectory(long value) {
        this.startOfQueryInDirectory = value;
    }

    /**
     * Gets the value of the snortMode property.
     * 
     */
    public long getSnortMode() {
        return snortMode;
    }

    /**
     * Sets the value of the snortMode property.
     * 
     */
    public void setSnortMode(long value) {
        this.snortMode = value;
    }

    /**
     * Gets the value of the ieProxySettings property.
     * 
     */
    public boolean isIEProxySettings() {
        return ieProxySettings;
    }

    /**
     * Sets the value of the ieProxySettings property.
     * 
     */
    public void setIEProxySettings(boolean value) {
        this.ieProxySettings = value;
    }

    /**
     * Gets the value of the proxy property.
     * 
     */
    public boolean isProxy() {
        return proxy;
    }

    /**
     * Sets the value of the proxy property.
     * 
     */
    public void setProxy(boolean value) {
        this.proxy = value;
    }

    /**
     * Gets the value of the proxyHost property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProxyHost() {
        return proxyHost;
    }

    /**
     * Sets the value of the proxyHost property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProxyHost(String value) {
        this.proxyHost = value;
    }

    /**
     * Gets the value of the proxyIpPort property.
     * 
     */
    public long getProxyIpPort() {
        return proxyIpPort;
    }

    /**
     * Sets the value of the proxyIpPort property.
     * 
     */
    public void setProxyIpPort(long value) {
        this.proxyIpPort = value;
    }

    /**
     * Gets the value of the proxyUsername property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProxyUsername() {
        return proxyUsername;
    }

    /**
     * Sets the value of the proxyUsername property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProxyUsername(String value) {
        this.proxyUsername = value;
    }

    /**
     * Gets the value of the proxyPassword property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProxyPassword() {
        return proxyPassword;
    }

    /**
     * Sets the value of the proxyPassword property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProxyPassword(String value) {
        this.proxyPassword = value;
    }

    /**
     * Gets the value of the proxyUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProxyUrl() {
        return proxyUrl;
    }

    /**
     * Sets the value of the proxyUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProxyUrl(String value) {
        this.proxyUrl = value;
    }

    /**
     * Gets the value of the proxyHttpsHost property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProxyHttpsHost() {
        return proxyHttpsHost;
    }

    /**
     * Sets the value of the proxyHttpsHost property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProxyHttpsHost(String value) {
        this.proxyHttpsHost = value;
    }

    /**
     * Gets the value of the proxyHttpsIpPort property.
     * 
     */
    public long getProxyHttpsIpPort() {
        return proxyHttpsIpPort;
    }

    /**
     * Sets the value of the proxyHttpsIpPort property.
     * 
     */
    public void setProxyHttpsIpPort(long value) {
        this.proxyHttpsIpPort = value;
    }

    /**
     * Gets the value of the userAgentProxy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserAgentProxy() {
        return userAgentProxy;
    }

    /**
     * Sets the value of the userAgentProxy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserAgentProxy(String value) {
        this.userAgentProxy = value;
    }

    /**
     * Gets the value of the safeHosts property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSafeHosts() {
        return safeHosts;
    }

    /**
     * Sets the value of the safeHosts property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSafeHosts(String value) {
        this.safeHosts = value;
    }

    /**
     * Gets the value of the startTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * Sets the value of the startTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartTime(String value) {
        this.startTime = value;
    }

    /**
     * Gets the value of the endTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * Sets the value of the endTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndTime(String value) {
        this.endTime = value;
    }

    /**
     * Gets the value of the duration property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Sets the value of the duration property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDuration(String value) {
        this.duration = value;
    }

    /**
     * Gets the value of the reEstablishSessionAfterThisPauseTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReEstablishSessionAfterThisPauseTime() {
        return reEstablishSessionAfterThisPauseTime;
    }

    /**
     * Sets the value of the reEstablishSessionAfterThisPauseTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReEstablishSessionAfterThisPauseTime(String value) {
        this.reEstablishSessionAfterThisPauseTime = value;
    }

    /**
     * Gets the value of the pauseAfterThisTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPauseAfterThisTime() {
        return pauseAfterThisTime;
    }

    /**
     * Sets the value of the pauseAfterThisTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPauseAfterThisTime(String value) {
        this.pauseAfterThisTime = value;
    }

    /**
     * Gets the value of the trainingConfig property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrainingConfig() {
        return trainingConfig;
    }

    /**
     * Sets the value of the trainingConfig property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrainingConfig(String value) {
        this.trainingConfig = value;
    }

    /**
     * Gets the value of the trainingConfigLogin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrainingConfigLogin() {
        return trainingConfigLogin;
    }

    /**
     * Sets the value of the trainingConfigLogin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrainingConfigLogin(String value) {
        this.trainingConfigLogin = value;
    }

    /**
     * Gets the value of the dollarsPerHourAppDev property.
     * 
     */
    public double getDollarsPerHourAppDev() {
        return dollarsPerHourAppDev;
    }

    /**
     * Sets the value of the dollarsPerHourAppDev property.
     * 
     */
    public void setDollarsPerHourAppDev(double value) {
        this.dollarsPerHourAppDev = value;
    }

    /**
     * Gets the value of the dollarsPerHourServerAdmin property.
     * 
     */
    public double getDollarsPerHourServerAdmin() {
        return dollarsPerHourServerAdmin;
    }

    /**
     * Sets the value of the dollarsPerHourServerAdmin property.
     * 
     */
    public void setDollarsPerHourServerAdmin(double value) {
        this.dollarsPerHourServerAdmin = value;
    }

    /**
     * Gets the value of the setupHoursAppDev property.
     * 
     */
    public double getSetupHoursAppDev() {
        return setupHoursAppDev;
    }

    /**
     * Sets the value of the setupHoursAppDev property.
     * 
     */
    public void setSetupHoursAppDev(double value) {
        this.setupHoursAppDev = value;
    }

    /**
     * Gets the value of the setupHoursServerAdmin property.
     * 
     */
    public double getSetupHoursServerAdmin() {
        return setupHoursServerAdmin;
    }

    /**
     * Sets the value of the setupHoursServerAdmin property.
     * 
     */
    public void setSetupHoursServerAdmin(double value) {
        this.setupHoursServerAdmin = value;
    }

    /**
     * Gets the value of the minHoursPerIssueAppDev property.
     * 
     */
    public double getMinHoursPerIssueAppDev() {
        return minHoursPerIssueAppDev;
    }

    /**
     * Sets the value of the minHoursPerIssueAppDev property.
     * 
     */
    public void setMinHoursPerIssueAppDev(double value) {
        this.minHoursPerIssueAppDev = value;
    }

    /**
     * Gets the value of the maxHoursPerIssueAppDev property.
     * 
     */
    public double getMaxHoursPerIssueAppDev() {
        return maxHoursPerIssueAppDev;
    }

    /**
     * Sets the value of the maxHoursPerIssueAppDev property.
     * 
     */
    public void setMaxHoursPerIssueAppDev(double value) {
        this.maxHoursPerIssueAppDev = value;
    }

    /**
     * Gets the value of the minHoursPerIssueServerAdmin property.
     * 
     */
    public double getMinHoursPerIssueServerAdmin() {
        return minHoursPerIssueServerAdmin;
    }

    /**
     * Sets the value of the minHoursPerIssueServerAdmin property.
     * 
     */
    public void setMinHoursPerIssueServerAdmin(double value) {
        this.minHoursPerIssueServerAdmin = value;
    }

    /**
     * Gets the value of the maxHoursPerIssueServerAdmin property.
     * 
     */
    public double getMaxHoursPerIssueServerAdmin() {
        return maxHoursPerIssueServerAdmin;
    }

    /**
     * Sets the value of the maxHoursPerIssueServerAdmin property.
     * 
     */
    public void setMaxHoursPerIssueServerAdmin(double value) {
        this.maxHoursPerIssueServerAdmin = value;
    }

    /**
     * Gets the value of the attackLinkBlacklist property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttackLinkBlacklist() {
        return attackLinkBlacklist;
    }

    /**
     * Sets the value of the attackLinkBlacklist property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttackLinkBlacklist(String value) {
        this.attackLinkBlacklist = value;
    }

}
