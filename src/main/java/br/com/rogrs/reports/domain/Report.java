package br.com.rogrs.reports.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Report.
 */
@Entity
@Table(name = "report")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Report implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

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

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

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
