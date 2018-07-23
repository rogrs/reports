package br.com.rogrs.web.rest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.rogrs.domain.Application;
import br.com.rogrs.services.ReportsEngineService;
import net.sf.jasperreports.engine.JRException;

@Controller
@RequestMapping("/engine")
public class ReportsEngineResources {

	@Autowired
	private ReportGeneratePDF reportGeneratePDF;
	
	@Autowired
	private ReportsEngineService reportsEngineService;

	@PostMapping
	public void imprimir(@RequestParam Map<String, Object> parametros, HttpServletResponse response)
			throws JRException, SQLException, IOException {

		reportGeneratePDF.execute(parametros, "/relatorios/livros.jasper", response);
	}
	
	
	@GetMapping("/application/{name}")
	public ResponseEntity<Application> getApplication(@PathVariable String name) {
		
		Application result = reportsEngineService.execute(name);
		return new ResponseEntity<Application>(result, HttpStatus.OK);
		
		
	}


}
