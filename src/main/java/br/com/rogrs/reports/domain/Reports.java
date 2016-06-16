package br.com.rogrs.reports.domain;


import javax.persistence.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Reports extends AbstractPersistable<Long>{
    
    private static final long serialVersionUID = 1L;

    private String jrxmlFilename;
    
    private String jasperFilename;
    
    private String pdfFilename;
    
    private String menuName;

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getJrxmlFilename() {
        return jrxmlFilename;
    }

    public void setJrxmlFilename(String jrxmlFilename) {
        this.jrxmlFilename = jrxmlFilename;
    }

    public String getJasperFilename() {
        return jasperFilename;
    }

    public void setJasperFilename(String jasperFilename) {
        this.jasperFilename = jasperFilename;
    }

    public String getPdfFilename() {
        return pdfFilename;
    }

    public void setPdfFilename(String pdfFilename) {
        this.pdfFilename = pdfFilename;
    }

}
