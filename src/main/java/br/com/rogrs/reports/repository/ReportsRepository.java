package br.com.rogrs.reports.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.rogrs.reports.domain.Reports;

@RepositoryRestResource(collectionResourceRel = "reportsRel", path = "reports")
public interface ReportsRepository extends JpaRepository<Reports, Long> {
	
	public Reports findByMenuName(String menuName);


}