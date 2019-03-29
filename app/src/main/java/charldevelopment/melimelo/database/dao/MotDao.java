package charldevelopment.melimelo.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.ArrayList;
import java.util.List;

import charldevelopment.melimelo.database.models.Mot;

@Dao
public interface MotDao {

    @Query("SELECT * FROM mots")
    public List<Mot> obtenirListeMots();

    @Insert
    public void insererListeMots(List<Mot> mots);

    @Insert
    public void insererUnMot(Mot mot);

    @Delete
    public void supprimerUnMot(Mot mot);
}
