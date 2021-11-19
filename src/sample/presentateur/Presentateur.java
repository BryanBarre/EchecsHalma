package sample.presentateur;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.Observer;
import sample.Save;
import sample.model.Model;

import java.util.Arrays;


public class Presentateur implements Observer {
    private Alert alert;
    public int coordY;
    public int coordX;
    public int newCoordX;
    public int newCoordY;

    public GridPane id_GridPane;
    public Label idScoreBlanc;
    public Label idScoreNoir;
    public Button id_btn_fini;
    private Model model;

    private boolean tour=false;

    private String[][] echiquier;
    private String scoreBlanc;
    private String scoreNoir;


    public Image pionNoir = new Image("sample/ressource/pion_n.png");
    public Image pionBlanc = new Image("sample/ressource/pion_b.png");

    public Image roiBlanc = new Image("sample/ressource/roi_b.png");
    public Image reineNoir = new Image("sample/ressource/roi_n.png");
    public Image possible = new Image("sample/ressource/possible.png");


    //pieceSelectionner permet de voir et d'agir sur les case disponible d'une pieces
    void pieceSelectioner(Event actionEvent) {
        //effacerTableau();
        ImageView imageView = (ImageView) actionEvent.getSource();

        //on ne ce deplace que si c'est notre tour
        if (tour == false) {
            if (imageView.getAccessibleText().equals("r_b")) {
                coordY = GridPane.getRowIndex(imageView);
                coordX = GridPane.getColumnIndex(imageView);
                model.possibiliteRoi(coordX, coordY);
            }
            if(imageView.getAccessibleText().equals("p_b")) {
                coordY = GridPane.getRowIndex(imageView);
                coordX = GridPane.getColumnIndex(imageView);
                model.possibilitePion(coordX, coordY);
            }
        }
        else {
            if (imageView.getAccessibleText().equals("r_n")) {
                coordY = GridPane.getRowIndex(imageView);
                coordX = GridPane.getColumnIndex(imageView);
                model.possibiliteRoi(coordX, coordY);
            }
            if(imageView.getAccessibleText().equals("p_n")){
                coordY = GridPane.getRowIndex(imageView);
                coordX = GridPane.getColumnIndex(imageView);
                model.possibilitePion(coordX, coordY);
            }
        }
    }

    //deplacerPiece() sert a deplacer la piece
    //concerné a la place d'un emplacement disponible
    void deplacerPiece(Event actionEvent){
        ImageView imageView = (ImageView)actionEvent.getSource();
        //on recupere les coordoné de la piece
        newCoordX = GridPane.getColumnIndex(imageView);
        newCoordY = GridPane.getRowIndex(imageView);
        //on deplace la piece sur le modele
        model.deplacer(coordX,coordY,newCoordX,newCoordY);
        System.out.println(model.toString());
        idScoreNoir.setText(""+scoreNoir);
        idScoreBlanc.setText(""+scoreBlanc);
    }

