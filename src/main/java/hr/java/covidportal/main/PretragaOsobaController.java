package main.java.hr.java.covidportal.main;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import main.java.hr.java.covidportal.model.Osoba;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class PretragaOsobaController implements Initializable {

    ObservableList<Osoba> listaObservableOsoba;

    @FXML
    private TextField imeOsobe;
    @FXML
    private TextField prezimeOsobe;


    @FXML
    private TableView<Osoba> tablicaOsoba;
    @FXML
    private TableColumn<Osoba, String> stupacImeOsobe;

    @FXML
    private TableColumn<Osoba, String> stupacPrezimeOsobe;

    @FXML
    private TableColumn<Osoba, String> stupacStarostOsobe;

    @FXML
    private TableColumn<Osoba, String> stupacZupanijaOsoba;

    @FXML
    private TableColumn<Osoba, String> stupacBolestOsobe;

    @FXML
    private TableColumn<Osoba, String> stupacKontaktiraneOsobe;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stupacImeOsobe
                .setCellValueFactory(new PropertyValueFactory<Osoba,String>("ime"));
        stupacPrezimeOsobe
                .setCellValueFactory(new PropertyValueFactory<Osoba,String>("prezime"));
        stupacStarostOsobe
                .setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().dohvatiStarost().toString()));
        stupacZupanijaOsoba
                .setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getZupanija().getNaziv()));
        stupacBolestOsobe
                .setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getZarazenBolescu().getNaziv()));
        stupacKontaktiraneOsobe
                .setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getImenaPrezimenaKontakata()));


        try {
            listaObservableOsoba = FXCollections.observableArrayList(Database.dohvatiSveOsobe());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        tablicaOsoba.setItems(listaObservableOsoba);
    }

    public void pretraga() throws IOException, SQLException {
        String imeText = imeOsobe.getText();
        String prezimeText = prezimeOsobe.getText();

        List<Osoba> listaFiltriranihOsoba = FXCollections.observableArrayList(Database.dohvatiSveOsobe())
                .stream()
                .filter(osoba -> osoba.getIme().toLowerCase().contains(imeText.toLowerCase()))
                .filter(osoba -> osoba.getPrezime().toLowerCase().contains(prezimeText.toLowerCase()))
                .collect(Collectors.toList());

        listaObservableOsoba.clear();
        listaObservableOsoba.addAll(FXCollections.observableArrayList(listaFiltriranihOsoba));

    }

}
