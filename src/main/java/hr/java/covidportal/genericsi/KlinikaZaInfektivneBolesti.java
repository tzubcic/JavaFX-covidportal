package main.java.hr.java.covidportal.genericsi;

import main.java.hr.java.covidportal.model.Osoba;
import main.java.hr.java.covidportal.model.Virus;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Entitet koji predstavlja kliniku za infektivne bolesti te za vrijednosti ima parametrizirane liste
 * @param <T> genericki tip koji nasljedjuje Virus
 * @param <S> genericki tip koji nasljedjuje Osobu
 */

public class KlinikaZaInfektivneBolesti<T extends Virus,S extends Osoba> {

    private List<T> sviVirusi;
    private List<S> sveZarazeneOsobe;

    /**
     *  Inicijalizacija podataka, listi virusa i osoba
     * @param sviVirusi lista svih unesenih virusa u aplikaciju
     * @param sveZarazeneOsobe lista svih osoba koje su zarazene virusima
     */

    public KlinikaZaInfektivneBolesti(List<T> sviVirusi, List<S> sveZarazeneOsobe) {
        this.sviVirusi = sviVirusi;
        this.sveZarazeneOsobe = sveZarazeneOsobe;
    }

    public List<T> getSviVirusi() {
        return sviVirusi;
    }

    public void setSviVirusi(List<T> sviVirusi) {
        this.sviVirusi = sviVirusi;
    }

    public List<S> getSveZarazeneOsobe() {
        return sveZarazeneOsobe;
    }

    public void setSveZarazeneOsobe(List<S> sveZarazeneOsobe) {
        this.sveZarazeneOsobe = sveZarazeneOsobe;
    }

    /**
     * služi za dodavaanje pojedinog virusa u listu virusa klinike
     * @param uneseniVirus vrijednost unesenog virusa
     */

    public void dodajVirus(T uneseniVirus){
            sviVirusi.add(uneseniVirus);
    }

    /**
     * služi za dodavaanje pojedine osobe u listu osoba klinike
     * @param unesenaOsoba vrijednost unesene osobe
     */
    public void dodajOsobu(S unesenaOsoba){
            sveZarazeneOsobe.add(unesenaOsoba);
    }

    /**
     *  Sortira sve viruse u klinci po nazivu obrnuto od smjera abecede
     */
    public void sortiranjeVirusaLambdom() {
        sviVirusi = sviVirusi.stream()
                .sorted(Comparator.comparing(Virus::getNaziv).reversed())
                .collect(Collectors.toList());
    }
}
