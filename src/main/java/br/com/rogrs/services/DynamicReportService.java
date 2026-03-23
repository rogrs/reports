package br.com.rogrs.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import br.com.rogrs.service.dto.DynamicReportRequest;
import br.com.rogrs.service.dto.ReportDataSourceRequest;
import br.com.rogrs.service.dto.ReportTemplateRequest;
import br.com.rogrs.service.dto.TemplateMetadataResponse;
import br.com.rogrs.web.rest.errors.BadRequestAlertException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

@Service
public class DynamicReportService {

    public byte[] renderPdf(DynamicReportRequest request) {
        validate(request);

        try {
            JasperReport jasperReport = compileReport(request.getTemplate());
            JasperPrint jasperPrint = fillReport(jasperReport, request);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
            return outputStream.toByteArray();
        } catch (JRException | IOException | SQLException | ClassNotFoundException exception) {
            throw new BadRequestAlertException("Falha ao gerar relatório: " + exception.getMessage(), "reporting", "generation_error");
        }
    }

    public List<TemplateMetadataResponse> listTemplates() {
        return Arrays.asList(
            new TemplateMetadataResponse("reports/templates/dynamic-list.jrxml",
                "Template tabular para listas dinâmicas usando coleção JSON ou consulta SQL com parâmetros Jasper."),
            new TemplateMetadataResponse("reports/templates/executive-summary.jrxml",
                "Template resumido para indicadores e sumário executivo em formato PDF.")
        );
    }

    private void validate(DynamicReportRequest request) {
        boolean hasCollection = !CollectionUtils.isEmpty(request.getData());
        boolean hasJdbc = request.getDataSource() != null && StringUtils.hasText(request.getDataSource().getJdbcUrl());

        if (!hasCollection && !hasJdbc) {
            throw new BadRequestAlertException(
                "Informe uma coleção em data ou uma configuração JDBC em dataSource", "reporting", "missing_data_source");
        }
    }

    private JasperReport compileReport(ReportTemplateRequest templateRequest) throws IOException, JRException {
        if (templateRequest.getSourceType() == ReportTemplateRequest.SourceType.INLINE) {
            JasperDesign design = JRXmlLoader.load(new StringReader(templateRequest.getValue()));
            return JasperCompileManager.compileReport(design);
        }

        ClassPathResource resource = new ClassPathResource(templateRequest.getValue());
        if (!resource.exists()) {
            throw new BadRequestAlertException("Template não encontrado no classpath", "reporting", "template_not_found");
        }

        try (InputStream inputStream = resource.getInputStream()) {
            JasperDesign design = JRXmlLoader.load(inputStream);
            return JasperCompileManager.compileReport(design);
        }
    }

    private JasperPrint fillReport(JasperReport jasperReport, DynamicReportRequest request)
        throws JRException, SQLException, ClassNotFoundException {
        Map<String, Object> parameters = request.getParameters();
        if (parameters == null) {
            throw new BadRequestAlertException("Parâmetros inválidos", "reporting", "invalid_parameters");
        }

        if (!CollectionUtils.isEmpty(request.getData())) {
            return JasperFillManager.fillReport(jasperReport, parameters, new JRMapCollectionDataSource(request.getData()));
        }

        ReportDataSourceRequest dataSourceRequest = request.getDataSource();
        if (StringUtils.hasText(dataSourceRequest.getDriverClassName())) {
            Class.forName(dataSourceRequest.getDriverClassName());
        }

        try (Connection connection = DriverManager.getConnection(
            dataSourceRequest.getJdbcUrl(), dataSourceRequest.getUsername(), dataSourceRequest.getPassword())) {
            return JasperFillManager.fillReport(jasperReport, parameters, connection);
        }
    }
}
