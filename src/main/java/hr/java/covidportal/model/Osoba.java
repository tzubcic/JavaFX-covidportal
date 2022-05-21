package main.java.hr.java.covidportal.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Predstavlja entitet osobe koja je definirana imenom, prezimenom, starosti, zupanijom, zarazenoscu bolesti i
 * popisom kontaktiranih osoba
 *
 * @author Toni Zubcic
 */

public class Osoba implements Serializable {

    private Long id;
    private String ime;
    private String prezime;
    private LocalDate datumRodjenja;
    private Zupanija zupanija;
    private Bolest zarazenBolescu;
    private List<Osoba> kontaktiraneOsobe;

    /**
     * predstavlja implementaciju builder patterna
     */

    public static class Builder {
        private  Long id;
        private String ime;
        private String prezime;
        private LocalDate datumRodjenja;
        private Zupanija zupanija;
        private Bolest zarazenBolescu;
        private List<Osoba> kontaktiraneOsobe;

        public Builder saId(Long id){
            this.id = id;

            return this;
        }

        public Builder saIme(String ime){
            this.ime = ime;

            return this;
        }

        public Builder saPrezime(String prezime){
            this.prezime = prezime;

            return this;
        }

        public Builder saDatumRodjenja(LocalDate datumRodjenja){
            this.datumRodjenja = datumRodjenja;

            return this;
        }

        public Builder uZupanija(Zupanija zupanija){
            this.zupanija = zupanija;

            return this;
        }

        public Builder imaBolest(Bolest zarazenBolescu){
            this.zarazenBolescu = zarazenBolescu;

            return this;
        }

        public Builder imaKontakte( List<Osoba> kontaktiraneOsobe){

            if(this.zarazenBolescu instanceof Virus) {
                for(Osoba osoba : kontaktiraneOsobe)
                    osoba.setZarazenBolescu(this.zarazenBolescu);
            }

            this.kontaktiraneOsobe = kontaktiraneOsobe;

            return this;
        }

        /**
         * inicijalizira podatke o imenu, prezimenu, starosti, županiji, zaraženošću bolesti i popisom kontakata
         *
         * @return vraća objekt entiteta osobe sa incijaliziranim podacima
         */

        public  Osoba build() {

            Osoba osoba = new Osoba();

            osoba.id = this.id;
            osoba.ime = this.ime;
            osoba.prezime = this.prezime;
            osoba.datumRodjenja = this.datumRodjenja;
            osoba.zupanija = this.zupanija;
            osoba.zarazenBolescu = this.zarazenBolescu;
            osoba.kontaktiraneOsobe = this.kontaktiraneOsobe;

            return osoba;
        }

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(LocalDate datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public Zupanija getZupanija() {
        return zupanija;
    }

    public void setZupanija(Zupanija zupanija) {
        this.zupanija = zupanija;
    }

    public Bolest getZarazenBolescu() {
        return zarazenBolescu;
    }

    public void setZarazenBolescu(Bolest zarazenBolescu) {
        this.zarazenBolescu = zarazenBolescu;
    }

    public List<Osoba> getKontaktiraneOsobe() {
        return kontaktiraneOsobe;
    }

    public void setKontaktiraneOsobe(List<Osoba> kontaktiraneOsobe) {

        if(this.zarazenBolescu instanceof Virus) {
            for(Osoba osoba : kontaktiraneOsobe)
                osoba.setZarazenBolescu(this.zarazenBolescu);
        }

        this.kontaktiraneOsobe = kontaktiraneOsobe;

    }

    /**
     *  služi za usporedbu dva objekta na temelju vrijednosti imena, prezimena, starosti, zupanije, zarazenosti bolescu
     *  i popisom kontaktiranih osoba. Ukoliko su vrijednosti dvaju objekata iste vraća <code>true</code>, ako su
     *  razlicitog tipa, razlicitih vrijednosti ili smo proslijedili prazan parametar
     *  vraca <code>false</code>
     * @param o objekt koji uspoređujemo sa trenutnim
     * @return vraca odgovarajucu <code>boolean</code> vrijednost usporedbe
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Osoba osoba = (Osoba) o;
        return Objects.equals(ime, osoba.ime) &&
                Objects.equals(prezime, osoba.prezime) &&
                Objects.equals(datumRodjenja, osoba.datumRodjenja) &&
                Objects.equals(zupanija, osoba.zupanija) &&
                Objects.equals(zarazenBolescu, osoba.zarazenBolescu) &&
                Objects.equals(kontaktiraneOsobe, osoba.kontaktiraneOsobe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ime, prezime, datumRodjenja, zupanija, zarazenBolescu, kontaktiraneOsobe);
    }

    @Override
    public String toString() {
        return ime + " " + prezime + '\'';
    }

    public String getImenaPrezimenaKontakata(){
        List<Osoba> popisKontakata = kontaktiraneOsobe;
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        for(Osoba o : popisKontakata){
            cnt++;
            if(cnt != popisKontakata.size()){
                sb.append(o.getIme() + " " + o.getPrezime() + ", ");
            }
            else{
                sb.append(o.getIme() + " " + o.getPrezime());
            }

        }
        return sb.toString();
    }

    public Integer dohvatiStarost() {
        return Period.between(this.datumRodjenja, LocalDate.now()).getYears();
    }
}
