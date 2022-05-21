package main.java.hr.java.covidportal.main;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import main.java.hr.java.covidportal.model.Bolest;
import main.java.hr.java.covidportal.model.Osoba;
import main.java.hr.java.covidportal.model.Zupanija;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DodavanjeNoveOsobeController implements Initializable {

    @FXML
    private TextField imeNoveOsobeText;

    @FXML
    private TextField prezimeNoveOsobeText;

    @FXML
    private DatePicker starostNoveOsobeDate;

    @FXML
    private ChoiceBox<Zupanija> choiceBoxZupanija = new ChoiceBox<>();

    @FXML
    private ChoiceBox<Bolest> choiceBoxBolest = new ChoiceBox<>();

    @FXML
    private TableView<Osoba> tablicaOsoba;

    @FXML
    private TableColumn<Osoba, String> stupacImeOsobe;

    @FXML
    private TableColumn<Osoba, String> stupacPrezimeOsobe;

    @FXML
    private TableColumn<Osoba, String> stupacZupanijaOsoba;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Database.dohvatiSveZupanije()
                    .forEach(zupanija -> choiceBoxZupanija.getItems().add(zupanija));
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }

        try {
            Database.dohvatiSveBolesti()
                    .forEach(bolest ->  choiceBoxBolest.getItems().add(bolest));
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }

        stupacImeOsobe
                .setCellValueFactory(new PropertyValueFactory<Osoba,String>("ime"));
        stupacPrezimeOsobe
                .setCellValueFactory(new PropertyValueFactory<Osoba,String>("prezime"));

        stupacZupanijaOsoba
                .setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getZupanija().getNaziv()));

        try {
            tablicaOsoba.setItems(FXCollections.observableArrayList(Database.dohvatiSveOsobe()));
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }

        tablicaOsoba.setRowFactory(tv -> {
            TableRow<Osoba> red = new TableRow<>();
            red.setOnMouseClicked(mouseEvent -> {
                if (mouseEvent.getClickCount() == 2 && (!red.isEmpty())) {
                    Osoba osoba = red.getItem();
                    try {
                        kliknuto(osoba);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return red;
        });
    }

    List<Osoba> listaKontaktiranihOsobaNoveOsobe = new ArrayList<>();

    public void spremi() throws IOException, SQLException {

        if(jesuSviParametriUneseni(imeNoveOsobeText.getText(),
                                prezimeNoveOsobeText.getText(),
                                starostNoveOsobeDate.getValue())){

            String imeNoveOsobe = imeNoveOsobeText.getText();
            String prezimeNoveOsobe = prezimeNoveOsobeText.getText();

            LocalDate localDate = starostNoveOsobeDate.getValue();

            Database.spremiNovuOsobu(
                    new Osoba.Builder()
                            .saIme(imeNoveOsobe)
                            .saPrezime(prezimeNoveOsobe)
                            .saDatumRodjenja(localDate)
                            .uZupanija(choiceBoxZupanija.getValue())
                            .imaBolest(choiceBoxBolest.getValue())
                            .imaKontakte(listaKontaktiranihOsobaNoveOsobe)
                            .build()
            );

            ispisUspjesnogUnosa(imeNoveOsobe, prezimeNoveOsobe);
        }

        else {
            ispisNeuspjesnogUnosa();
        }

    }

    public void ispisNeuspjesnogUnosa() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Nova osoba");
        alert.setHeaderText("Neuspjesan unos!");
        alert.setContentText("Nedostaje parametar!");
        alert.showAndWait();
    }

    public void ispisUspjesnogUnosa(String imeNoveOsobe, String prezimeNoveOsobe) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Nova osoba");
        alert.setHeaderText("Uspjesan unos!");
        alert.setContentText("Uspjesno ste dodali novu osobu!\n" +
                "Ime nove osobe: " + imeNoveOsobe +
                "\n" +
                "prezime nove osobe: " + prezimeNoveOsobe);
        alert.showAndWait();
    }

    public boolean jesuSviParametriUneseni(String ime,String prezime, LocalDate starost){
        if(ime.isBlank() || prezime.isBlank() || starost == null ||
                choiceBoxZupanija.getValue() == null ||
                choiceBoxBolest.getValue() == null){

            return false;
        }
        return true;
    }

    public void kliknuto(Osoba osoba) throws IOException {

            if(!jeUnesena(osoba)){
                listaKontaktiranihOsobaNoveOsobe.add(osoba);
                ispisUspjesnogUnosaKontakta();

            }
            else {
                ispisNeuspjesnogUnosaKontakta();
            }

    }

    public void ispisNeuspjesnogUnosaKontakta() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Nova osoba");
        alert.setHeaderText("Neuspjesan unos!");
        alert.setContentText("Unijeli ste vec uneseni kontakt! Molim ponovite!");
        alert.showAndWait();
    }

    public void ispisUspjesnogUnosaKontakta() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Nova osoba");
        alert.setHeaderText("Uspjesan unos!");
        alert.setContentText("Uspjesno ste dodali novi kontakt!");
        alert.showAndWait();
    }

    public boolean jeUnesena(Osoba osoba){
        for(Osoba x : listaKontaktiranihOsobaNoveOsobe){
            if(x.equals(osoba)){
                return true;
            }
        }
        return false;
    }

}
