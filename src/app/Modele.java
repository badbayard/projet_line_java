package app;

import core.*;
import utilities.Pair;

import java.util.Observable;

public class Modele extends Observable {
    private Jeu partie;
    private Niveau niveau;


    public Modele(Niveau lvl) {
        this.niveau = lvl;
        partie = new Jeu(lvl.getTailleGrille());
        Symbole symb;
        switch (lvl) {
            case FACILE:
                symb = Symbole.getRandomSymbole();
                while (getPartie().getEtatChemins().containsKey(symb)) {
                    symb = Symbole.getRandomSymbole();
                }
                getCase(0,0).placeSymb(symb);
                getCase(2,1).placeSymb(symb);
                getPartie().getEtatChemins().put(symb, false);
                while (getPartie().getEtatChemins().containsKey(symb)) {
                    symb = Symbole.getRandomSymbole();
                }
                getCase(0,2).placeSymb(symb);
                getCase(2,2).placeSymb(symb);
                getPartie().getEtatChemins().put(symb, false);
                break;

            case MOYEN:
                symb = Symbole.getRandomSymbole();
                while (getPartie().getEtatChemins().containsKey(symb)) {
                    symb = Symbole.getRandomSymbole();
                }
                getCase(0,0).placeSymb(symb);
                getCase(0,2).placeSymb(symb);
                getPartie().getEtatChemins().put(symb, false);
                while (getPartie().getEtatChemins().containsKey(symb)) {
                    symb = Symbole.getRandomSymbole();
                }
                getCase(0,3).placeSymb(symb);
                getCase(3,0).placeSymb(symb);
                getPartie().getEtatChemins().put(symb, false);
                while (getPartie().getEtatChemins().containsKey(symb)) {
                    symb = Symbole.getRandomSymbole();
                }
                getCase(0,4).placeSymb(symb);
                getCase(4,2).placeSymb(symb);
                getPartie().getEtatChemins().put(symb, false);
                while (getPartie().getEtatChemins().containsKey(symb)) {
                    symb = Symbole.getRandomSymbole();
                }
                getCase(2,1).placeSymb(symb);
                getCase(3,3).placeSymb(symb);
                getPartie().getEtatChemins().put(symb, false);
                while (getPartie().getEtatChemins().containsKey(symb)) {
                    symb = Symbole.getRandomSymbole();
                }
                getCase(4,0).placeSymb(symb);
                getCase(3,2).placeSymb(symb);
                getPartie().getEtatChemins().put(symb, false);
                break;

            case DIFFICILE:
                symb = Symbole.getRandomSymbole();
                while (getPartie().getEtatChemins().containsKey(symb)) {
                    symb = Symbole.getRandomSymbole();
                }
                getCase(0,0).placeSymb(symb);
                getCase(1,5).placeSymb(symb);
                getPartie().getEtatChemins().put(symb, false);
                while (getPartie().getEtatChemins().containsKey(symb)) {
                    symb = Symbole.getRandomSymbole();
                }
                getCase(1,0).placeSymb(symb);
                getCase(2,3).placeSymb(symb);
                getPartie().getEtatChemins().put(symb, false);
                while (getPartie().getEtatChemins().containsKey(symb)) {
                    symb = Symbole.getRandomSymbole();
                }
                getCase(3,0).placeSymb(symb);
                getCase(2,1).placeSymb(symb);
                getPartie().getEtatChemins().put(symb, false);
                while (getPartie().getEtatChemins().containsKey(symb)) {
                    symb = Symbole.getRandomSymbole();
                }
                getCase(1,3).placeSymb(symb);
                getCase(5,3).placeSymb(symb);
                getPartie().getEtatChemins().put(symb, false);
                while (getPartie().getEtatChemins().containsKey(symb)) {
                    symb = Symbole.getRandomSymbole();
                }
                getCase(4,0).placeSymb(symb);
                getCase(3,3).placeSymb(symb);
                getPartie().getEtatChemins().put(symb, false);
                while (getPartie().getEtatChemins().containsKey(symb)) {
                    symb = Symbole.getRandomSymbole();
                }
                getCase(5,0).placeSymb(symb);
                getCase(3,4).placeSymb(symb);
                getPartie().getEtatChemins().put(symb, false);
                break;

            default:
                for (int i = 0; i < lvl.getNbSymboles(); i++) {
                    symb = Symbole.getRandomSymbole();
                    while (getPartie().getEtatChemins().containsKey(symb)) {
                        symb = Symbole.getRandomSymbole();
                    }
                    getPartie().placeSymbole(symb);
                }
                break;
        }
        /*
        Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>[] coords = new Pair<>[lvl.getNbSymboles()];
        switch (lvl) {
            case FACILE:
                coords[][]
                break;
            case NORMAL:
                break;
            case DIFFICILE:
                break;
        }
        */
        /*
        for (int i = 0; i < lvl.getCoords().size(); i++) {
            Symbole symb = Symbole.getRandomSymbole();
            while (getPartie().getEtatChemins().containsKey(symb)) {
                symb = Symbole.getRandomSymbole();
            }
            int x, y;
            x = lvl.getCoords().get(i).getFirst().getFirst();
            y = lvl.getCoords().get(i).getFirst().getSecond();
            getCase(x, y).placeSymb(symb);
            x = lvl.getCoords().get(i).getSecond().getFirst();
            y = lvl.getCoords().get(i).getSecond().getSecond();
            getCase(x, y).placeSymb(symb);
            getPartie().getEtatChemins().put(symb, false);
        }
        */
    }


