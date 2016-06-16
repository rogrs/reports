package br.com.rogrs.reports.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rogrs.reports.domain.Reports;

@Repository
public interface ReportsRepository extends JpaRepository<Reports, Long> {


}