package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.presentateur.Alert;
import sample.presentateur.Presentateur;
import sample.model.Model;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/View.fxml"));
        Parent root = fxmlLoader.load();
        Presentateur presentateur = fxmlLoader.getController();

        Model model = new Model();
        presentateur.setModel(model);
        Alert alert = new Alert();
        alert.setModel(model);
        presentateur.setAlert(alert);
        alert.setPresentateur(presentateur);
        model.addObserver(presentateur);
        model.initialiser();

        primaryStage.setTitle("Echec Halma");
        primaryStage.setScene(new Scene(root, 680, 420));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
