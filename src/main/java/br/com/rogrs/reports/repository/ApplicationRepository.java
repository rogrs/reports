package br.com.rogrs.reports.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.rogrs.reports.domain.Application;

@RepositoryRestResource(collectionResourceRel = "applicationsRel", path = "applictions")
public interface ApplicationRepository extends JpaRepository<Application, Long> {
	
	public Application findByName(String name);


}