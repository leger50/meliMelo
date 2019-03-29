package charldevelopment.melimelo.classes;

public class MotJeu {

    private int id;
    private String mot;
    private int ligne;
    private int colonne;
    private int longeur;
    private boolean isligne;

    public MotJeu(int id, String mot, int ligne, int colonne, int longeur, boolean isLigne) {
        this.id = id;
        this.mot = mot;
        this.ligne = ligne;
        this.colonne = colonne;
        this.longeur = longeur;
        this.isligne = isLigne;
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

    public int getLongeur() {
        return this.longeur;
    }

    public boolean getIsLigne(){
        return isligne;
    }
}
