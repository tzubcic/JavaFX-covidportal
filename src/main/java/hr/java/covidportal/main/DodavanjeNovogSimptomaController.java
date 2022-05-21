package main.java.hr.java.covidportal.main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import main.java.hr.java.covidportal.enumeracije.VrijednostSimptoma;
import main.java.hr.java.covidportal.model.Simptom;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DodavanjeNovogSimptomaController implements Initializable {

    @FXML
    private TextField nazivNovogSimptomaText;

    @FXML
    private RadioButton rbPRODUKTIVNI;

    @FXML
    private RadioButton rbINTENZIVNO;

    @FXML
    private RadioButton rbJAKA;

    @FXML
    private RadioButton rbVISOKA;

    @FXML
    private ToggleGroup tgVrijednosti;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    public void spremi() throws IOException, SQLException {

        if(jesuSviParametriUneseni(nazivNovogSimptomaText.getText())){

            String nazivNovogSimptoma = nazivNovogSimptomaText.getText();

            VrijednostSimptoma vrijednostNovogSimptoma;

            if(rbJAKA.isSelected()){
                vrijednostNovogSimptoma = VrijednostSimptoma.JAKA;
            }
            else if(rbVISOKA.isSelected()){
                vrijednostNovogSimptoma = VrijednostSimptoma.VISOKA;
            }
            else if(rbINTENZIVNO.isSelected()) {
                vrijednostNovogSimptoma = VrijednostSimptoma.INTENZIVNO;
            }
            else if(rbPRODUKTIVNI.isSelected()) {
                vrijednostNovogSimptoma = VrijednostSimptoma.PRODUKTIVNI;
            }
            else {
                vrijednostNovogSimptoma = null;
            }

            Simptom noviSimptom = new Simptom(nazivNovogSimptoma,vrijednostNovogSimptoma);

            Database.spremiNoviSimptom(noviSimptom);

            ispisUspjesnogUnosaSimptoma(nazivNovogSimptoma);

        }else {
            ispisNeuspjesnogUnosaSimptoma();
        }

    }

    public void ispisUspjesnogUnosaSimptoma(String nazivNovogSimptoma) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Novi simptom");
        alert.setHeaderText("Uspjesan unos!");
        alert.setContentText("Uspjesno ste dodali novi simptom!\n" +
                "Naziv novog simpoma: " + nazivNovogSimptoma);
        alert.showAndWait();
    }

    public void ispisNeuspjesnogUnosaSimptoma() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Novi simptom");
        alert.setHeaderText("Neuspjesan unos!");
        alert.setContentText("Nedostaje parametar!");
        alert.showAndWait();
    }


    public boolean jesuSviParametriUneseni(String nazivSimptoma){
        if(nazivSimptoma.isBlank() || (!rbPRODUKTIVNI.isSelected() &&
                                        !rbINTENZIVNO.isSelected() &&
                                        !rbJAKA.isSelected() &&
                                        !rbVISOKA.isSelected())){
            return false;
        }
        return true;
    }
}
