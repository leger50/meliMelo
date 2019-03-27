package charldevelopment.melimelo.database.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "parties")
public class Partie {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private Date date;
    private int dureeSecondes;

    public Partie(int id, int dureeSecondes){
        this.id = id;
        this.date = new Date();
        this.dureeSecondes = dureeSecondes;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getDureeSecondes() {
        return this.dureeSecondes;
    }

    public void setDureeSecondes(int dureeSecondes) {
        this.dureeSecondes = dureeSecondes;
    }
}
