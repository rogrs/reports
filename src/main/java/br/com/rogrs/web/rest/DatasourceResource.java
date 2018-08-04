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

import br.com.rogrs.domain.Database;
import br.com.rogrs.repository.DatasourceRepository;
import br.com.rogrs.web.rest.errors.BadRequestAlertException;
import br.com.rogrs.web.rest.util.HeaderUtil;
import br.com.rogrs.web.rest.util.PaginationUtil;
import br.com.rogrs.web.rest.util.ResponseUtil;

@RestController
@RequestMapping("/api")
public class DatasourceResource {

    private final Logger log = LoggerFactory.getLogger(DatasourceResource.class);

    private static final String ENTITY_NAME = "datasource";

    private final DatasourceRepository datasourceRepository;

    public DatasourceResource(DatasourceRepository datasourceRepository) {
        this.datasourceRepository = datasourceRepository;
    }

    @PostMapping("/datasources")
    @Timed
    public ResponseEntity<Database> createDatasource(@Valid @RequestBody Database datasource) throws URISyntaxException {
        log.debug("REST request to save Datasource : {}", datasource);
        if (datasource.getId() != null) {
            throw new BadRequestAlertException("A new datasource cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Database result = datasourceRepository.save(datasource);
        return ResponseEntity.created(new URI("/api/datasources/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PutMapping("/datasources")
    @Timed
    public ResponseEntity<Database> updateDatasource(@Valid @RequestBody Database datasource) throws URISyntaxException {
        log.debug("REST request to update Datasource : {}", datasource);
        if (datasource.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Database result = datasourceRepository.save(datasource);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, datasource.getId().toString()))
            .body(result);
    }

    /**
     * GET  /datasources : get all the datasources.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of datasources in body
     */
    @GetMapping("/datasources")
    @Timed
    public ResponseEntity<List<Database>> getAllDatasources(Pageable pageable) {
        log.debug("REST request to get a page of Datasources");
        Page<Database> page = datasourceRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/datasources");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


    @GetMapping("/datasources/{id}")
    @Timed
    public ResponseEntity<Database> getDatasource(@PathVariable Long id) {
        log.debug("REST request to get Datasource : {}", id);
        Optional<Database> datasource = datasourceRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(datasource);
    }

    @DeleteMapping("/datasources/{id}")
    @Timed
    public ResponseEntity<Void> deleteDatasource(@PathVariable Long id) {
        log.debug("REST request to delete Datasource : {}", id);

        datasourceRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
