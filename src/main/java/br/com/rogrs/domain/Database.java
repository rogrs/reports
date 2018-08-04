package br.com.rogrs.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.util.Objects;

@Entity
@Table(name = "database")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Database extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "name", nullable = false)
	private String name;

	@NotNull
	@Column(name = "jhi_url", nullable = false)
	private String url;

	@NotNull
	@Column(name = "jhi_driver", nullable = false)
	private String driver;

	@NotNull
	@Column(name = "jhi_database", nullable = false)
	private String database;

	@NotNull
	@Column(name = "jhi_username", nullable = false)
	private String username;

	@NotNull
	@Column(name = "jhi_password", nullable = false)
	private String password;

	@NotNull
	@Column(name = "jhi_dialect", nullable = false)
	private String dialect;

	@ManyToOne
	@JsonIgnoreProperties("datasources")
	private Application aplication;

	public String getName() {
		return name;
	}

	public Database name(String name) {
		this.name = name;
		return this;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public Database url(String url) {
		this.url = url;
		return this;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDriver() {
		return driver;
	}

	public Database driver(String driver) {
		this.driver = driver;
		return this;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getDatabase() {
		return database;
	}

	public Database database(String database) {
		this.database = database;
		return this;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getUsername() {
		return username;
	}

	public Database username(String username) {
		this.username = username;
		return this;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public Database password(String password) {
		this.password = password;
		return this;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Application getAplication() {
		return aplication;
	}

	public Database aplication(Application application) {
		this.aplication = application;
		return this;
	}

	public void setAplication(Application application) {
		this.aplication = application;
	}

	public String getDialect() {
		return dialect;
	}

	public void setDialect(String dialect) {
		this.dialect = dialect;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Database datasource = (Database) o;
		if (datasource.getId() == null || getId() == null) {
			return false;
		}
		return Objects.equals(getId(), datasource.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}

	@Override
	public String toString() {
		return "Datasource [name=" + name + ", "
				+ "url=" + url + ", driver=" + driver + ", database=" + database
				+ ", username=" + username + ", password=" + password + ", dialect=" + dialect + ", aplication="
				+ aplication + "]";
	}

}
