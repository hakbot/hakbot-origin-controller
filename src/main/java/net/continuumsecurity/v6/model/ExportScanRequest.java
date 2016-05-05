package net.continuumsecurity.v6.model;

import javax.xml.bind.annotation.*;

@XmlRootElement
public class ExportScanRequest {
    private String format;

    @XmlElement
    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
