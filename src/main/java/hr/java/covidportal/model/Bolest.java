package main.java.hr.java.covidportal.model;

import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Predstavlja entitet koji je definiran nazivom i listom simptoma, te nasljeđuje
 * apstraktnu klasu <code>ImenovaniEntitet</code>
 *
 * @author Toni Zubcic
 */

public class Bolest extends ImenovaniEntitet {

    boolean virus;
    private List<Simptom> simptomi;

    /**
     * Inicijalizira podatke o nazivu i polju simptoma
     *
     * @param naziv podatak o nazivu
     * @param virus da li je bolest virus
     */

    public Bolest(String naziv, boolean virus) {
        super(naziv);
        this.virus = virus;
    }

    public boolean isVirus() {
        return virus;
    }

    public void setVirus(boolean virus) {
        this.virus = virus;
    }

    public List<Simptom> getSimptomi() {
        return simptomi;
    }

    public void setSimptomi(List<Simptom> simptomi) {
        this.simptomi = simptomi;
    }

    public String getImenaIzListeSimptoma(){
        List<Simptom> ovi = simptomi;
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        for(Simptom s : ovi){
            cnt++;
            if(cnt != ovi.size()){
                sb.append(s.getNaziv() + ", ");
            }
            else{
                sb.append(s.getNaziv());
            }
        }
        return sb.toString();
    }


    @Override
    public String toString() {
        return this.getNaziv();
    }

    /**
     *  služi za usporedbu dva objekta na temelju vrijednosti naziva, ukoliko su
     *  razlicitog tipa, razlicitih vrijednosti ili smo proslijedili prazan parametar
     *  vraca <code>false</code>, ukoliko su isti
     *  i istih vrijednosti vraca <code>true</code>
     * @param o objekt koji uspoređujemo sa trenutnim
     * @return vraca odgovarajucu <code>boolean</code> vrijednost usporedbe
     */


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bolest bolest = (Bolest) o;
        return Objects.equals(this.getNaziv(),bolest.getNaziv());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNaziv());
    }
}
