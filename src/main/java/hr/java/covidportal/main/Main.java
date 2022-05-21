package main.java.hr.java.covidportal.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main extends Application {

    private static Stage mainStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        mainStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("pocetniEkran.fxml"));
        mainStage.setTitle("Početni ekran");
        mainStage.setScene(new Scene(root, 600, 400));
        root.getStylesheets().add(getClass().getResource("/testni.css").toExternalForm());
        mainStage.show();
    }


    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    /**
     *
     * Služi za pokretanje programa kojim se unose podaci o osobama zaraženim različitim bolestima
     *
     * @param args arhumenti komadne linije se ne koriste
     */

    public static void main(String[] args) {
        logger.info("Pokrenuli smo aplikaciju");

        launch(args);
    }

    public static Stage getMainStage() {
        return mainStage;
    }

}
