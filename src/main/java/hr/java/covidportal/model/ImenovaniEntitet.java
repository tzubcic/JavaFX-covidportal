package main.java.hr.java.covidportal.model;

import java.io.Serializable;

/**
 * Predstavlja apstraktnu klasu sa podatkom o nazivu
 *
 * @author Toni Zubcic
 */

public abstract class ImenovaniEntitet implements Serializable {

    private String naziv;
    private Long id;

    /**
     *  Inicijalizira podatak o nazivu
     *
     * @param naziv podatak o nazivu
     */

    public ImenovaniEntitet(String naziv) {

        this.naziv = naziv;
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
