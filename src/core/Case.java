package core;

import utilities.Pair;

import java.util.Vector;

public class Case {
    private int x, y;
    private Symbole symb;
    private boolean libre;
    private Pair<Rail, Rail> rails;


    public Case(int x, int y) {
        this.x = x;
        this.y = y;
        this.symb = Symbole.vide;
        this.libre = true;
        this.rails = new Pair<Rail, Rail>(Rail.vide, Rail.vide);
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Symbole getSymb() {
        return symb;
    }

    public void setSymb(Symbole symb) {
        this.symb = symb;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Case) {
            if (this.getX() == ((Case) obj).getX() && this.getY() == ((Case) obj).getY()) {
                if (this.getSymb() == ((Case) obj).getSymb()) {
                    return this.getNbRail() == ((Case) obj).getNbRail();
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean equals(Case c) {
        if (this.getX() == c.getX()) {
            if (this.getY() == c.getY()) {
                return (this.getSymb() == c.getSymb());
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public Pair<Rail, Rail> getRails() {
        return rails;
    }

    public Case copieCase() {
        Case c = new Case(getX(), getY());
        c.setSymb(getSymb());
        c.setLibre(estLibre());
        c.getRails().setFirst(getRailPrec());
        c.getRails().setSecond(getRailSuiv());
        return c;
    }

    public boolean compareCoords(Case c) {
        if (this.getX() == c.getX()) {
            return (this.getY() == c.getY());
        } else {
            return false;
        }
    }

    public boolean compareSymb(Case c) {
        return (this.getSymb() == c.getSymb());
    }

    public boolean estLibre() {
        return libre;
    }

    public boolean estVide() {
        if (estLibre()) {
            // Si la case ne contenait pas de symbole
            // Elle doit contenir 2 rails pour valider le jeu
            if (getNbRail() == 2) {
                return false;
            } else {
                return true;
            }
        } else {
            // Sinon 1 rail
            if (getNbRail() == 1) {
                return false;
            } else {
                return true;
            }
        }
    }

    public void placeSymb(Symbole symb) {
        setSymb(symb);
        setLibre(false);
    }

    public boolean estVoisin(Case c) {
        if (this.getX() == c.getX()) {
            return (Math.abs(this.getY() - c.getY()) == 1);
        }
        else if (this.getY() == c.getY()) {
            return (Math.abs(this.getX() - c.getX()) == 1);
        }
        else return false;
    }

    public Rail railVersCase(Case c) {
        int dx = this.getX() - c.getX();
        int dy = this.getY() - c.getY();

        if (dx == 0) {
            if (dy == -1) {
                return Rail.EST;
            } else if (dy == 1) {
                return Rail.OUEST;
            } else {
                return Rail.vide;
            }
        } else if (dy == 0) {
            if (dx == -1) {
                return Rail.SUD;
            } else if (dx == 1) {
                return Rail.NORD;
            } else {
                return Rail.vide;
            }
        } else {
            return Rail.vide;
        }
    }

    public int getNbRail() {
        int r = 0;
        if (rails.getFirst() != Rail.vide) {
            r++;
        }
        if (rails.getSecond() != Rail.vide) {
            r++;
        }
        return r;
    }

    public boolean contientRail() {
        return (getNbRail() != 0);
    }

    public void ajouteRailPrec(Rail r) {
        rails.setFirst(r);
    }

    public void ajouteRailSuiv(Rail r) {
        rails.setSecond(r);
    }

    public void retireRailPrec() {
        rails.setFirst(Rail.vide);
    }

    public void retireRailSuiv() {
        rails.setSecond(Rail.vide);
    }

    public Rail getRailPrec() {
        return rails.getFirst();
    }

    public Rail getRailSuiv() {
        return rails.getSecond();
    }

    public boolean hasNext() {
        return (rails.getSecond() == Rail.vide);
    }

    public boolean hasPrev() {
        return (rails.getFirst() == Rail.vide);
    }

    public Pair<Integer, Integer> getNextCoords() {
        Pair<Integer, Integer> result = new Pair<Integer, Integer>(null, null);
        switch (rails.getSecond()) {
            case NORD:
                result.setFirst(getX() - 1);
                result.setSecond(getY());
                break;
            case SUD:
                result.setFirst(getX() + 1);
                result.setSecond(getY());
                break;
            case EST:
                result.setFirst(getX());
                result.setSecond(getY() + 1);
                break;
            case OUEST:
                result.setFirst(getX());
                result.setSecond(getY() - 1);
                break;
            default:
                break;
        }
        return result;
    }

    public Pair<Integer, Integer> getPrevCoords() {
        Pair<Integer, Integer> result = new Pair<Integer, Integer>(null, null);
        switch (rails.getFirst()) {
            case NORD:
                result.setFirst(getX() - 1);
                result.setSecond(getY());
                break;
            case SUD:
                result.setFirst(getX() + 1);
                result.setSecond(getY());
                break;
            case EST:
                result.setFirst(getX());
                result.setSecond(getY() + 1);
                break;
            case OUEST:
                result.setFirst(getX());
                result.setSecond(getY() - 1);
                break;
            default:
                break;
        }
        return result;
    }

    private void setLibre(boolean libre) {
        this.libre = libre;
    }
}