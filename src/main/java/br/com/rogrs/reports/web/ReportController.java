package br.com.rogrs.reports.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = ReportController.BASE_URL)
public class ReportController {
	
	
	public static final String BASE_URL = "/report";
	
	
    @RequestMapping("/teste")
    public ResponseEntity<?> export(@RequestParam(value="name" ) String name) {
    
    	
    	return ResponseEntity.ok().build();
    }

	/*@GetMapping(name = "exportPdf", value = "/export/pdf")
	@ResponseBody
	public ResponseEntity<?> exportPdf(@RequestParam(value = "app") String app) {

		return ResponseEntity.ok().build();
	}*/

}
