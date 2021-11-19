package sample;

import sample.model.Model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class Save {
    public static void sauvegarder(Model model) {
        try {
            FileOutputStream fileOut = new FileOutputStream("store");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(model);
            objectOut.close();
            System.out.println("save OK");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static Model charger(Model replacedModel) {
        List<Observer> observers = replacedModel.getObservers();
        try {
            FileInputStream fileIn = new FileInputStream("store");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            Model newModel = (Model)objectIn.readObject();
            objectIn.close();
            for (Observer observer:observers) {
                newModel.addObserver(observer);   //ajouter observers
            }
            System.out.println("Load OK");
            return newModel;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return replacedModel;
    }



}
