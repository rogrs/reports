package br.com.rogrs.reports.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.rogrs.reports.domain.Datasource;

@RepositoryRestResource(collectionResourceRel = "datasourcesRel", path = "datasources")
public interface DatasourcesRepository extends JpaRepository<Datasource, Long> {

	public Datasource findByDatabase(String database);

}