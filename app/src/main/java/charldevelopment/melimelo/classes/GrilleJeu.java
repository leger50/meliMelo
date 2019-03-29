package charldevelopment.melimelo.classes;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import charldevelopment.melimelo.database.models.Mot;

public class GrilleJeu {

    private static final int NB_METHODES = 2;
    private static final String[] ALPHABET = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    private static final String MARQUE_VIDE = ".";

    private final int tailleJeu;
    private final int nbMots = 10;

    private String[][] grille;
    private HashMap<Integer, MotJeu> listeMot;

    public GrilleJeu(int tailleJeu, List<Mot> listeMotsComplet) {
        this.tailleJeu = tailleJeu;
        this.grille = new String[this.tailleJeu][this.tailleJeu];
        this.listeMot= new HashMap<>();

        this.initialiserGrille();
        this.recupererMotsAleatoires(listeMotsComplet, this.nbMots);
        this.remplirVide();
    }

    private void initialiserGrille() {
        for (int i = 0; i < this.tailleJeu; i++) {
            for (int j = 0; j < this.tailleJeu; j++) {
                this.grille[i][j] = this.MARQUE_VIDE;
            }

        }
    }

    private void remplirVide(){
        Random rand = new Random();

        for (int i = 0; i < this.tailleJeu; i++){
            for(int j = 0; j <this.tailleJeu; j++){
                if (this.grille[i][j].equals(this.MARQUE_VIDE)){
                    this.grille[i][j]=this.ALPHABET[rand.nextInt(26)];
                }
            }
        }
    }

    public String affichageGrilleJeu(){

        StringBuilder retour = new StringBuilder();

        for (int i = 0; i < this.tailleJeu; i++){
            for (int j = 0; j < this.tailleJeu; j++){
                retour.append("   ");
                retour.append(this.grille[i][j]);
                retour.append("   ");
            }
            retour.append("\n");
        }

        return retour.toString();
    }

    public String toString(){

        StringBuilder retour = new StringBuilder();

        for (int i = 0; i < this.tailleJeu; i++){
            for (int j = 0; j < this.tailleJeu; j++){
                retour.append(this.grille[i][j]);
            }
        }

        return retour.toString();
    }

    private void recupererMotsAleatoires(List<Mot> listeComplete, int nbMotsAtrouver){
        Random aleatoire = new Random();
        int nbMotsChoisis = 0;

        while(nbMotsChoisis < nbMotsAtrouver){
            int index = aleatoire.nextInt(listeComplete.size());
            String motChoisi = listeComplete.get(index).getTexte().toUpperCase();

            if(motChoisi.length() < this.tailleJeu){
                this.insererMot(motChoisi);
                nbMotsChoisis++;
            }
        }
    }

    private void insererMot (String mot){
        Random rand = new Random();

        int choixMethode = rand.nextInt(this.NB_METHODES);

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

        int ligne = random.nextInt(this.tailleJeu);

        int colonneMax = this.tailleJeu -mot.length();

        int colonne = random.nextInt(colonneMax);


        int essais = 0;
        for(int k = colonne; k < mot.length()+colonne; k++){
            if(!(this.grille[ligne][k].equals(this.MARQUE_VIDE))){
                ligne = random.nextInt(this.tailleJeu);
                colonne = random.nextInt(colonneMax);
                k=colonne;
                essais++;
                if(essais>50){
                    return(false);
                }
            }
        }

        if(!(this.grille[ligne][colonne].equals(this.MARQUE_VIDE))){ //Problème superpostion première lettre
            return false;
        }

        int index = 1;
        for(int i = colonne; i < mot.length()+colonne; i++){
            this.grille[ligne][i]=tabMot[index];
            index++;

        }

        int id = this.listeMot.size();
        this.listeMot.put(id,new MotJeu(id,mot,ligne,colonne,mot.length(),false));
        return true;
    }

    private boolean insererMotColonne(String mot) {
        Random random = new Random();

        String[] tabMot = mot.split("");

        int colonne = random.nextInt(this.tailleJeu);

        int ligneMax = this.tailleJeu - mot.length();

        int ligne = random.nextInt(ligneMax);


        int essais = 0;
        for (int k = ligne; k < mot.length() + ligne; k++) {
            if (!(this.grille[k][colonne].equals(this.MARQUE_VIDE))) {
                colonne = random.nextInt(this.tailleJeu);
                ligne = random.nextInt(ligneMax);
                k = ligne;
                essais++;
                if (essais > 50) {
                    return (false);
                }
            }
        }

        if(!(this.grille[ligne][colonne].equals(this.MARQUE_VIDE))){ //Problème superposition première lettre
            return false;
        }

        int index = 1;
        for (int i = ligne; i < mot.length() + ligne; i++) {
            this.grille[i][colonne] = tabMot[index];
            index++;

        }

        int id = this.listeMot.size();
        this.listeMot.put(id,new MotJeu(id,mot,ligne,colonne,mot.length(),false));
        return true;
    }

    public HashMap getListeMot(){
        return this.listeMot;
    }
}
