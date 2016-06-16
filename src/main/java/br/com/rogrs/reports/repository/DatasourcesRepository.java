package br.com.rogrs.reports.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rogrs.reports.domain.Datasources;

@Repository
public interface DatasourcesRepository extends JpaRepository<Datasources, Long> {


}