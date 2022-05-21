package main.java.hr.java.covidportal.main;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import main.java.hr.java.covidportal.enumeracije.VrijednostSimptoma;
import main.java.hr.java.covidportal.model.Bolest;
import main.java.hr.java.covidportal.model.Simptom;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DodavanjeNoveBolestiController implements Initializable {

    @FXML
    private TextField nazivNoveBolestiText;

    @FXML
    private TableView<Simptom> tablicaSimptoma;

    @FXML
    private TableColumn<Simptom, String> stupacIdSimptoma;
    @FXML
    private TableColumn<Simptom, String> stupacNazivaSimptoma;
    @FXML
    private TableColumn<Simptom, VrijednostSimptoma> stupacVrijednostiSimptoma;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stupacIdSimptoma
                .setCellValueFactory(new PropertyValueFactory<Simptom,String>("id"));

        stupacNazivaSimptoma
                .setCellValueFactory(new PropertyValueFactory<Simptom,String>("naziv"));
        stupacVrijednostiSimptoma
                .setCellValueFactory(new PropertyValueFactory<Simptom,VrijednostSimptoma>("vrijednost"));

        try {
            tablicaSimptoma.setItems(FXCollections.observableArrayList(Database.dohvatiSveSimptome()));
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }

        tablicaSimptoma.setRowFactory(tv -> {
            TableRow<Simptom> red = new TableRow<>();
            red.setOnMouseClicked(mouseEvent -> {
                if (mouseEvent.getClickCount() == 2 && (!red.isEmpty())) {
                    Simptom simptom = red.getItem();
                    try {
                        kliknuto(simptom);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return red;
        });

    }

    List<Simptom> listaSimptomaNoveBolesti = new ArrayList<>();

    public void spremi() throws IOException, SQLException {

        if(jesuSviParametriUneseni(nazivNoveBolestiText.getText())){
            String nazivNoveBolesti = nazivNoveBolestiText.getText();

            Bolest novaBolest = new Bolest(nazivNoveBolesti, false);
            novaBolest.setSimptomi(listaSimptomaNoveBolesti);

            Database.spremiNovuBolest(novaBolest);

            ispisUspjesnogUnosaBolesti(nazivNoveBolesti);

        }else{
            ispisNeuspjesnogUnosaBolesti();
        }

    }

    public void ispisNeuspjesnogUnosaBolesti() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Nova bolest");
        alert.setHeaderText("Neuspjesan unos!");
        alert.setContentText("Nedostaje parametar!");
        alert.showAndWait();
    }

    public void ispisUspjesnogUnosaBolesti(String nazivNoveBolesti) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Nova bolest");
        alert.setHeaderText("Uspjesan unos!");
        alert.setContentText("Uspjesno ste dodali novu bolest!\n" +
                "Naziv nove bolesti: " + nazivNoveBolesti);
        alert.showAndWait();
    }



    private void kliknuto(Simptom simptom) throws IOException {

        if(!jeVecUnesenSimptom(simptom)){
            listaSimptomaNoveBolesti.add(simptom);
            ispisUspjesnogUnosaSimptoma(simptom);
        }
        else {
            ispisNeuspjesnogUnosaSimptoma();
        }
    }

    private void ispisUspjesnogUnosaSimptoma(Simptom simptom) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Nova bolest");
        alert.setHeaderText("Dodam novi simptom " + simptom.getNaziv() +" !");
        alert.showAndWait();
    }

    private void ispisNeuspjesnogUnosaSimptoma() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Nova bolest");
        alert.setHeaderText("Neuspjesan unos!");
        alert.setContentText("Odabrali ste vec uneseni simptom! Molim ponovite odabir!");
        alert.showAndWait();
    }

    public boolean jeVecUnesenSimptom(Simptom simptom){
        for(Simptom x : listaSimptomaNoveBolesti){
            if(x.equals(simptom)){
                return true;
            }
        }
        return false;
    }

    public boolean jesuSviParametriUneseni(String nazivBolesti){
        if(nazivBolesti.isBlank() || listaSimptomaNoveBolesti.isEmpty()){
            return false;
        }
        return true;
    }

}
