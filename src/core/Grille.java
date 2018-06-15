package core;

public class Grille {
    private int taille;
    private Case[][] tab;

    public Grille(int taille) {
        this.taille = taille;
        tab = new Case[taille][taille];
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                tab[i][j] = new Case(i, j);
            }
        }
    }

    public int getTaille() {
        return taille;
    }

    public Case[][] getTab() {
        return tab;
    }

    public boolean contientCaseVide() {
        for (int i = 0; i < getTaille(); i++) {
            for (int j = 0; j < getTaille(); j++) {
                if (getTab()[i][j].estVide()) {
                    return true;
                }
            }
        }
        return false;
    }
}
