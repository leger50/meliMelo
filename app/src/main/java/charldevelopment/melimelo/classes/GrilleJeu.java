package charldevelopment.melimelo.classes;

import java.util.Random;

public class GrilleJeu {

    private final int TAILLE = 10;
    private String[][] grille;

    public GrilleJeu() {
        this.grille = new String[this.TAILLE][this.TAILLE];
        for (int i = 0; i < this.TAILLE;i++) {
            for (int j = 0; j < this.TAILLE; j++) {
                this.grille[i][j] = ".";
            }

        }
        this.insererMot("COUCOU");
        this.insererMot("CHARLIE");
        this.insererMot("NATHAN");
        this.insererMot("QUENTIN");
        this.insererMot("LARA");
        this.insererMot("POKEMON");

        this.toString();
    }

    public String toString(){

        String retour = "";

        for (int i = 0; i < this.TAILLE;i++){
            for (int j = 0; j < this.TAILLE; j++){
                retour += this.grille[i][j];
            }
            System.out.println(retour);
            retour = "";

        }

        return retour;
    }

    public void insererMot (String mot){
        Random random = new Random();

        String[] tabMot = mot.split("");

        int ligne = random.nextInt(this.TAILLE);

        int colonneMax = this.TAILLE-mot.length();

        for(int k = 0; k < colonneMax; k++){
            if(!this.grille[ligne][k].equals(".")){
                ligne = random.nextInt(this.TAILLE);
                k=0;
            }
        }

        int colonne = random.nextInt(colonneMax);

        int index = 1;
        for(int i = colonne; i < mot.length()+colonne; i++){
            this.grille[ligne][i]=tabMot[index];
            index++;
        }
    }
}
