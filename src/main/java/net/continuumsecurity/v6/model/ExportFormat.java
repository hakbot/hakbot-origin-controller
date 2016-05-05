package net.continuumsecurity.v6.model;

public enum ExportFormat {
    NESSUS("nessus"), HTML("html"), PDF("pdf"), CSV("csv"), DB("db");

    private final String value;

    ExportFormat(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
