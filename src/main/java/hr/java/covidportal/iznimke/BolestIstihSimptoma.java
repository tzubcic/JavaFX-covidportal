package main.java.hr.java.covidportal.iznimke;

/**
 * Bačena kada se za novu bolest unese ista lista simptoma koja je već unesena za neku drugu bolest
 *
 * @author Toni Zubcic
 */
public class BolestIstihSimptoma extends RuntimeException {

    /**
     * Konstruira <code>BolestIstihSImptoma</code>, sprema referencu na poruku o grešci za kasnije dohvaćanje preko <code>getMessage</code>.
     * @param message sadržaj poruke o grešci
     */

    public BolestIstihSimptoma(String message) {
        super(message);
    }

    /**
     *  Konstruira <code>BolestIstihSImptoma</code> sa referencom na poruku i uzrokom javljanja greške
     * @param message sadržaj poruke o grešci
     * @param cause uzrok koji je spremljen za kasnije dohvaćanje preko <code>Throwable.getCause</code>
     */

    public BolestIstihSimptoma(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     *  Konstruira <code>BolestIstihSImptoma</code> sa uzrokom javljanja greške i porukom
     *
     * @param cause uzrok koji je spremljen za kasnije dohvaćanje preko <code>Throwable.getCause</code>
     */

    public BolestIstihSimptoma(Throwable cause) {
        super(cause);
    }
}