    public Jeu getPartie() {
        return partie;
    }

    public Case getCase(int i, int j) {
        return partie.getGrille().getTab()[i][j];
    }

    public int getTaillePartie() {
        return partie.getGrille().getTaille();
    }

    public Chemin getChemin() {
        return partie.getChemin();
    }

    public void startDD(int i, int j) {
        if(!getCase(i, j).estLibre()) {
            // Si la case contient un symbole
            if (getCase(i, j).contientRail()) {
                /*
                // Si le symbole est déjà relié à un chemin
                if (!getChemin().estVide()) {
                    getChemin().viderChemin();
                }
                getPartie().supprimerCheminDepuisCase(getCase(i, j));
                */
            } else {
                // Sinon
                getChemin().pushCase(getCase(i, j));
            }
        } else {
            // Sinon
            if (getCase(i, j).contientRail()) {
                // Si la case contient des rails
                if (getCase(i, j).getNbRail() == 2) {
                    // Si la case contient deux rails

                }
            }
        }
        setChanged();
        notifyObservers();
    }

    public void enterDD(int i, int j) {
        if (getCase(i, j).estVoisin(getChemin().dernier())) {
            // Si la case est voisine
            if (getCase(i, j).estLibre()) {
                // Si la case ne contient pas de symbole
                if (!getCase(i, j).contientRail()) {
                    // Si la case ne contient pas de rail
                    getCase(i, j).setSymb(getChemin().dernier().getSymb());
                    getChemin().pushCase(getCase(i, j));
                } else {
                    // Sinon
                    if (getCase(i, j).getSymb() == getChemin().getCaseDebut().getSymb()) {
                        getChemin().couperChemin(getCase(i, j));
                    }
                }
            } else {
                // Sinon si la case contient un symbole
                if (getCase(i, j).compareSymb(getChemin().dernier())) {
                    // Si le symbole est le même
                    if (!getCase(i, j).compareCoords(getChemin().getCaseDebut())) {
                        // Si la case n'est pas celle du début de tracé
                        getChemin().pushCase(getCase(i, j));
                    } else {
                        getChemin().couperChemin(getCase(i, j));
                    }
                } else {
                    // Sinon si c'est un autre symbole

                }
            }
        }
        setChanged();
        notifyObservers();
    }

    public void stopDD(int i, int j) {
        if (!getCase(i, j).estLibre()) {
            getPartie().validerChemin();
            getPartie().testFinPartie();
        }
        setChanged();
        notifyObservers();
    }
}
