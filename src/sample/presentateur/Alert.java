package sample.presentateur;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import sample.Save;
import sample.model.Model;

public class Alert {
    private final Label label;
    private final Stage window;
    private Model model;
    private Presentateur presentateur;

    public Alert(){
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(250);

        label = new Label();

        Button closeButton = new Button("Annuler");
        closeButton.setOnAction(e -> window.close());
        Button sauv = new Button ("Oui");
        sauv.setOnAction(e -> {Save.sauvegarder(model);window.close();presentateur.close(); });
        Button nonsauv = new Button("Non");
        nonsauv.setOnAction(e -> {window.close();presentateur.close();});
        VBox layout = new VBox(10);
        HBox layout2 = new HBox(10);
        Scene scene = new Scene (layout);

        closeButton.setStyle("-fx-background-radius:  200;");
        sauv.setStyle("-fx-background-radius:  200;");
        sauv.setPadding(new Insets(3.5, 20, 3.5, 20));
        nonsauv.setStyle("-fx-background-radius:  200;");
        nonsauv.setPadding(new Insets(3.5, 20, 3.5, 20));
        layout2.setMargin(closeButton, new Insets(0, 0, 0,48));
        layout2.setMargin(layout2, new Insets(40, 0, 0,0));
        layout.setPadding(new Insets(40, 25, 40, 25));
        layout.setStyle("-fx-background-color:  #deb887;");
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-image: url('/sample/ressource/fond2.jpg');");
        label.setTextFill(Color.WHITESMOKE);
        label.setFont(Font.font("Viner Hand ITC", FontWeight.BOLD, 18));

        layout2.getChildren().addAll(closeButton, sauv, nonsauv);
        layout.getChildren().addAll(label, layout2);

        window.setScene(scene);
    }

    public void setPresentateur(Presentateur presentateur) {
        this.presentateur = presentateur;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void display(String title, String message) {
        window.setTitle(title);
        label.setText(message);
        window.showAndWait();
    }
}