package gui;

import core.Case;
import core.Rail;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CaseCouleur extends Parent {
    private Canvas canvas;
    private GraphicsContext gc;
    private Color couleur;
    private double taille_case;

    public CaseCouleur(Color couleur) {
        this.taille_case = 50;
        this.canvas = new Canvas(50,50);
        this.gc = canvas.getGraphicsContext2D();
        setCouleur(couleur);
        this.getChildren().add(canvas);
    }

    public CaseCouleur(Color couleur, double taille_case) {
        this.taille_case = taille_case;
        this.canvas = new Canvas(taille_case, taille_case);
        this.gc = canvas.getGraphicsContext2D();
        setCouleur(couleur);
        this.getChildren().add(canvas);
    }

    public GraphicsContext getGc() {
        return gc;
    }

    public Color getCouleur() {
        return couleur;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
        getGc().setFill(couleur);
        getGc().setStroke(couleur);
    }


    public double getTailleCase() {
        return taille_case;
    }

    public void dessinerSymbole() {
        double rayon = getTailleCase() / 3;
        getGc().fillOval(
                (getTailleCase() / 2) - rayon,
                (getTailleCase() / 2) - rayon,
                rayon * 2,
                rayon * 2);
    }

    public void dessinerRail(Rail r) {
        if (r != Rail.vide) {
            double rayon = getTailleCase() / 8;
            getGc().fillOval(
                    (getTailleCase() / 2) - rayon,
                    (getTailleCase() / 2) - rayon,
                    rayon * 2,
                    rayon * 2);
            double x = 0, y = 0, w = 0, h = 0;
            switch (r) {
                case NORD:
                    x = (getTailleCase() / 2) - rayon;
                    y = 0;
                    w = rayon * 2;
                    h = getTailleCase() / 2;
                    break;
                case SUD:
                    x = (getTailleCase() / 2) - rayon;
                    y = getTailleCase() / 2;
                    w = rayon * 2;
                    h = getTailleCase() / 2;
                    break;
                case EST:
                    x = getTailleCase() / 2;
                    y = (getTailleCase() / 2) - rayon;
                    w = getTailleCase() / 2;
                    h = rayon * 2;
                    break;
                case OUEST:
                    x = 0;
                    y = (getTailleCase() / 2) - rayon;
                    w = getTailleCase() / 2;
                    h = rayon * 2;
                    break;
                default:
                    break;
            }
            getGc().fillRect(x, y, w, h);
        }
    }

    public void effacerRail(Rail r) {
        if(r != Rail.vide) {
            double rayon = getTailleCase() / 8;
            double x = 0, y = 0, w = 0, h = 0;
            switch (r) {
                case NORD:
                    x = (getTailleCase() / 2) - rayon;
                    y = 0;
                    w = rayon * 2;
                    h = getTailleCase() / 2;
                    break;
                case SUD:
                    x = (getTailleCase() / 2) - rayon;
                    y = getTailleCase() / 2;
                    w = rayon * 2;
                    h = getTailleCase() / 2;
                    break;
                case EST:
                    x = getTailleCase() / 2;
                    y = (getTailleCase() / 2) - rayon;
                    w = getTailleCase() / 2;
                    h = rayon * 2;
                    break;
                case OUEST:
                    x = 0;
                    y = (getTailleCase() / 2) - rayon;
                    w = getTailleCase() / 2;
                    h = rayon * 2;
                    break;
                default:
                    x = 0;
                    y = 0;
                    w = 0;
                    h = 0;
                    break;
            }
            this.getGc().clearRect(x, y, w, h);
            this.getGc().fillOval(
                    (getTailleCase() / 2) - rayon,
                    (getTailleCase() / 2) - rayon,
                    rayon * 2,
                    rayon * 2);
        }
    }

    public void update(Case c) {
        this.getGc().clearRect(0,0,getTailleCase(),getTailleCase());
        this.setCouleur(c.getSymb().getCouleur());
        if (!c.estLibre()) {
            dessinerSymbole();
        }
        dessinerRail(c.getRailPrec());
        dessinerRail(c.getRailSuiv());
    }
}
