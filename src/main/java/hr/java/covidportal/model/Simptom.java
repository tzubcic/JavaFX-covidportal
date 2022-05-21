package main.java.hr.java.covidportal.model;

import main.java.hr.java.covidportal.enumeracije.VrijednostSimptoma;

import java.util.List;
import java.util.Objects;

/**
 * Predstavlja entitet koji je definiran nazivom i vrijednosti, te nasljeđuje
 * apstraktnu klasu <code>ImenovaniEntitet</code>
 *
 * @author Toni Zubcic
 */

public class Simptom extends ImenovaniEntitet {

    private VrijednostSimptoma vrijednost;

    /**
     * Inicijalizira podatke o nazivu i vrijednosti
     *
     * @param naziv podatak o nazivu
     * @param vrijednost podatak o vrijednosti
     */

    public Simptom(String naziv, VrijednostSimptoma vrijednost) {
        super(naziv);
        this.vrijednost = vrijednost;
    }

    public VrijednostSimptoma getVrijednost() {
        return vrijednost;
    }

    public void setVrijednost(VrijednostSimptoma vrijednost) {
        this.vrijednost = vrijednost;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNaziv());
    }

    /**
     * Služi za uspredbu trenutnog objekta sa drugim <code>obj</code> objektom na temelju naziva
     * ukoliko su objekti jednaki vraća <code>true</code>,
     * ukoliko su različiti vraća <code>false</code>
     *
     * @param obj objekt s kojim će se trenutni objekt klase <code>Simptom</code> uspoređivati
     * @return vraća vrijednost usporedbe <code>true</code> ukoliko su jednaki, ako nisu <code>false</code>
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Simptom))
            return false;

        Simptom s = (Simptom) obj;
        return this.getNaziv().equals(s.getNaziv()) &&
                this.getVrijednost().equals(s.getVrijednost());

    }


    @Override
    public String toString() {
        return this.getNaziv();
    }
}
