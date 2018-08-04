package br.com.rogrs.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "aplication")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Application extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description")
	private String description;

	@OneToMany(mappedBy = "aplication")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Set<Database> datasources = new HashSet<>();

	@OneToMany(mappedBy = "aplication")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Set<Report> reports = new HashSet<>();

	public String getName() {
		return name;
	}

	public Application name(String name) {
		this.name = name;
		return this;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public Application description(String description) {
		this.description = description;
		return this;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Database> getDatasources() {
		return datasources;
	}

	public Application datasources(Set<Database> datasources) {
		this.datasources = datasources;
		return this;
	}

	public Application addDatasource(Database datasource) {
		this.datasources.add(datasource);
		datasource.setAplication(this);
		return this;
	}

	public Application removeDatasource(Database datasource) {
		this.datasources.remove(datasource);
		datasource.setAplication(null);
		return this;
	}

	public void setDatasources(Set<Database> datasources) {
		this.datasources = datasources;
	}

	public Set<Report> getReports() {
		return reports;
	}

	public Application reports(Set<Report> reports) {
		this.reports = reports;
		return this;
	}

	public Application addReport(Report report) {
		this.reports.add(report);
		report.setAplication(this);
		return this;
	}

	public Application removeReport(Report report) {
		this.reports.remove(report);
		report.setAplication(null);
		return this;
	}

	public void setReports(Set<Report> reports) {
		this.reports = reports;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Application application = (Application) o;
		if (application.getId() == null || getId() == null) {
			return false;
		}
		return Objects.equals(getId(), application.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}



}
