package br.com.rogrs.service.dto;

import javax.validation.constraints.NotBlank;

public class ReportTemplateRequest {

    public enum SourceType {
        CLASSPATH,
        INLINE
    }

    private SourceType sourceType = SourceType.CLASSPATH;

    @NotBlank
    private String value;

    public SourceType getSourceType() {
        return sourceType;
    }

    public void setSourceType(SourceType sourceType) {
        this.sourceType = sourceType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
