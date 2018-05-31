package br.com.rogrs.reports.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.rogrs.reports.domain.Report;

@RepositoryRestResource(collectionResourceRel = "reportsRel", path = "reports")
public interface ReportsRepository extends JpaRepository<Report, Long> {
	
	public Report findByName(String name);


}