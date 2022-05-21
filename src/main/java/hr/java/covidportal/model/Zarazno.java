package main.java.hr.java.covidportal.model;

/**
 * Predstavlja sučelje koje će opisati svojstvo entiteta <code>Virus</code>
 *
 * @author Toni Zubcic
 */

public interface Zarazno {

    /**
     *
     * Implementacija na {@link Virus#prelazakZarazeNaOsobu(Osoba)}  }
     */
    void prelazakZarazeNaOsobu(Osoba osoba);
}
