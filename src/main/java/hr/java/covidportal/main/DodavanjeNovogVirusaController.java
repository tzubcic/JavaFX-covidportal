package main.java.hr.java.covidportal.main;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import main.java.hr.java.covidportal.enumeracije.VrijednostSimptoma;
import main.java.hr.java.covidportal.model.Simptom;
import main.java.hr.java.covidportal.model.Virus;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DodavanjeNovogVirusaController implements Initializable {

    @FXML
    private TextField nazivNovogVirusaText;

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

    List<Simptom> listaSimptomaNovogVirusa = new ArrayList<>();

    public void spremi() throws IOException, SQLException {

        if(jesuSviParametriUneseni(nazivNovogVirusaText.getText())){

            String nazivNovogVirusa = nazivNovogVirusaText.getText();

            Virus noviVirus = new Virus(nazivNovogVirusa,true);

            noviVirus.setSimptomi(listaSimptomaNovogVirusa);

            Database.spremiNovuBolest(noviVirus);

            ispisUspjesnogUnosaVirusa(nazivNovogVirusa);

        }else{
            ispisNeuspjesnogUnosaVirusa();
        }

    }

    public void ispisUspjesnogUnosaVirusa(String nazivNovogVirusa) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Novi virus");
        alert.setHeaderText("Uspjesan unos!");
        alert.setContentText("Uspjesno ste dodali novi virus!\n" +
                "Naziv novog virusa: " + nazivNovogVirusa);
        alert.showAndWait();
    }

    public void ispisNeuspjesnogUnosaVirusa() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Novi viruse");
        alert.setHeaderText("Neuspjesan unos!");
        alert.setContentText("Nedostaje parametar!");
        alert.showAndWait();
    }

    private void kliknuto(Simptom simptom) throws IOException {

        if(!jeUnesena(simptom)){
            listaSimptomaNovogVirusa.add(simptom);
            ispisUspjesnogUnosaSimptomaVirusa(simptom);
        }
        else {
            ispisNeuspjesnogUnosaSimptomaVirusa();
        }
    }

    private void ispisUspjesnogUnosaSimptomaVirusa(Simptom simptom) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Novi virus");
        alert.setHeaderText("Dodam novi simptom " + simptom.getNaziv() +" !");
        alert.showAndWait();
    }

    private void ispisNeuspjesnogUnosaSimptomaVirusa() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Novi virus");
        alert.setHeaderText("Neuspjesan unos!");
        alert.setContentText("Odabrali ste vec uneseni simptom! Molim ponovite odabir!");
        alert.showAndWait();
    }

    public boolean jeUnesena(Simptom simptom){
        for(Simptom x : listaSimptomaNovogVirusa){
            if(x.equals(simptom)){
                return true;
            }
        }
        return false;
    }

    public boolean jesuSviParametriUneseni(String nazivNovogVirusa){
        if(nazivNovogVirusa.isBlank() || listaSimptomaNovogVirusa.isEmpty()){
            return false;
        }
        return true;
    }
}
