package charldevelopment.melimelo.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.ArrayList;

import charldevelopment.melimelo.database.models.Partie;

@Dao
public interface PartieDao {

    @Query("SELECT * FROM parties")
    public ArrayList<Partie> obtenirListeParties();

    @Insert
    public void insererUnePartie(Partie partie);

    @Delete
    public void supprimerUnePartie(Partie partie);
}
