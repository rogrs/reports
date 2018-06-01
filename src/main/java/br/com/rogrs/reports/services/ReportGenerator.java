package br.com.rogrs.reports.services;

import java.sql.Connection;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.rogrs.reports.domain.Report;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Service
public class ReportGenerator {

	private static final Logger log = LoggerFactory.getLogger(ReportGenerator.class);

	public void generate(Report report, Connection connection, HashMap<String, Object> params) {

		try {
			log.info("Start ...." + report.getJrxmlFilename());

			JasperCompileManager.compileReportToFile(report.getJrxmlFilename(), report.getJasperFilename());

			// Generate jasper print
			JasperPrint jprint = (JasperPrint) JasperFillManager.fillReport(report.getJasperFilename(), params,
					connection);

			// Export pdf file
			JasperExportManager.exportReportToPdfFile(jprint, report.getPdfFilename());

			log.info("Done exporting reports to pdf" + report.getPdfFilename());

		} catch (Exception e) {
			log.error("Exception generate ", e);
		} 

	}
}
