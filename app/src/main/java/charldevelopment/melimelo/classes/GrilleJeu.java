package charldevelopment.melimelo.classes;

import java.util.Random;

public class GrilleJeu {

    private final int TAILLE = 10;
    private String[][] grille;
    private static final int NBMETHODE = 2;
    private static final String[] ALPHABET = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

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

    private void insererMotLigne(String mot) {
        Random random = new Random();

        String[] tabMot = mot.split("");

        int ligne = random.nextInt(this.TAILLE);

        int colonneMax = this.TAILLE-mot.length();

        int colonne = random.nextInt(colonneMax);

        for(int k = colonne; k < mot.length()+colonne; k++){
            if(!this.grille[ligne][k].equals(".")){
                System.out.println(this.grille[ligne][k]);
                ligne = random.nextInt(this.TAILLE);
                colonne = random.nextInt(colonneMax);
                k=0;
            }
        }

        int index = 1;
        for(int i = colonne; i < mot.length()+colonne; i++){
            this.grille[ligne][i]=tabMot[index];
            index++;
        }
    }

    private void insererMotColonne(String mot) {
        Random random = new Random();

        String[] tabMot = mot.split("");

        int colonne = random.nextInt(this.TAILLE);

        int ligneMax = this.TAILLE-mot.length();

        int ligne = random.nextInt(ligneMax);

        for(int k = ligne; k < mot.length()+ligne; k++){
            if(!this.grille[k][colonne].equals(".")){
                ligne = random.nextInt(ligneMax);
                colonne = random.nextInt(this.TAILLE);
                k=0;
            }
        }

        int index = 1;
        for(int i = ligne; i < mot.length()+ligne; i++){
            this.grille[i][colonne]=tabMot[index];
            index++;
        }
    }

    private void remplirVide(){
        Random rand = new Random();

        for (int i = 0; i < this.TAILLE; i++){
            for(int j = 0; j <this.TAILLE; j++){
                if (this.grille[i][j].equals(".")){
                    this.grille[i][j]=this.ALPHABET[rand.nextInt(26)];
                }
            }
        }
    }
}
