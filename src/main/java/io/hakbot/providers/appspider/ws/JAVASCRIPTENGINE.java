
package io.hakbot.providers.appspider.ws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for JAVASCRIPTENGINE.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="JAVASCRIPTENGINE">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ejsNoEngine"/>
 *     &lt;enumeration value="ejsMozilla"/>
 *     &lt;enumeration value="ejsIExplorer"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "JAVASCRIPTENGINE")
@XmlEnum
public enum JAVASCRIPTENGINE {

    @XmlEnumValue("ejsNoEngine")
    EJS_NO_ENGINE("ejsNoEngine"),
    @XmlEnumValue("ejsMozilla")
    EJS_MOZILLA("ejsMozilla"),
    @XmlEnumValue("ejsIExplorer")
    EJS_I_EXPLORER("ejsIExplorer");
    private final String value;

    JAVASCRIPTENGINE(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static JAVASCRIPTENGINE fromValue(String v) {
        for (JAVASCRIPTENGINE c: JAVASCRIPTENGINE.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
