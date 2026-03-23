package br.com.rogrs.service.dto;

public class TemplateMetadataResponse {

    private final String code;

    private final String description;

    public TemplateMetadataResponse(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
