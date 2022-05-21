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

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class PretragaBolestiController implements Initializable {

    ObservableList<Bolest> listaObservableBolesti;

    @FXML
    private TextField nazivBolesti;

    @FXML
    private TableView<Bolest> tablicaBolesti;
    @FXML
    private TableColumn<Bolest, String> stupacNazivBolesti;
    @FXML
    private TableColumn<Bolest, String> stupacListaSimptomaBolesti;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stupacNazivBolesti
                .setCellValueFactory(new PropertyValueFactory<Bolest,String>("naziv"));
        stupacListaSimptomaBolesti
                .setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getImenaIzListeSimptoma()));


        try {
            listaObservableBolesti = FXCollections.observableArrayList(Database.dohvatiSveBolesti());
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }

        tablicaBolesti.setItems(listaObservableBolesti);
    }

    public void pretraga() throws IOException, SQLException {
        String nazivBolestiText = nazivBolesti.getText();

        List<Bolest> listaFiltriranihBolesti = FXCollections.observableArrayList(Database.dohvatiSveBolesti())
                .stream()
                .filter(bolest -> bolest.getNaziv().toLowerCase().contains(nazivBolestiText.toLowerCase()))
                .collect(Collectors.toList());


        listaObservableBolesti.clear();
        listaObservableBolesti.addAll(FXCollections.observableArrayList(listaFiltriranihBolesti));

    }

}
