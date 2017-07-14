package br.com.rogrs.reports.services;

import java.sql.Connection;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.rogrs.reports.domain.Reports;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class ReportGenerator {
    

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportGenerator.class);

    public void generate(Reports report, Connection connection, HashMap<String, Object> params) {

        try {
            LOGGER.info("Start ...."+report.getJrxmlFilename());

            JasperCompileManager.compileReportToFile(report.getJrxmlFilename(), report.getJasperFilename());

            // Generate jasper print
            JasperPrint jprint = (JasperPrint) JasperFillManager.fillReport(report.getJasperFilename(), params, connection);

            // Export pdf file
            JasperExportManager.exportReportToPdfFile(jprint, report.getPdfFilename());

            LOGGER.info("Done exporting reports to pdf"+report.getPdfFilename());

        } catch (Exception e) {
            LOGGER.error("Exception" + e);
        }

    }
}
