package main.java.hr.java.covidportal.iznimke;

/**
 * Bačena kada se pri odabiru broja kontaktiranih osoba odabere veci broj od trenutno unesenih osoba
 *
 * @author Toni Zubcic
 */

public class VisakOsobaUKontaktu extends Exception {

    /**
     * Konstruira <code>VisakOsobaUKontaktu</code>, sprema referencu na poruku o grešci za kasnije dohvaćanje preko <code>getMessage</code>.
     * @param message sadržaj poruke o grešci
     */
    public VisakOsobaUKontaktu(String message) {
        super(message);
    }

    /**
     *  Konstruira <code>VisakOsobaUKontaktu</code> sa referencom na poruku i uzrokom javljanja greške
     * @param message sadržaj poruke o grešci
     * @param cause uzrok koji je spremljen za kasnije dohvaćanje preko <code>Throwable.getCause</code>
     */
    public VisakOsobaUKontaktu(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     *  Konstruira <code>VisakOsobaUKontaktu</code> sa uzrokom javljanja greške i porukom
     *
     * @param cause uzrok koji je spremljen za kasnije dohvaćanje preko <code>Throwable.getCause</code>
     */
    public VisakOsobaUKontaktu(Throwable cause) {
        super(cause);
    }
}
