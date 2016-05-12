
package io.hakbot.providers.temp.ws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for COMPREHENSIVENESS.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="COMPREHENSIVENESS">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ecDefault"/>
 *     &lt;enumeration value="ecLight"/>
 *     &lt;enumeration value="ecDeep"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "COMPREHENSIVENESS")
@XmlEnum
public enum COMPREHENSIVENESS {

    @XmlEnumValue("ecDefault")
    EC_DEFAULT("ecDefault"),
    @XmlEnumValue("ecLight")
    EC_LIGHT("ecLight"),
    @XmlEnumValue("ecDeep")
    EC_DEEP("ecDeep");
    private final String value;

    COMPREHENSIVENESS(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static COMPREHENSIVENESS fromValue(String v) {
        for (COMPREHENSIVENESS c: COMPREHENSIVENESS.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
