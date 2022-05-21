package main.java.hr.java.covidportal.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import main.java.hr.java.covidportal.model.Zupanija;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class PretragaZupanijaController implements Initializable {

    ObservableList<Zupanija> listaObservableZupanija;

    @FXML
    private TextField nazivZupanije;

    @FXML
    private TableView<Zupanija> tablicaZupanija;
    @FXML
    private TableColumn<Zupanija, String> stupacNazivZupanije;
    @FXML
    private TableColumn<Zupanija, Integer> stupacBrStanovnika;
    @FXML
    private TableColumn<Zupanija, Integer> stupacBrZaraženih;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stupacNazivZupanije
                .setCellValueFactory(new PropertyValueFactory<Zupanija,String>("naziv"));
        stupacBrStanovnika
                .setCellValueFactory(new PropertyValueFactory<Zupanija,Integer>("brojStanovnika"));
        stupacBrZaraženih
                .setCellValueFactory(new PropertyValueFactory<Zupanija,Integer>("brojZarazenih"));

        try {
            listaObservableZupanija = FXCollections.observableArrayList(Database.dohvatiSveZupanije());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        tablicaZupanija.setItems(listaObservableZupanija);
    }

    public void pretraga() throws IOException, SQLException {
        String nazivZupanijeText = nazivZupanije.getText();

        List<Zupanija> listaFiltriranihKlubova = FXCollections.observableArrayList(Database.dohvatiSveZupanije())
                .stream()
                .filter(zupanija -> zupanija.getNaziv().toLowerCase().contains(nazivZupanijeText.toLowerCase()))
                .collect(Collectors.toList());

        listaObservableZupanija.clear();
        listaObservableZupanija.addAll(FXCollections.observableArrayList(listaFiltriranihKlubova));

    }

}
