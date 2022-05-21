package main.java.hr.java.covidportal.sort;

import main.java.hr.java.covidportal.model.Virus;

import java.util.Comparator;

/**
 * Predstavlja entitet za sortiranje klase <code>Virus</code>
 */

public class VirusSorter implements Comparator<Virus> {

    /**
     * služi za uspređivanje dva objekta klase <code>Virus</code> po povratnoj vrijednost njihovih naziva
     * obrnuto od redoslijeda abecede
     *
     * @param o1 prvi objekt za usporedbu
     * @param o2 drugi objekt za usporedbu
     * @return ukoliko su jednaki vraća nulu, ukoliko je <code>a</code> veci od <code>b</code> pozitivan integer,
     *      * <code>a</code> manji od <code>b</code> negativan integer
     */
    @Override
    public int compare(Virus o1, Virus o2) {
        return o2.getNaziv().compareTo(o1.getNaziv());
    }
}
