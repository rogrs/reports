package br.com.rogrs.reports.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * A Report.
 */
@Entity
@Table(name = "report")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Report extends AbstractPersistable<Long>{

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "jrxml_filename")
    private String jrxmlFilename;

    @Column(name = "jasper_filename")
    private String jasperFilename;

    @Column(name = "pdf_filename")
    private String pdfFilename;

    @ManyToOne
    private Application aplication;

   
    public String getName() {
        return name;
    }

    public Report name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJrxmlFilename() {
        return jrxmlFilename;
    }

    public Report jrxmlFilename(String jrxmlFilename) {
        this.jrxmlFilename = jrxmlFilename;
        return this;
    }

    public void setJrxmlFilename(String jrxmlFilename) {
        this.jrxmlFilename = jrxmlFilename;
    }

    public String getJasperFilename() {
        return jasperFilename;
    }

    public Report jasperFilename(String jasperFilename) {
        this.jasperFilename = jasperFilename;
        return this;
    }

    public void setJasperFilename(String jasperFilename) {
        this.jasperFilename = jasperFilename;
    }

    public String getPdfFilename() {
        return pdfFilename;
    }

    public Report pdfFilename(String pdfFilename) {
        this.pdfFilename = pdfFilename;
        return this;
    }

    public void setPdfFilename(String pdfFilename) {
        this.pdfFilename = pdfFilename;
    }

    public Application getAplication() {
        return aplication;
    }

    public Report aplication(Application application) {
        this.aplication = application;
        return this;
    }

    public void setAplication(Application application) {
        this.aplication = application;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Report report = (Report) o;
        if (report.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), report.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Report{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", jrxmlFilename='" + getJrxmlFilename() + "'" +
            ", jasperFilename='" + getJasperFilename() + "'" +
            ", pdfFilename='" + getPdfFilename() + "'" +
            "}";
    }
}
