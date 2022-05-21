package main.java.hr.java.covidportal.enumeracije;

/**
 * Vrijednosti simptoma koje možemo dodijeliti pojedinim simptomima prilikom njihovog unosa
 *
 */
public enum VrijednostSimptoma {

    PRODUKTIVNI("Produktivni"),

    INTENZIVNO("Intenzivno"),

    JAKA("Jaka"),

    VISOKA("Visoka");

    /**
     * vrijednost konstante enuma
     */
    private String vrijednost;

    /**
     * konstruktor koji se ne može pozivati u programu
     * @param vrijednost vrijednost učestalosti simptoma
     */
    private VrijednostSimptoma(String vrijednost) {
        this.vrijednost = vrijednost;
    }

    public String getVrijednost() {
        return vrijednost;
    }

    /**
     * Vraća konstantu čija <code>vrijednost</code> odgovara parametru <code>vrijednostStringa</code>, ukoliko
     * unesena vrijednost ne odgovara vrijednostima konstanti, vraća se iznimka <code>IllegalArgumentException</code>
     *
     * @param vrijednostStringa vrijednost koju unosimo za usporedbu sa vrijednostima konstanti
     * @return vraća konstantu određene vrijednosti
     */
    public static VrijednostSimptoma zaVrijednost(String vrijednostStringa){
        for(VrijednostSimptoma vs : values()){
            if (vs.getVrijednost().equals(vrijednostStringa)){
                return vs;
            }
        }
        throw new IllegalArgumentException("Unesena je vrijednost koja ne odgovara mogućim vrijednostima konstanti");
    }

}