    //recupererModele() creer les élément en fonction de ce que fait le modele
    public void recupererModele(){
        effacerTableau();
        for (int x=0;x<8;x++){
            for (int y=0;y<8;y++){
                if("p_n".equals(echiquier[x][y])){
                    ImageView p_n = new ImageView();
                    p_n.setId("id_"+x+"_"+y);
                    p_n.setAccessibleText("p_n");
                    p_n.setImage(pionNoir);
                    p_n.setFitHeight(31.0);
                    p_n.setFitWidth(32.0);
                    p_n.setPickOnBounds(true);
                    p_n.setPreserveRatio(true);
                    //pour centrer dans la case
                    GridPane.setHalignment(p_n, HPos.CENTER);
                    GridPane.setValignment(p_n, VPos.CENTER);
                    p_n.setOnMouseClicked(this::pieceSelectioner);

                    id_GridPane.add(p_n,x,y);
                }
                if("p_b".equals(echiquier[x][y])){
                    ImageView p_b = new ImageView();
                    p_b.setId("id_"+x+"_"+y);
                    p_b.setAccessibleText("p_b");
                    p_b.setImage(pionBlanc);
                    p_b.setFitHeight(31.0);
                    p_b.setFitWidth(32.0);
                    p_b.setPickOnBounds(true);
                    p_b.setPreserveRatio(true);
                    //pour centrer dans la case
                    GridPane.setHalignment(p_b, HPos.CENTER);
                    GridPane.setValignment(p_b, VPos.CENTER);
                    p_b.setOnMouseClicked(this::pieceSelectioner);
                    id_GridPane.add(p_b,x,y);
                }
                if("r_n".equals(echiquier[x][y])){
                    ImageView r_n = new ImageView();
                    r_n.setId("id_"+x+"_"+y);
                    r_n.setAccessibleText("r_n");
                    r_n.setImage(reineNoir);
                    r_n.setFitHeight(31.0);
                    r_n.setFitWidth(32.0);
                    r_n.setPickOnBounds(true);
                    r_n.setPreserveRatio(true);
                    //pour centrer dans la case
                    GridPane.setHalignment(r_n, HPos.CENTER);
                    GridPane.setValignment(r_n, VPos.CENTER);
                    r_n.setOnMouseClicked(this::pieceSelectioner);
                    id_GridPane.add(r_n,x,y);
                }
                if("r_b".equals(echiquier[x][y])){
                    ImageView r_b = new ImageView();
                    r_b.setId("id_"+x+"_"+y);
                    r_b.setAccessibleText("r_b");
                    r_b.setImage(roiBlanc);
                    r_b.setFitHeight(31.0);
                    r_b.setFitWidth(32.0);
                    r_b.setPickOnBounds(true);
                    r_b.setPreserveRatio(true);
                    //pour centrer dans la case
                    GridPane.setHalignment(r_b, HPos.CENTER);
                    GridPane.setValignment(r_b, VPos.CENTER);
                    r_b.setOnMouseClicked(this::pieceSelectioner);
                    id_GridPane.add(r_b,x,y);
                }
                if("saut".equals(echiquier[x][y])){
                    ImageView saut = new ImageView();
                    saut.setId("id_"+x+"_"+y);
                    saut.setAccessibleText("saut");
                    saut.setImage(possible);
                    saut.setFitHeight(31.0);
                    saut.setFitWidth(32.0);
                    saut.setPickOnBounds(true);
                    saut.setPreserveRatio(true);

                    //pour centrer dans la case
                    GridPane.setHalignment(saut, HPos.CENTER);
                    GridPane.setValignment(saut, VPos.CENTER);
                    saut.setOnMouseClicked(this::deplacerPiece);
                    id_GridPane.add(saut,x,y);
                }
                if("depl".equals(echiquier[x][y])){
                    ImageView depl = new ImageView();
                    depl.setId("id_"+x+"_"+y);
                    depl.setAccessibleText("depl");
                    depl.setImage(possible);
                    depl.setFitHeight(31.0);
                    depl.setFitWidth(32.0);
                    depl.setPickOnBounds(true);
                    depl.setPreserveRatio(true);
                    //pour centrer dans la case
                    GridPane.setHalignment(depl, HPos.CENTER);
                    GridPane.setValignment(depl, VPos.CENTER);
                    depl.setOnMouseClicked(this::deplacerPiece);
                    id_GridPane.add(depl,x,y);
                }
            }
        }
    }

    //efface toutes les pieces du gridpane
    private void effacerTableau() {
        id_GridPane.getChildren().removeIf(node -> node.getClass().getSimpleName().equals("ImageView"));
    }

    public void setAlert(Alert alert) {
        this.alert = alert;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    //Observer
    @Override
    public void update(Model model) {
        this.echiquier= model.getEchiquier();
        for (String[] row : echiquier){
            System.out.println(Arrays.toString(row));
        }

        this.scoreBlanc=""+model.getScoreBlanc();
        this.scoreNoir=""+model.getScoreNoir();
        this.tour=model.isTour();
        this.recupererModele();
    }

    public void openAlert() {
        alert.display("Fini !", "voulez-vous sauvegarder la partie?");
    }
    public void close(){
        ((Stage)id_btn_fini.getScene().getWindow()).close();
    }

    public void charger(ActionEvent actionEvent) {
        Model model = Save.charger(this.model); //recuperer le model du pres et charger nv model
        this.model = model; //remplacer
        update(model);
        System.out.println(model);
        alert.setModel(model); // ajouter le model à l'alert
    }
    public void reinit(){
        this.model.newGame();
        this.model.resetScore();

    }
}