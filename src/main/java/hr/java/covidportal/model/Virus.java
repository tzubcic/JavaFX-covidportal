package main.java.hr.java.covidportal.model;

import java.util.List;
import java.util.Set;

/**
 * Predstavlja entitet sa vrijednostima naziv i poljem simptoma,
 * implementira sučelje Zarazno
 *
 * @author Toni Zubcic
 */

public class Virus extends Bolest implements Zarazno {

    /**
     * Inicijalizacija pdataka o nazivu i polju simptoma
     *
     * @param naziv podatak o nazivu
     */

    public Virus(String naziv, boolean virus) {
        super(naziv, virus);
    }



    /**
     * Služi da objektu <code>osoba</code> promijeni vrijednosti <code>zarazenBolescu</code> na vrijednosti
     * trenutnog objekta entiteta <code>Virus</code>
     *
     * @param osoba objekt kojem se mijenja vrijednost <code>zarazenBolescu</code>
     */
    @Override
    public void prelazakZarazeNaOsobu(Osoba osoba) {
        osoba.setZarazenBolescu(this);
    }


}
