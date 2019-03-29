package charldevelopment.melimelo.database.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

import charldevelopment.melimelo.database.MotsParDefaut;

@Entity(tableName = "mots")
public class Mot {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String texte;

    public Mot(int id, String texte){
        this.id = id;
        this.texte = texte;
    }

    public int getId() {
        return this.id;
    }

    public String getTexte() {
        return this.texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        final StringBuilder chaine = new StringBuilder("Mot{");
        chaine.append("id=").append(id);
        chaine.append(", texte='").append(texte).append('\'');
        chaine.append('}');

        return chaine.toString();
    }

    /**
     * Fonction permettant de lister tous les mots d'une liste donnée
     */
    public static String listerMots(List<Mot> listeMots){
        StringBuilder chaine = new StringBuilder();

        for(Mot mot : listeMots){
            chaine.append(mot);
            chaine.append("\n");
        }

        return chaine.toString();
    }

    /**
     * Fonction permettant de créer une liste de mots à partir de la classe 'MotsParDefaut'
     */
    public static List<Mot> obtenirMotsInitialisation(){

        String[] listeMots = MotsParDefaut.listeMotsDefaut;
        List<Mot> motsInitialisation = new ArrayList<>();

        for(int i=0; i<listeMots.length; i++){
            motsInitialisation.add(new Mot(i,listeMots[i]));
        }

        return motsInitialisation;
    }
}
