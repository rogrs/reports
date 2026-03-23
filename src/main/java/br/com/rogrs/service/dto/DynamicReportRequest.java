package br.com.rogrs.service.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class DynamicReportRequest {

    @NotBlank
    private String reportName;

    @NotNull
    @Valid
    private ReportTemplateRequest template;

    @Valid
    private ReportDataSourceRequest dataSource;

    private Map<String, Object> parameters = new HashMap<>();

    private List<Map<String, Object>> data = new ArrayList<>();

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public ReportTemplateRequest getTemplate() {
        return template;
    }

    public void setTemplate(ReportTemplateRequest template) {
        this.template = template;
    }

    public ReportDataSourceRequest getDataSource() {
        return dataSource;
    }

    public void setDataSource(ReportDataSourceRequest dataSource) {
        this.dataSource = dataSource;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    public List<Map<String, Object>> getData() {
        return data;
    }

    public void setData(List<Map<String, Object>> data) {
        this.data = data;
    }
}
