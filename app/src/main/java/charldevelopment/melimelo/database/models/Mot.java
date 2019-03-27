package charldevelopment.melimelo.database.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

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
}
