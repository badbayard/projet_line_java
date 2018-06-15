package core;

import utilities.Pair;

import java.util.EnumMap;
import java.util.Random;

public class Jeu {
    private Chemin chemin;
    private Grille grille;
    private EnumMap<Symbole, Boolean> etat_chemins;
    private boolean estPartieFinie;

    public Jeu(int taille_grille) {
        this.chemin = new Chemin();
        this.grille = new Grille(taille_grille);
        this.etat_chemins = new EnumMap<Symbole, Boolean>(Symbole.class);
        this.estPartieFinie = false;
    }

    public void placeSymbole(Symbole symb) {
        Random r = new Random();
        int random_x = r.nextInt(getGrille().getTaille());
        int random_y = r.nextInt(getGrille().getTaille());
        for (int i = 0; i < 2; i++) {
            while (!getCase(random_x, random_y).estLibre()) {
                random_x = r.nextInt(getGrille().getTaille());
                random_y = r.nextInt(getGrille().getTaille());
            }
            getCase(random_x, random_y).placeSymb(symb);
        }
        getEtatChemins().put(symb, false);
    }

    public EnumMap<Symbole, Boolean> getEtatChemins() {
        return etat_chemins;
    }

    public Chemin getChemin() {
        return chemin;
    }

    public Grille getGrille() {
        return grille;
    }

    public Case getCase(int i, int j) {
        return grille.getTab()[i][j];
    }

    public boolean voisin(Case c1, Case c2) {
        if (c1.getX() == c2.getX()) {
            return (Math.abs(c1.getY() - c2.getY()) == 1);
        }
        else if (c1.getY() == c2.getY()) {
            return (Math.abs(c1.getX() - c2.getX()) == 1);
        }
        else return false;
    }

    public void validerChemin () {
        getEtatChemins().put(getChemin().getCaseDebut().getSymb(), true);
        getChemin().viderChemin();
    }

    public Chemin getCheminDepuisCase(Case c) {
        /*
        if(c.hasPrev()) {
            Case courant = c;
            Pair<Integer, Integer> coords = courant.getPrevCoords();
            courant.retireRailPrec();
            courant = getCase(coords.getFirst(), coords.getSecond());
            while (courant.hasPrev()) {
                if (courant.estLibre()) {
                    courant.setSymb(Symbole.vide);
                }
                courant.retireRailSuiv();
                coords = courant.getPrevCoords();
                courant.retireRailPrec();
                courant = getCase(coords.getFirst(), coords.getSecond());
            }
            courant.retireRailSuiv();
        } else {
            Case courant = c;
            Pair<Integer, Integer> coords = courant.getNextCoords();
            courant.retireRailSuiv();
            courant = getCase(coords.getFirst(), coords.getSecond());
            while (courant.hasNext()) {
                if (courant.estLibre()) {
                    courant.setSymb(Symbole.vide);
                }
                courant.retireRailPrec();
                coords = courant.getNextCoords();
                courant.retireRailSuiv();
                courant = getCase(coords.getFirst(), coords.getSecond());
            }
            courant.retireRailPrec();
        }
        */
        Chemin ch = new Chemin();
        Pair<Integer, Integer> coords = new Pair<Integer, Integer>(c.getX(), c.getY());
        while (getCase(coords.getFirst(), coords.getSecond()).hasPrev()) {
            coords = getCase(coords.getFirst(), coords.getSecond()).getPrevCoords();
        }
        while (getCase(coords.getFirst(), coords.getSecond()).hasNext()) {
            ch.pushCase(getCase(coords.getFirst(), coords.getSecond()));
            coords = getCase(coords.getFirst(), coords.getSecond()).getNextCoords();
        }
        return ch;
    }

    public void supprimerCheminDepuisCase(Case c) {
        Chemin ch = getCheminDepuisCase(c);
        if (!c.hasNext()) {
            ch.couperChemin(ch.getCaseDebut());
        } else {
            ch.couperChemin(c);
        }
    }

    public boolean estPartieFinie() {
        return estPartieFinie;
    }

    public void testFinPartie() {
        boolean b;
        if (getEtatChemins().values().contains(false)) {
            // Si une paire de symboles n'a pas encore été reliée
            b = false;
        } else {
            // Tous les symboles ont été reliés
            if (getGrille().contientCaseVide()) {
                // Si la grille contient encore des cases vides la partie n'est pas finie
                b = false;
            } else {
                b = true;
            }
        }
        this.estPartieFinie = b;
    }

    private boolean estFinie () {
        if (getEtatChemins().values().contains(false)) {
            // Si une paire de symboles n'a pas encore été reliée
            return false;
        } else {
            // Tous les symboles ont été reliés
            if (getGrille().contientCaseVide()) {
                // Si la grille contient encore des cases vides la partie n'est pas finie
                return false;
            } else {
                return true;
            }
        }
    }
}
