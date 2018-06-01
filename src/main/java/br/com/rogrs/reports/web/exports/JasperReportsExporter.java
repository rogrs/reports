package br.com.rogrs.reports.web.exports;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

public interface JasperReportsExporter {

	public void export(JasperPrint jp, String fileName, HttpServletResponse response) throws JRException, IOException;

}