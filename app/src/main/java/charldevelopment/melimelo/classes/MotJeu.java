package charldevelopment.melimelo.classes;

public class MotJeu {

    private int id;
    private String mot;
    private int ligne;
    private int colonne;
    private int longueur;
    private boolean estLigne;

    public MotJeu(int id, String mot, int ligne, int colonne, int longueur, boolean estLigne) {
        this.id = id;
        this.mot = mot;
        this.ligne = ligne;
        this.colonne = colonne;
        this.longueur = longueur;
        this.estLigne = estLigne;
    }

    public int getId(){
        return this.id;
    }

    public String getMot() {
        return this.mot;
    }

    public int getLigne() {
        return this.ligne;
    }

    public int getColonne() {
        return this.colonne;
    }

    public int getLongueur() {
        return this.longueur;
    }

    public boolean estLigne(){
        return estLigne;
    }
}
