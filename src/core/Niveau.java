package core;

import utilities.Pair;

import java.util.Vector;

public enum Niveau {
    FACILE(3, 2),
    MOYEN(5, 5),
    DIFFICILE(6, 6);

    private int taille_grille;
    private int nb_symboles;

    Niveau(int taille_grille, int nb_symboles) {
        this.taille_grille = taille_grille;
        this.nb_symboles = nb_symboles;
    }

    public int getTailleGrille() {
        return taille_grille;
    }

    public int getNbSymboles() {
        return nb_symboles;
    }
}
