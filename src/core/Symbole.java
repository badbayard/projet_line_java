package core;

import javafx.scene.paint.Color;

import java.util.Arrays;
import java.util.Random;
import java.util.Vector;

public enum Symbole {
    vide("vide", Color.TRANSPARENT),
    rail("rail", Color.TRANSPARENT),
    ROUGE("Rouge", Color.RED),
    ORANGE("Orange", Color.ORANGE),
    JAUNE("Jaune", Color.YELLOW),
    VERT("Vert", Color.GREEN),
    BLEU("Bleu", Color.BLUE),
    VIOLET("Violet", Color.PURPLE),
    GRIS("Gris", Color.GREY),
    BLANC("Blanc", Color.WHITE),
    CYAN("CYAN", Color.CYAN);

    private String nom_symbole;
    private Color couleur;

    private Symbole(String nom_symbole, Color couleur) {
        this.nom_symbole = nom_symbole;
        this.couleur = couleur;
    }

    public String getNomSymbole() {
        return nom_symbole;
    }

    public Color getCouleur() {
        return couleur;
    }

    public static Symbole getRandomSymbole() {
        Random random = new Random();
        Symbole[] all_vals = values();
        Vector<Symbole> vals = new Vector<Symbole>();
        for (int i = 0; i < all_vals.length; i++) {
            if (all_vals[i] != Symbole.vide && all_vals[i] != Symbole.rail) {
                vals.add(all_vals[i]);
            }
        }
        return vals.get(random.nextInt(vals.size()));
    }
}
