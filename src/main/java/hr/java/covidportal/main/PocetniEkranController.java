package main.java.hr.java.covidportal.main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PocetniEkranController implements Initializable {

    @FXML
    public void prikaziEkranZaPretraguZupanija() throws IOException {
        Parent pretragaZupanijaFrame =
                FXMLLoader.load(getClass().getClassLoader().getResource(
                        "pretragaZupanija.fxml"));
        Scene pretragaZupanijaScene = new Scene(pretragaZupanijaFrame, 600, 400);

        pretragaZupanijaFrame.getStylesheets().add(getClass().getResource("/testni.css").toExternalForm());

        Main.getMainStage().setScene(pretragaZupanijaScene);
        Main.getMainStage().setTitle("Pretraga županija");
    }

    @FXML
    public void prikaziEkranZaPretraguSimptoma() throws IOException {
        Parent pretragaSimptomaFrame =
                FXMLLoader.load(getClass().getClassLoader().getResource(
                        "pretragaSimptoma.fxml"));
        Scene pretragaSimptomaScene = new Scene(pretragaSimptomaFrame, 600, 400);

        pretragaSimptomaFrame.getStylesheets().add(getClass().getResource("/testni.css").toExternalForm());

        Main.getMainStage().setScene(pretragaSimptomaScene);
        Main.getMainStage().setTitle("Pretraga simptoma");
    }

    @FXML
    public void prikaziEkranZaPretraguVirusa() throws IOException {
        Parent pretragaVirusaFrame =
                FXMLLoader.load(getClass().getClassLoader().getResource(
                        "pretragaVirusa.fxml"));
        Scene pretragaVirusaScene = new Scene(pretragaVirusaFrame, 600, 400);

        pretragaVirusaFrame.getStylesheets().add(getClass().getResource("/testni.css").toExternalForm());

        Main.getMainStage().setScene(pretragaVirusaScene);
        Main.getMainStage().setTitle("Pretraga virusa");
    }

    @FXML
    public void prikaziEkranZaPretraguBolesti() throws IOException {
        Parent pretragaBolestiFrame =
                FXMLLoader.load(getClass().getClassLoader().getResource(
                        "pretragaBolesti.fxml"));
        Scene pretragaBolestiScene = new Scene(pretragaBolestiFrame, 600, 400);

        pretragaBolestiFrame.getStylesheets().add(getClass().getResource("/testni.css").toExternalForm());

        Main.getMainStage().setScene(pretragaBolestiScene);
        Main.getMainStage().setTitle("Pretraga bolesti");
    }

    @FXML
    public void prikaziEkranZaPretraguOsoba() throws IOException {
        Parent pretragaOsobaFrame =
                FXMLLoader.load(getClass().getClassLoader().getResource(
                        "pretragaOsoba.fxml"));
        Scene pretragaOsobaScene = new Scene(pretragaOsobaFrame, 1175, 717);

        pretragaOsobaFrame.getStylesheets().add(getClass().getResource("/testni.css").toExternalForm());

        Main.getMainStage().setScene(pretragaOsobaScene);
        Main.getMainStage().setTitle("Pretraga osoba");
    }

    @FXML
    public void prikaziEkranZaDodavanjeNoveZupanije() throws IOException {
        Parent dodavanjeNoveZupanijeFrame =
                FXMLLoader.load(getClass().getClassLoader().getResource(
                        "dodavanjeNoveZupanije.fxml"));
        Scene dodavanjeNoveZupanijeScene = new Scene(dodavanjeNoveZupanijeFrame, 600, 400);

        dodavanjeNoveZupanijeFrame.getStylesheets().add(getClass().getResource("/testni.css").toExternalForm());

        Main.getMainStage().setScene(dodavanjeNoveZupanijeScene);
        Main.getMainStage().setTitle("Dodavanje nove županije");

    }

    @FXML
    public void prikaziEkranZaDodavanjeNoveBolesti() throws IOException {
        Parent dodavanjeNoveBolestiFrame =
                FXMLLoader.load(getClass().getClassLoader().getResource(
                        "dodavanjeNoveBolesti.fxml"));
        Scene dodavanjeNoveBolestiScene = new Scene(dodavanjeNoveBolestiFrame, 822, 552);

        dodavanjeNoveBolestiFrame.getStylesheets().add(getClass().getResource("/testni.css").toExternalForm());

        Main.getMainStage().setScene(dodavanjeNoveBolestiScene);
        Main.getMainStage().setTitle("Dodavanje nove bolesti");

    }

    @FXML
    public void prikaziEkranZaDodavanjeNovogVirusa() throws IOException {
        Parent dodavanjeNovogVirusaFrame =
                FXMLLoader.load(getClass().getClassLoader().getResource(
                        "dodavanjeNovogVirusa.fxml"));
        Scene dodavanjeNovogVirusaScene = new Scene(dodavanjeNovogVirusaFrame, 797, 472);

        dodavanjeNovogVirusaFrame.getStylesheets().add(getClass().getResource("/testni.css").toExternalForm());

        Main.getMainStage().setScene(dodavanjeNovogVirusaScene);
        Main.getMainStage().setTitle("Dodavanje novog virusa");

    }

    @FXML
    public void prikaziEkranZaDodavanjeNovogSimptoma() throws IOException {
        Parent dodavanjeNovogSimptomaFrame =
                FXMLLoader.load(getClass().getClassLoader().getResource(
                        "dodavanjeNovogSimptoma.fxml"));
        Scene dodavanjeNovogSimptomaScene = new Scene(dodavanjeNovogSimptomaFrame, 600, 400);

        dodavanjeNovogSimptomaFrame.getStylesheets().add(getClass().getResource("/testni.css").toExternalForm());

        Main.getMainStage().setScene(dodavanjeNovogSimptomaScene);
        Main.getMainStage().setTitle("Dodavanje novog simptoma");

    }

    @FXML
    public void prikazEkranaZaDodavanjeNoveOsobe() throws IOException {
        Parent dodavanjeNoveOsobeFrame =
                FXMLLoader.load(getClass().getClassLoader().getResource(
                        "dodavanjeNoveOsobe.fxml"));
        Scene dodavanjeNoveOsobeScene = new Scene(dodavanjeNoveOsobeFrame, 896, 705);

        dodavanjeNoveOsobeScene.getStylesheets().add(getClass().getResource("/testni.css").toExternalForm());

        Main.getMainStage().setScene(dodavanjeNoveOsobeScene);
        Main.getMainStage().setTitle("Dodavanje nove osobe");

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
