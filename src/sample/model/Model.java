package sample.model;


import sample.Observer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Model implements Serializable{
    private final String[][] echiquier = new String[8][8];

    private int scoreBlanc=0;
    private int scoreNoir=0;

    transient private List<Observer> observers=new ArrayList<>();


    private boolean tour;

    public String[][] getEchiquier() {

        return echiquier;

    }

    @Override
    //renvoie une representaion de l'etat de l'echiquier
    public String toString() {
        String string="score blanc: "+scoreBlanc+"\tscore noir: "+scoreNoir+"\n";
        for (int x=0;x<echiquier.length;x++){
            for(int y=0;y<echiquier.length;y++){
                string=string+echiquier[y][x]+" ";
            }
            string=string+("\n");
        }
        return string;
    }

    public Model() {
        System.out.println(this);
    }

    public void initialiser()
    {
        System.out.println("<initialiser>");
        echiquier[5][0]= "p_n";
        echiquier[6][0]= "p_n";
        echiquier[7][0]= "p_n";
        echiquier[5][1]= "p_n";
        echiquier[6][1]= "r_n";
        echiquier[7][1]= "p_n";
        echiquier[5][2]= "p_n";
        echiquier[6][2]= "p_n";
        echiquier[7][2]= "p_n";
        echiquier[0][5]= "p_b";
        echiquier[1][5]= "p_b";
        echiquier[2][5]= "p_b";
        echiquier[0][6]= "p_b";
        echiquier[1][6]= "r_b";
        echiquier[2][6]= "p_b";
        echiquier[0][7]= "p_b";
        echiquier[1][7]= "p_b";
        echiquier[2][7]= "p_b";

        tour = false;
        System.out.println(this);
        notifyObservers();
    }

    //permet de trouver tous les emplacement ou un pion donné peut ce déplacer
    public void possibilitePion(int coordX,int coordY){
        System.out.println("<possibiliterPion>");
        effacerPossibilite();
        sautPion(coordX,coordY);
        deplPion(coordX,coordY);
        System.out.println(this);
        notifyObservers();
    }

    //permet de trouver tous les emplacement ou un roi donné peut ce déplacer (idem que pion + diagonale)
    public void possibiliteRoi(int coordX,int coordY){
        System.out.println("<possibiliterRoi>");
        //possibilitePion(coordX,coordY);
        effacerPossibilite();
        sautPion(coordX,coordY);
        sautRoi(coordX,coordY);
        deplPion(coordX,coordY);
        deplRoi(coordX,coordY);
        notifyObservers();
    }


    //verification des deplacement pour pion
    public void deplPion(int coordX,int coordY){
        System.out.println("<deplPion>");

        if (coordX+1<8 && echiquier[coordX+1][coordY] == null){
            echiquier[coordX+1][coordY] = "depl";
        }
        if (coordX-1>=0 && echiquier[coordX-1][coordY] == null){
            echiquier[coordX-1][coordY] = "depl";
        }
        if (coordY+1<8 && echiquier[coordX][coordY+1] == null){
            echiquier[coordX][coordY+1] = "depl";
        }
        if (coordY-1>=0 && echiquier[coordX][coordY-1] == null){
            echiquier[coordX][coordY-1] = "depl";
        }
    }
    //verification des saut pour pion
    public void sautPion(int coordX,int coordY){
        System.out.println("<sautPion>");


        //sauter vers le bas
        if (coordY+1<8 && echiquier[coordX][coordY+1] != null){
            if (coordY+2<8 && echiquier[coordX][coordY + 2] == null) {
                echiquier[coordX][coordY + 2] = "saut";
                sautPion(coordX,coordY+2);
            }
        }

        //sauter vers le haut

        if (coordY-1>=0 && echiquier[coordX][coordY-1] != null){
            if (coordY-2>=0 && echiquier[coordX][coordY-2] == null) {
                echiquier[coordX][coordY - 2] = "saut";
                sautPion(coordX,coordY-2);
            }
        }

        //sauter vers la droite
        if (coordX+1<8 && echiquier[coordX+1][coordY] != null){
            if (coordX+2<8 && echiquier[coordX+2][coordY] == null) {
                echiquier[coordX+2][coordY] = "saut";

                sautPion(coordX+2,coordY);
            }
        }
        if (coordX-1>=0 && echiquier[coordX-1][coordY] != null){
            if (coordX-2>=0 && echiquier[coordX-2][coordY] == null) {
                echiquier[coordX-2][coordY] = "saut";

                sautPion(coordX-2,coordY);
            }
        }
    }

    //verification des deplacement pour roi
    public void deplRoi(int coordX,int coordY){

        if (coordX+1<8 && coordY+1<8 && echiquier[coordX+1][coordY+1] == null){
            echiquier[coordX+1][coordY+1] = "depl";
        }

        if (coordX-1>=0 && coordY-1>=0 && echiquier[coordX-1][coordY-1] == null){
            echiquier[coordX-1][coordY-1] = "depl";
        }
        if (coordX+1<8 && coordY-1>=0 && echiquier[coordX+1][coordY-1] == null){
            echiquier[coordX+1][coordY-1] = "depl";
        }
        if (coordX-1>=0 && coordY+1<8 && echiquier[coordX-1][coordY+1] == null){
            echiquier[coordX-1][coordY+1] = "depl";
        }
    }
    //verification des saut pour roi
    public void sautRoi(int coordX,int coordY){
        sautPion(coordX,coordY);

        if (coordX+1<8 && coordY+1<8 && echiquier[coordX+1][coordY+1] != null) {
            if (coordX + 2 < 8 && coordY + 2 < 8 && echiquier[coordX + 2][coordY + 2] == null) {
                echiquier[coordX + 2][coordY + 2] = "saut";
                sautRoi(coordX+2,coordY+2);
            }
        }
        if (coordX-1>=0 && coordY-1>=0 && echiquier[coordX-1][coordY-1] != null){
            if (coordX-2>=0 && coordY-2>=0 && echiquier[coordX-2][coordY-2] == null) {
                echiquier[coordX - 2][coordY - 2] = "saut";

                sautRoi(coordX-2,coordY-2);
            }
        }
        if (coordX+1<8 && coordY-1>=0 && echiquier[coordX+1][coordY-1] != null){
            if (coordX+2<8 && coordY-2>=0 && echiquier[coordX+2][coordY-2] == null) {
                echiquier[coordX + 2][coordY - 2] = "saut";
                sautRoi(coordX+2,coordY-2);
            }
        }
        if (coordX-1>=0 && coordY+1<8 && echiquier[coordX-1][coordY+1] != null){
            if (coordX-2>=0 && coordY+2<8 && echiquier[coordX-2][coordY+2] == null) {
                echiquier[coordX - 2][coordY + 2] = "saut";
                sautRoi(coordX-2,coordY+2);
            }
        }

    }


    //permet de déplacer une piece donné si on lui donne un emplacement disponible
    public void deplacer(int coordX,int coordY,int X,int Y){

        String pion = echiquier[coordX][coordY];
        String emplacement = echiquier[X][Y];
        System.out.println("pion: "+pion);
        System.out.println("emplacement: "+emplacement);

        if (Objects.equals(emplacement, "depl")){
            echiquier[X][Y] = pion;
            echiquier[coordX][coordY] = null;
            passer();
        }

        if (Objects.equals(emplacement, "saut")){
            echiquier[X][Y] = pion;
            echiquier[coordX][coordY] = null;
            passer();
        }

        effacerPossibilite();
        this.notifyObservers();
        win();
    }

    //simple fontion pour passer sont tour
    public void passer(){

        if (tour==false)
            tour=true;
        else
            tour=false;
    }

    //efface toute les possibilité (utiliser après deplacement)
    public void effacerPossibilite(){

        for (int x=0;x<echiquier.length;x++){
            for (int y=0;y<echiquier.length;y++){
                if (echiquier[x][y]=="saut" || echiquier[x][y]=="depl" ){
                    echiquier[x][y]=null;
                }
            }
        }
        notifyObservers();
    }



    public void win(){

        if(echiquier[1][6]=="r_n" && echiquier[0][5]==("p_n") && echiquier[0][6]== "p_n" &&
                echiquier[0][7]== "p_n" && echiquier[1][5]== "p_n" &&
                echiquier[1][7]== "p_n" && echiquier[2][5]== "p_n" &&
                echiquier[2][6]== "p_n" && echiquier[2][7]== "p_n"){
            scoreNoir++;
            newGame();
            System.out.println("noir gagné");
        }
        if(echiquier[6][1]=="r_b" && echiquier[5][0]==("p_b") && echiquier[6][0]== "p_b" &&
                echiquier[7][0]== "p_b" && echiquier[5][1]== "p_b" &&
                echiquier[7][1]== "p_b" && echiquier[5][2]== "p_b" &&
                echiquier[6][2]== "p_b" && echiquier[7][2]== "p_b"){

            scoreBlanc++;
            newGame();
            System.out.println("blanc gagné");
        }
        notifyObservers();
    }

    public int getScoreBlanc() {

        return scoreBlanc;
    }

    public int getScoreNoir() {

        return scoreNoir;
    }

    public boolean isTour() {

        return tour;
    }

    public void newGame() {

        for (int x=0;x<echiquier.length;x++){
            for(int y=0;y<echiquier.length;y++){
                echiquier[y][x]=null;
            }
        }

        echiquier[5][0]= "p_n";
        echiquier[6][0]= "p_n";
        echiquier[7][0]= "p_n";
        echiquier[5][1]= "p_n";
        echiquier[6][1]= "r_n";
        echiquier[7][1]= "p_n";
        echiquier[5][2]= "p_n";
        echiquier[6][2]= "p_n";
        echiquier[7][2]= "p_n";

        echiquier[0][5]= "p_b";
        echiquier[1][5]= "p_b";
        echiquier[2][5]= "p_b";
        echiquier[0][6]= "p_b";
        echiquier[1][6]= "r_b";
        echiquier[2][6]= "p_b";
        echiquier[0][7]= "p_b";
        echiquier[1][7]= "p_b";
        echiquier[2][7]= "p_b";
        tour=false;
        notifyObservers();
    }

    public void addObserver(Observer observer){

        if (!this.observers.contains(observer))
            this.observers.add(observer);
    }

    public List<Observer> getObservers() {
        return observers;
    }

    private void notifyObservers(){

        for (Observer observer:observers){
            observer.update(this);
        }
    }
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        observers = new ArrayList<>();
    }

    public void resetScore() {
        this.scoreBlanc =0;
        this.scoreNoir =0;

    }
}