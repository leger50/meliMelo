package charldevelopment.melimelo.classes;

public class GrilleJeu {

    private char[][] grilleLettres;
    private int tailleGrilleHorizontale;
    private int tailleGrilleVerticale;

    public GrilleJeu(int tailleGrilleHorizontale, int tailleGrilleVerticale){
        this.grilleLettres = new char[this.tailleGrilleHorizontale][this.tailleGrilleVerticale];
        this.tailleGrilleHorizontale = tailleGrilleHorizontale;
        this.tailleGrilleVerticale = tailleGrilleVerticale;

        this.initialiserGrille();
    }

    private void initialiserGrille(){

        char lettre = 'a';

        for(int i=0; i<this.tailleGrilleHorizontale; i++){
            for (int j=0; j<this.tailleGrilleVerticale; j++){
                this.grilleLettres[i][j] = lettre;
                lettre++;
            }
        }

    }
}
