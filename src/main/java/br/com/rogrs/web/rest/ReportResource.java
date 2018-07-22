package br.com.rogrs.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import br.com.rogrs.domain.Report;
import br.com.rogrs.repository.ReportRepository;
import br.com.rogrs.web.rest.errors.BadRequestAlertException;
import br.com.rogrs.web.rest.util.HeaderUtil;
import br.com.rogrs.web.rest.util.PaginationUtil;
import br.com.rogrs.web.rest.util.ResponseUtil;

@RestController
@RequestMapping("/api")
public class ReportResource {

	private final Logger log = LoggerFactory.getLogger(ReportResource.class);

	private static final String ENTITY_NAME = "report";

	private final ReportRepository reportRepository;

	public ReportResource(ReportRepository reportRepository) {
		this.reportRepository = reportRepository;
	}

	@PostMapping("/reports")
	@Timed
	public ResponseEntity<Report> createReport(@Valid @RequestBody Report report) throws URISyntaxException {
		log.debug("REST request to save Report : {}", report);
		if (report.getId() != null) {
			throw new BadRequestAlertException("A new report cannot already have an ID ", ENTITY_NAME, "idexists");
		}
		Report result = reportRepository.save(report);
		return ResponseEntity.created(new URI("/api/reports/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

	@PutMapping("/reports")
	@Timed
	public ResponseEntity<Report> updateReport(@Valid @RequestBody Report report) throws URISyntaxException {
		log.debug("REST request to update Report : {}", report);
		if (report.getId() == null) {
			throw new BadRequestAlertException("Invalid id ", ENTITY_NAME, " idnull");
		}
		Report result = reportRepository.save(report);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, report.getId().toString()))
				.body(result);
	}

	@GetMapping("/reports")
	@Timed
	public ResponseEntity<List<Report>> getAllReports(Pageable pageable) {
		log.debug("REST request to get a page of Reports");
		Page<Report> page = reportRepository.findAll(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/reports");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}

	@GetMapping("/reports/{id}")
	@Timed
	public ResponseEntity<Report> getReport(@PathVariable Long id) {
		log.debug("REST request to get Report : {}", id);
		Optional<Report> report = reportRepository.findById(id);
		return ResponseUtil.wrapOrNotFound(report);
	}

	@DeleteMapping("/reports/{id}")
	@Timed
	public ResponseEntity<Void> deleteReport(@PathVariable Long id) {
		log.debug("REST request to delete Report : {}", id);

		reportRepository.deleteById(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}
}
