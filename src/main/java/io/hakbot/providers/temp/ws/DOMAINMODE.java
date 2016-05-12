
package io.hakbot.providers.temp.ws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DOMAINMODE.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="DOMAINMODE">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="edmNoDomainCrawling"/>
 *     &lt;enumeration value="edmThisAndUnderDomainCrawling"/>
 *     &lt;enumeration value="edmAnyInDomainCrawling"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DOMAINMODE")
@XmlEnum
public enum DOMAINMODE {

    @XmlEnumValue("edmNoDomainCrawling")
    EDM_NO_DOMAIN_CRAWLING("edmNoDomainCrawling"),
    @XmlEnumValue("edmThisAndUnderDomainCrawling")
    EDM_THIS_AND_UNDER_DOMAIN_CRAWLING("edmThisAndUnderDomainCrawling"),
    @XmlEnumValue("edmAnyInDomainCrawling")
    EDM_ANY_IN_DOMAIN_CRAWLING("edmAnyInDomainCrawling");
    private final String value;

    DOMAINMODE(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DOMAINMODE fromValue(String v) {
        for (DOMAINMODE c: DOMAINMODE.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
