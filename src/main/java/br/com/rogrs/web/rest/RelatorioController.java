package br.com.rogrs.web.rest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.sf.jasperreports.engine.JRException;

@Controller
@RequestMapping("/relatorio")
public class RelatorioController {

	@Autowired
	private ReportGeneratePDF reportGeneratePDF;

	@PostMapping
	public void imprimir(@RequestParam Map<String, Object> parametros, HttpServletResponse response)
			throws JRException, SQLException, IOException {

		reportGeneratePDF.execute(parametros, "/relatorios/livros.jasper", response);
	}

}
