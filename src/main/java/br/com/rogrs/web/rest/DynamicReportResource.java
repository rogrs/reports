package br.com.rogrs.web.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rogrs.service.dto.DynamicReportRequest;
import br.com.rogrs.service.dto.TemplateMetadataResponse;
import br.com.rogrs.services.DynamicReportService;

@RestController
@RequestMapping("/api/reporting")
public class DynamicReportResource {

    private final DynamicReportService dynamicReportService;

    public DynamicReportResource(DynamicReportService dynamicReportService) {
        this.dynamicReportService = dynamicReportService;
    }

    @GetMapping("/templates")
    public ResponseEntity<List<TemplateMetadataResponse>> listTemplates() {
        return ResponseEntity.ok(dynamicReportService.listTemplates());
    }

    @PostMapping(value = "/render", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> render(@Valid @RequestBody DynamicReportRequest request) {
        byte[] pdf = dynamicReportService.renderPdf(request);
        String fileName = request.getReportName().replaceAll("[^a-zA-Z0-9-_]", "_") + ".pdf";

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION,
                ContentDisposition.builder("inline").filename(fileName).build().toString())
            .contentType(MediaType.APPLICATION_PDF)
            .body(pdf);
    }
}
