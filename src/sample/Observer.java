package sample;

import sample.model.Model;

//l'observer permet de mettre a jour la vue par rapport au model
public interface Observer {
    void update (Model model);
}
