package main.java.hr.java.covidportal.iznimke;

/**
 * Bačena kada se pri odabiru kontaktiranih osoba odabere prethodno već odabrana osoba
 *
 * @author Toni Zubcic
 */
public class DuplikatKontaktiraneOsobe extends Exception {

    /**
     * Konstruira <code>DuplikatKontaktiraneOsobe</code>, sprema referencu na poruku o grešci za kasnije dohvaćanje preko <code>getMessage</code>.
     * @param message sadržaj poruke o grešci
     */

    public DuplikatKontaktiraneOsobe(String message) {
        super(message);
    }

    /**
     *  Konstruira <code>DuplikatKontaktiraneOsobe</code> sa referencom na poruku i uzrokom javljanja greške
     * @param message sadržaj poruke o grešci
     * @param cause uzrok koji je spremljen za kasnije dohvaćanje preko <code>Throwable.getCause</code>
     */

    public DuplikatKontaktiraneOsobe(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     *  Konstruira <code>DuplikatKontaktiraneOsobe</code> sa uzrokom javljanja greške i porukom
     *
     * @param cause uzrok koji je spremljen za kasnije dohvaćanje preko <code>Throwable.getCause</code>
     */
    public DuplikatKontaktiraneOsobe(Throwable cause) {
        super(cause);
    }
}
