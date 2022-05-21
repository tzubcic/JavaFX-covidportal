package main.java.hr.java.covidportal.main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import main.java.hr.java.covidportal.model.Zupanija;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DodavanjeNoveZupanijeController implements Initializable {

    @FXML
    private TextField nazivNoveZupanijeText;

    @FXML
    private TextField brojStanovnikaNoveZupanijeText;

    @FXML
    private TextField brojZarazenihNoveZupanijeText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    public void spremi() throws IOException, SQLException {

        if(jesuSviParametriUneseni(nazivNoveZupanijeText.getText(),
                brojStanovnikaNoveZupanijeText.getText(),
                brojZarazenihNoveZupanijeText.getText())){

            String nazivNoveZupanije = nazivNoveZupanijeText.getText();
            Integer brojStanovnikaNoveZupanije = Integer.parseInt(brojStanovnikaNoveZupanijeText.getText());
            Integer brojZarazenihNoveZupanije = Integer.parseInt(brojZarazenihNoveZupanijeText.getText());

            Zupanija novaZupanija = new Zupanija(
                    nazivNoveZupanije,
                    brojStanovnikaNoveZupanije,
                    brojZarazenihNoveZupanije);

            Database.spremiNovuZupaniju(novaZupanija);

            ispisUspjesnogUnosaZupanije(nazivNoveZupanije);


        }else {
            ispisNeuspjesnogUnosaZupanije();
        }

    }

    public void ispisUspjesnogUnosaZupanije(String nazivNoveZupanije) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Nova županija");
        alert.setHeaderText("Uspjesan unos!");
        alert.setContentText("Uspjesno ste dodali novu županiju!\n" +
                "Ime nove županije: " + nazivNoveZupanije);
        alert.showAndWait();
    }

    public void ispisNeuspjesnogUnosaZupanije() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Nova zupanija");
        alert.setHeaderText("Neuspjesan unos!");
        alert.setContentText("Nedostaje parametar!");
        alert.showAndWait();
    }


    public boolean jesuSviParametriUneseni(String naziv,String brStanovnika, String brZarazenih){
        if(naziv.isBlank() || brStanovnika.isBlank() || brZarazenih.isBlank()){

            return false;
        }
        return true;
    }
}
