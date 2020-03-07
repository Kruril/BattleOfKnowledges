package enumeration;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public enum AvatarPlayer {

    ARBRE(new ImageView(new Image("images/avatar/arbre.png", 50,50,
            true,true))),
    ALIEN(new ImageView(new Image("images/avatar/alien.png",50,50,
            true,true))),
    BATTER(new ImageView(new Image("images/avatar/batter.png",50,50,
            true,true)));

    private ImageView valeur;

    AvatarPlayer(ImageView valeur) {
        this.valeur = valeur;
    }

    public ImageView getValeur() {
        return valeur;
    }
}
