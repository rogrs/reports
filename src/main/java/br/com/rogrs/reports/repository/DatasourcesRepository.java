package br.com.rogrs.reports.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.rogrs.reports.domain.Datasources;

@RepositoryRestResource(collectionResourceRel = "datasourcesRel", path = "datasources")
public interface DatasourcesRepository extends JpaRepository<Datasources, Long> {
	
	
	public Datasources findByDatabase(String database);


}