package main.java.hr.java.covidportal.main;

import main.java.hr.java.covidportal.enumeracije.VrijednostSimptoma;
import main.java.hr.java.covidportal.model.*;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Database {

    private static final String DATABASE_CONFIGURATION_FILE = "src\\main\\resources\\database.properties";

    private static Connection connectToDatabase() throws SQLException, IOException {

        Properties svojstva = new Properties();
        svojstva.load(new FileReader(DATABASE_CONFIGURATION_FILE));
        String urlBazePodataka = svojstva.getProperty("bazaPodatakaUrl");
        String korisnickoIme = svojstva.getProperty("korisnickoIme");
        String lozinka = svojstva.getProperty("lozinka");

        Connection veza = DriverManager.getConnection(urlBazePodataka, korisnickoIme, lozinka);

        return veza;
    }

    private static void closeConnectionToDatabase(Connection connection) throws SQLException {
        connection.close();
    }

    public static List<Zupanija> dohvatiSveZupanije() throws SQLException, IOException {
        List<Zupanija> listaZupanija = new ArrayList<>();

        Connection veza = connectToDatabase();

        Statement smt = veza.createStatement();
        ResultSet rs = smt.executeQuery("SELECT * FROM ZUPANIJA");

        while (rs.next()){

            long id = rs.getLong("id");
            String naziv = rs.getString("naziv");
            Integer brojStanovnika = rs.getInt("broj_stanovnika");
            Integer brojZarazenih = rs.getInt("broj_zarazenih_stanovnika");

            Zupanija novaZupanija = new Zupanija(naziv, brojStanovnika, brojZarazenih);
            novaZupanija.setId(id);

            listaZupanija.add(novaZupanija);

        }

        closeConnectionToDatabase(veza);

        return listaZupanija;
    }

    public static Zupanija dohvatZupanijePoId(long idPretrage, Connection veza) throws IOException, SQLException {

        PreparedStatement upit = veza.prepareStatement("SELECT * FROM ZUPANIJA WHERE ID = ?");
        upit.setLong(1,idPretrage);

        ResultSet rs = upit.executeQuery();

        Zupanija novaZupanija = null;

        while (rs.next()){
            long id = rs.getLong("id");
            String naziv = rs.getString("naziv");
            Integer brojStanovnika = rs.getInt("broj_stanovnika");
            Integer brojZarazenih = rs.getInt("broj_zarazenih_stanovnika");

            novaZupanija = new Zupanija(naziv, brojStanovnika, brojZarazenih);
            novaZupanija.setId(id);
        }

        return novaZupanija;
    }

    public static void spremiNovuZupaniju(Zupanija novaZupanija) throws SQLException, IOException {
        Connection veza = connectToDatabase();

        PreparedStatement upit = veza.prepareStatement(
                "INSERT INTO ZUPANIJA(naziv, broj_stanovnika, broj_zarazenih_stanovnika) VALUES(?,?,?)");

        upit.setString(1, novaZupanija.getNaziv());
        upit.setInt(2,novaZupanija.getBrojStanovnika());
        upit.setInt(3,novaZupanija.getBrojZarazenih());

        upit.executeUpdate();

        closeConnectionToDatabase(veza);
    }

    public static List<Simptom> dohvatiSveSimptome() throws SQLException, IOException {
        List<Simptom> listaSimptoma = new ArrayList<>();

        Connection veza = connectToDatabase();

        Statement smt = veza.createStatement();
        ResultSet rs = smt.executeQuery("SELECT * FROM SIMPTOM");

        while (rs.next()){

            long id = rs.getLong("id");
            String naziv = rs.getString("naziv");
            VrijednostSimptoma vrijednostSimptoma = VrijednostSimptoma.zaVrijednost(rs.getString("vrijednost"));

            Simptom novisimptoma = new Simptom(naziv, vrijednostSimptoma);
            novisimptoma.setId(id);

            listaSimptoma.add(novisimptoma);
        }

        closeConnectionToDatabase(veza);

        return listaSimptoma;
    }

    public static Simptom dohvatSimptomaPoId(long idPretrage, Connection veza) throws IOException, SQLException {

        PreparedStatement upit = veza.prepareStatement("SELECT * FROM SIMPTOM WHERE ID = ?");
        upit.setLong(1,idPretrage);

        ResultSet rs = upit.executeQuery();

        Simptom noviSimptom = null;

        while (rs.next()){
            long id = rs.getLong("id");
            String naziv = rs.getString("naziv");
            VrijednostSimptoma vrijednostSimptoma = VrijednostSimptoma.zaVrijednost(rs.getString("vrijednost"));

            noviSimptom = new Simptom(naziv, vrijednostSimptoma);
            noviSimptom.setId(id);
        }

        return noviSimptom;
    }

    public static void spremiNoviSimptom(Simptom noviSimptom) throws SQLException, IOException {
        Connection veza = connectToDatabase();

        PreparedStatement upit = veza.prepareStatement(
                "INSERT INTO SIMPTOM(naziv, vrijednost) VALUES(?,?)");

        upit.setString(1, noviSimptom.getNaziv());
        upit.setString(2,noviSimptom.getVrijednost().getVrijednost());

        upit.executeUpdate();

        closeConnectionToDatabase(veza);
    }

    public static List<Bolest> dohvatiSveBolesti() throws SQLException, IOException {
        List<Bolest> listaBolesti = new ArrayList<>();

        Connection veza = connectToDatabase();

        Statement smt = veza.createStatement();
        ResultSet rs = smt.executeQuery("SELECT * FROM BOLEST");

        while (rs.next()){

            long id = rs.getLong("id");
            String naziv = rs.getString("naziv");
            boolean virus = rs.getBoolean("virus");

            List<Simptom> listaSimptoma = new ArrayList<>();

            PreparedStatement upit2 = veza.prepareStatement("SELECT * FROM BOLEST_SIMPTOM WHERE BOLEST_ID = ?");
            upit2.setLong(1,id);
            ResultSet rs2 = upit2.executeQuery();

            while (rs2.next()){
                long idBolesti = rs2.getLong("bolest_id");
                long idSimptoma = rs2.getLong("simptom_id");

                listaSimptoma.add(dohvatSimptomaPoId(idSimptoma,veza));
            }

            if(virus){
                Virus noviVirus = new Virus(naziv, virus);
                noviVirus.setId(id);
                noviVirus.setSimptomi(listaSimptoma);

                listaBolesti.add(noviVirus);
            }
            else {
                Bolest novaBolest = new Bolest(naziv, virus);
                novaBolest.setId(id);
                novaBolest.setSimptomi(listaSimptoma);

                listaBolesti.add(novaBolest);
            }
        }

        closeConnectionToDatabase(veza);

        return listaBolesti;
    }

    public static Bolest dohvatiBolestPodId(long idPretrage, Connection veza) throws IOException, SQLException {

        PreparedStatement upit = veza.prepareStatement("SELECT * FROM BOLEST WHERE ID = ?");
        upit.setLong(1,idPretrage);

        ResultSet rs = upit.executeQuery();

        Bolest novaBolest = null;

        while (rs.next()) {

            long id = rs.getLong("id");
            String naziv = rs.getString("naziv");
            boolean virus = rs.getBoolean("virus");

            novaBolest = new Bolest(naziv, virus);
            novaBolest.setId(id);
        }

        return novaBolest;
    }

    public static void spremiNovuBolest(Bolest novaBolest) throws SQLException, IOException {
        Connection veza = connectToDatabase();

        long idNoveBolesti = -1;

        PreparedStatement upit = veza.prepareStatement(
                "INSERT INTO BOLEST(naziv, virus) VALUES(?,?)",PreparedStatement.RETURN_GENERATED_KEYS);

        upit.setString(1, novaBolest.getNaziv());
        upit.setBoolean(2,novaBolest.isVirus());
        upit.executeUpdate();

        ResultSet rs = upit.getGeneratedKeys();

        if (rs.next()){
           idNoveBolesti = rs.getLong("id");
        }

        PreparedStatement upit2 = veza.prepareStatement(
                "INSERT INTO BOLEST_SIMPTOM(bolest_id, simptom_id) VALUES(?,?)");

        for(Simptom s : novaBolest.getSimptomi()){
            upit2.setLong(1,idNoveBolesti);
            upit2.setLong(2,s.getId());
            upit2.executeUpdate();
        }

        closeConnectionToDatabase(veza);
    }


    public static List<Osoba> dohvatiSveOsobe() throws SQLException, IOException {
        List<Osoba> listaOsoba = new ArrayList<>();

        Connection veza = connectToDatabase();

        Statement smt = veza.createStatement();
        ResultSet rs = smt.executeQuery("SELECT * FROM OSOBA");

        while (rs.next()){

            long id = rs.getLong("id");
            String imeOsobe = rs.getString("ime");
            String prezimeOsobe = rs.getString("prezime");
            Date date = (Date) rs.getDate("datum_rodjenja");

            Instant instant = Instant.ofEpochMilli(date.getTime());
            LocalDate localDate = LocalDateTime.ofInstant(
                    instant, ZoneId.systemDefault()).toLocalDate();

            Long idZupanijeOsobe = rs.getLong("zupanija_id");
            Long idBolestiOsobe = rs.getLong("bolest_id");

            List<Osoba> listaKontakata = new ArrayList<>();

            PreparedStatement upit2 = veza.prepareStatement("SELECT * FROM KONTAKTIRANE_OSOBE  WHERE OSOBA_ID = ?");
            upit2.setLong(1,id);
            ResultSet rs2 = upit2.executeQuery();

            while (rs2.next()){
                long idOsobe = rs2.getLong("osoba_id");
                long idKontaktiraneOsobe = rs2.getLong("kontaktirana_osoba_id");

                listaKontakata.add(dohvatiOsobuPoId(idKontaktiraneOsobe,veza));
            }

            Osoba novaOsoba =
                    new Osoba.Builder()
                            .saIme(imeOsobe)
                            .saPrezime(prezimeOsobe)
                            .saDatumRodjenja(localDate)
                            .uZupanija(dohvatZupanijePoId(idZupanijeOsobe,veza))
                            .imaBolest(dohvatiBolestPodId(idBolestiOsobe,veza))
                            .imaKontakte(listaKontakata)
                            .build();
            novaOsoba.setId(id);

            listaOsoba.add(novaOsoba);

        }

        closeConnectionToDatabase(veza);

        return listaOsoba;
    }

    public static Osoba dohvatiOsobuPoId(long idPretrage, Connection veza) throws IOException, SQLException {

        PreparedStatement upit = veza.prepareStatement("SELECT * FROM OSOBA WHERE ID = ?");
        upit.setLong(1,idPretrage);

        ResultSet rs = upit.executeQuery();

        Osoba novaOsoba = null;

        while (rs.next()){

            long id = rs.getLong("id");
            String imeOsobe = rs.getString("ime");
            String prezimeOsobe = rs.getString("prezime");
            Date date = (Date) rs.getDate("datum_rodjenja");

            Instant instant = Instant.ofEpochMilli(date.getTime());
            LocalDate localDate = LocalDateTime.ofInstant(
                    instant, ZoneId.systemDefault()).toLocalDate();

            Long idZupanijeOsobe = rs.getLong("zupanija_id");
            Long idBolestiOsobe = rs.getLong("bolest_id");

            novaOsoba =
                    new Osoba.Builder()
                            .saIme(imeOsobe)
                            .saPrezime(prezimeOsobe)
                            .saDatumRodjenja(localDate)
                            .uZupanija(dohvatZupanijePoId(idZupanijeOsobe,veza))
                            .imaBolest(dohvatiBolestPodId(idBolestiOsobe,veza))
                            .build();
            novaOsoba.setId(id);
        }

        return novaOsoba;
    }

    public static void spremiNovuOsobu(Osoba novaOsoba) throws SQLException, IOException {
        Connection veza = connectToDatabase();

        long idNoveOsobe = -1;

        PreparedStatement upit = veza.prepareStatement(
                "INSERT INTO OSOBA(ime, prezime,datum_rodjenja,zupanija_id, bolest_id) VALUES(?,?,?,?,?)"
                ,PreparedStatement.RETURN_GENERATED_KEYS);

        upit.setString(1, novaOsoba.getIme());
        upit.setString(2, novaOsoba.getPrezime());

        LocalDate localDate = novaOsoba.getDatumRodjenja();
        Date date = Date.valueOf(localDate);
        upit.setDate(3, date);

        upit.setLong(4,novaOsoba.getZupanija().getId());
        upit.setLong(5,novaOsoba.getZarazenBolescu().getId());

        upit.executeUpdate();

        ResultSet rs = upit.getGeneratedKeys();

        if (rs.next()){
            idNoveOsobe = rs.getLong("id");
        }

        PreparedStatement upit2 = veza.prepareStatement(
                "INSERT INTO KONTAKTIRANE_OSOBE(osoba_id, kontaktirana_osoba_id) VALUES(?,?)");

        for(Osoba o : novaOsoba.getKontaktiraneOsobe()){
            upit2.setLong(1,idNoveOsobe);
            upit2.setLong(2,o.getId());
            upit2.executeUpdate();
        }
        closeConnectionToDatabase(veza);
    }

}
