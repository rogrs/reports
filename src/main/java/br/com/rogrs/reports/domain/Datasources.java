package br.com.rogrs.reports.domain;


import javax.persistence.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;


@Entity
public class Datasources extends AbstractPersistable<Long>{
    
    private static final long serialVersionUID = 1L;
    
    private String url;
    
    private String driver;
   
    private String database;
    
    private String pwd;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

}
