package main.java.hr.java.covidportal.sort;

import main.java.hr.java.covidportal.model.Zupanija;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Predstavlja entitet za sortiranje klase <code>Zupanija</code>
 */

public class CovidSorter implements Comparator<Zupanija> {

    /**
     * služi za uspređivanje dva objekta klase <code>Zupanija</code> po povratnoj vrijednost funkcije
     * <code>getPostotakZarazenih</code>
     *
     * @param a prvi objekt za usporedbu
     * @param b drugi objekt za usporedbu
     * @return ukoliko su jednaki vraća nulu, ukoliko je <code>a</code> veci od <code>b</code> pozitivan integer,
     *      * <code>a</code> manji od <code>b</code> negativan integer
     */
    public int compare(Zupanija a, Zupanija b)
    {
        return b.getPostotakZarazenih().compareTo(a.getPostotakZarazenih());
    }

    /** služi za vraćanje prvog objekta sa seta klase <code>Zupanija</code>, tako da se deklarira i inicijalizira
     * iterator koji će prelaziti preko popisa <code>setZupanija</code>, ali ce vratiti samo prvi objekt ukoliko popis
     * sadrži više od jednog elementa
     *
     * @param listZupanija set županija
     * @return vraća prvi objekt u setu (popisu)
     */
    public Zupanija getPrvaNaPopisu(List<Zupanija> listZupanija){

        Iterator<Zupanija> iter = listZupanija.iterator();
        return iter.next();
    }
}
