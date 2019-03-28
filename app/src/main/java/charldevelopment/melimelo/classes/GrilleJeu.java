package charldevelopment.melimelo.classes;

import java.util.Random;

public class GrilleJeu {

    private final int TAILLE = 10;
    private String[][] grille;
    private static final int NBMETHODE = 2;
    private static final String[] ALPHABET = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    private static final String MARQUE_VIDE = ".";

    public GrilleJeu() {
        this.grille = new String[this.TAILLE][this.TAILLE];
        for (int i = 0; i < this.TAILLE;i++) {
            for (int j = 0; j < this.TAILLE; j++) {
                this.grille[i][j] = this.MARQUE_VIDE;
            }

        }
        this.insererMot("COUCOU");
        this.insererMot("CHARLIE");
        this.insererMot("NATHAN");
        this.insererMot("QUENTIN");
        this.insererMot("LARA");
        this.insererMot("POKEMON");
        this.insererMot("GAETAN");
        this.insererMot("ADRIEN");
        this.insererMot("SYLVAIN");
        this.insererMot("JEREMY");

        //this.remplirVide();

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
        Random rand = new Random();

        int choixMethode = rand.nextInt(this.NBMETHODE);

        switch (choixMethode){
            case 0 : insererMotLigne(mot);
            break;
            case 1 : insererMotColonne(mot);
            break;
        }


    }

    private boolean insererMotLigne(String mot) {
        Random random = new Random();

        String[] tabMot = mot.split("");

        int ligne = random.nextInt(this.TAILLE);

        int colonneMax = this.TAILLE-mot.length();

        int colonne = random.nextInt(colonneMax);


        int essais = 0;
        for(int k = colonne; k < mot.length()+colonne; k++){
            //System.out.println(this.grille[ligne][k]);
            if(!(this.grille[ligne][k].equals(this.MARQUE_VIDE))){
                //System.out.println("OK!");
                ligne = random.nextInt(this.TAILLE);
                colonne = random.nextInt(colonneMax);
                k=colonne;
                essais++;
                if(essais>50){
                    return(false);
                }
            }
        }

        int index = 1;
        for(int i = colonne; i < mot.length()+colonne; i++){
            this.grille[ligne][i]=tabMot[index];
            index++;

        }
        return true;
    }

    private boolean insererMotColonne(String mot) {
        Random random = new Random();

        String[] tabMot = mot.split("");

        int colonne = random.nextInt(this.TAILLE);

        int ligneMax = this.TAILLE - mot.length();

        int ligne = random.nextInt(ligneMax);


        int essais = 0;
        for (int k = ligne; k < mot.length() + ligne; k++) {
            if (!(this.grille[k][colonne].equals(this.MARQUE_VIDE))) {
                colonne = random.nextInt(this.TAILLE);
                ligne = random.nextInt(ligneMax);
                k = ligne;
                essais++;
                if (essais > 50) {
                    return (false);
                }
            }
        }

        int index = 1;
        for (int i = ligne; i < mot.length() + ligne; i++) {
            this.grille[i][colonne] = tabMot[index];
            index++;

        }
        return true;
    }

    private void remplirVide(){
        Random rand = new Random();

        for (int i = 0; i < this.TAILLE; i++){
            for(int j = 0; j <this.TAILLE; j++){
                if (this.grille[i][j].equals(this.MARQUE_VIDE)){
                    this.grille[i][j]=this.ALPHABET[rand.nextInt(26)];
                }
            }
        }
    }
}
