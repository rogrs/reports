package br.com.rogrs.reports.services;

import java.sql.Connection;
import java.util.HashMap;

import br.com.rogrs.reports.domain.Reports;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class ReportGenerator {

    public void generate(Reports report, Connection connection, HashMap params) {

        try {
            System.out.println("Start ....");

            JasperCompileManager.compileReportToFile(report.getJrxmlFilename(), report.getJasperFilename());

            // Generate jasper print
            JasperPrint jprint = (JasperPrint) JasperFillManager.fillReport(report.getJasperFilename(), params, connection);

            // Export pdf file
            JasperExportManager.exportReportToPdfFile(jprint, report.getPdfFilename());

            System.out.println("Done exporting reports to pdf");

        } catch (Exception e) {
            System.out.print("Exceptiion" + e);
        }

    }
}
