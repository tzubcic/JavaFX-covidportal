package main.java.hr.java.covidportal.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import main.java.hr.java.covidportal.enumeracije.VrijednostSimptoma;
import main.java.hr.java.covidportal.model.Simptom;
import main.java.hr.java.covidportal.model.Zupanija;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class PretragaSimptomaController implements Initializable {

    ObservableList<Simptom> listaObservableSimptoma;


    @FXML
    private TextField nazivSimptoma;

    @FXML
    private TableView<Simptom> tablicaSimptoma;
    @FXML
    private TableColumn<Simptom, String> stupacNazivaSimptoma;
    @FXML
    private TableColumn<Simptom, VrijednostSimptoma> stupacVrijednostiSimptoma;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stupacNazivaSimptoma
                .setCellValueFactory(new PropertyValueFactory<Simptom,String>("naziv"));
        stupacVrijednostiSimptoma
                .setCellValueFactory(new PropertyValueFactory<Simptom,VrijednostSimptoma>("vrijednost"));


        try {
            listaObservableSimptoma = FXCollections.observableArrayList(Database.dohvatiSveSimptome());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        tablicaSimptoma.setItems(listaObservableSimptoma);
    }

    public void pretraga() throws IOException, SQLException {
        String nazivSimptomaText = nazivSimptoma.getText();

        List<Simptom> listaFiltriranihSimptoma = FXCollections.observableArrayList(Database.dohvatiSveSimptome())
                .stream()
                .filter(simptom -> simptom.getNaziv().toLowerCase().contains(nazivSimptomaText.toLowerCase()))
                .collect(Collectors.toList());


        listaObservableSimptoma.clear();
        listaObservableSimptoma.addAll(FXCollections.observableArrayList(listaFiltriranihSimptoma));

    }

    public void setListaObservableSimptoma(ObservableList<Simptom> observableSimptoma){
        listaObservableSimptoma = observableSimptoma;
    }

}
