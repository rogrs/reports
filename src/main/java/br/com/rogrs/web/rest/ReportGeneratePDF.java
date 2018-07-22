package br.com.rogrs.web.rest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ReportGeneratePDF {

	private final Logger log = LoggerFactory.getLogger(ReportGeneratePDF.class);

	@Autowired
	private DataSource dataSource;

	public void execute(Map<String, Object> parametros, final String fileName, final HttpServletResponse response) {

		parametros = parametros == null ? parametros = new HashMap<>() : parametros;
		InputStream jasperStream = this.getClass().getResourceAsStream(fileName);

		JasperReport jasperReport = null;
		JasperPrint jasperPrint = null;
		try {
			jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);

			jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource.getConnection());
		} catch (JRException | SQLException e) {
			log.error("Erro ao informar parametros para o relatorio", e);
		}

		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "inline; filename=livros.pdf");

		OutputStream outStream = null;
		try {
			outStream = response.getOutputStream();
		} catch (IOException e) {
			log.error("Erro ao exportar o relatorio", e);
		}
		try {
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
		} catch (JRException e) {
			log.error("Erro ao exportar o relatorio para PDF", e);
		}
	}

}
