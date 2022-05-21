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
import main.java.hr.java.covidportal.model.Bolest;
import main.java.hr.java.covidportal.model.Virus;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class PretragaVirusaController implements Initializable {

    ObservableList<Bolest> listaObservableVirusa;

    @FXML
    private TextField nazivVirusa;

    @FXML
    private TableView<Bolest> tablicaVirusa;
    @FXML
    private TableColumn<Virus, String> stupacNazivVirusa;
    @FXML
    private TableColumn<Virus, String> stupacListaSimptomaVirusa;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stupacNazivVirusa
                .setCellValueFactory(new PropertyValueFactory<Virus,String>("naziv"));
        stupacListaSimptomaVirusa
                .setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getImenaIzListeSimptoma()));


        try {
            listaObservableVirusa = FXCollections.observableArrayList(Database.dohvatiSveBolesti().stream()
            .filter(bolest -> bolest instanceof Virus)
            .collect(Collectors.toList()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        tablicaVirusa.setItems(listaObservableVirusa);
    }

    public void pretraga() throws IOException, SQLException {
        String nazivVirusaText = nazivVirusa.getText();

        List<Bolest> listaFiltriranihVirusa = FXCollections.observableArrayList(Database.dohvatiSveBolesti())
                .stream()
                .filter(bolest -> bolest instanceof Virus)
                .filter(virus -> virus.getNaziv().toLowerCase().contains(nazivVirusaText.toLowerCase()))
                .collect(Collectors.toList());


        listaObservableVirusa.clear();
        listaObservableVirusa.addAll(FXCollections.observableArrayList(listaFiltriranihVirusa));

    }

}
