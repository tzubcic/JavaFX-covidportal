package main.java.hr.java.covidportal.model;

import java.util.Objects;

/**
 * Predstavlja entitet županije koja je definirana nazivom i brojem stanovnika
 *
 * @author Toni Zubcic
 */

public class Zupanija extends ImenovaniEntitet {

    private Integer brojStanovnika;
    private Integer brojZarazenih;

    /**
     * Inicijalizacija podataka o nazivu i broju stanovnika
     *
     * @param naziv podatak o nazivu
     * @param brojStanovnika podatak o broju stanovnika
     */
    public Zupanija(String naziv, Integer brojStanovnika, Integer brojZarazenih) {
        super(naziv);
        this.brojStanovnika = brojStanovnika;
        this.brojZarazenih = brojZarazenih;
    }

    public Integer getBrojStanovnika() {
        return brojStanovnika;
    }

    public void setBrojStanovnika(Integer brojStanovnika) {
        this.brojStanovnika = brojStanovnika;
    }

    public Integer getBrojZarazenih() {
        return brojZarazenih;
    }

    public void setBrojZarazenih(Integer brojZarazenih) {
        this.brojZarazenih = brojZarazenih;
    }

    public Float getPostotakZarazenih(){
        return brojZarazenih * 100f / brojStanovnika;
    }

    /**
     * Služi za uspredbu trenutnog objekta sa drugim <code>o</code> objektom na temelju naziva
     * ukoliko su objekti jednaki vraća <code>true</code>,
     * ukoliko su različiti ili različitog tipa ili smo proslijedili objekt sa <code>null</code> vrijednosti
     * vraća <code>false</code>
     * @param o objekt s kojim uspoređujemo trenutni
     * @return vraća vrijednost usporedbe <code>true</code> ukoliko su jednaki, ako nisu i različitog
     * su tipa <code>false</code>
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Zupanija zupanija = (Zupanija) o;
        return Objects.equals(this.getNaziv(), zupanija.getNaziv());
    }



    @Override
    public int hashCode() {
        return Objects.hash(getNaziv());
    }

    @Override
    public String toString() {
        return this.getNaziv();
    }
}
