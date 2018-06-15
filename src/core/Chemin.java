package core;

import java.util.Stack;

public class Chemin {
    private Stack<Case> pile;
    private Case case_debut;

    public Chemin() {
        this.pile = new Stack<Case>();
        this.case_debut = null;
    }


    public Case getCaseDebut() {
        return case_debut;
    }

    public void setCaseDebut(Case case_debut) {
        this.case_debut = case_debut;
    }

    public boolean estVide() {
        return this.pile.empty();
    }

    public boolean estCheminComplet() {
        if (estVide()) {
            return false;
        } else {
            if (getCaseDebut().getSymb() == dernier().getSymb()) {
                return true;
            } else {
                return false;
            }
        }
    }

    public void viderChemin() {
        while (!estVide()) {
            this.pile.pop();
        }
        this.case_debut = null;
    }

    public void cleanChemin() {
        while (!estVide()) {
            popCase();
        }
        this.case_debut = null;
    }

    public void pushCase(Case c) {
        if (estVide()) {
            this.case_debut = c.copieCase();
            this.pile.push(c);
        } else {
            Rail r1 = dernier().railVersCase(c);
            Rail r2 = c.railVersCase(dernier());
            Case last = this.pile.pop();
            last.ajouteRailSuiv(r1);
            this.pile.push(last);
            c.ajouteRailPrec(r2);
            this.pile.push(c);
        }
    }

    public Case popCase() {
        Case c = this.pile.pop();
        c.retireRailPrec();
        if (c.estLibre()) {
            c.setSymb(Symbole.vide);
        }
        if (estVide()) {
            this.case_debut = null;
        } else {
            Case last = this.pile.pop();
            last.retireRailSuiv();
            this.pile.push(last);
        }
        return c;
    }

    public Case avantDernier() {
        Case last = this.pile.pop();
        Case c = this.pile.peek();
        this.pile.push(last);
        return c;
    }

    public Case dernier() {
        return this.pile.peek();
    }

    public void couperChemin(Case c) {
        while (!dernier().compareCoords(c)) {
            popCase();
        }
    }
}
