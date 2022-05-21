package main.java.hr.java.covidportal.model;

import java.util.Comparator;

/**
 * Predstavlja entitet za sortiranje popisa simptoma
 */

public class SortPopisaSimptoma implements Comparator <Simptom> {

    /**
     * Služi za uspoređivanje dvaju simptoma preko vrijednosti parametra <code>naziv</code>
     *
     * @param a prvi simptom
     * @param b drugi simptom
     * @return ukoliko su jednaki vraća nulu, ukoliko je <code>a</code> veci od <code>b</code> pozitivan integer,
     * <code>a</code> manji od <code>b</code> negativan integer
     */
    public int compare(Simptom a, Simptom b)
    {
        return a.getNaziv().compareTo(b.getNaziv());
    }
}
