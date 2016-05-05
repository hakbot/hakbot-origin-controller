package net.continuumsecurity.v6.model;

import javax.xml.bind.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExportV6 {
    private String file;

    @XmlElement
    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
