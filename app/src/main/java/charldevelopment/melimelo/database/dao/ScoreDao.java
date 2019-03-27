package charldevelopment.melimelo.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.ArrayList;

import charldevelopment.melimelo.database.models.Partie;
import charldevelopment.melimelo.database.models.Score;

@Dao
public interface ScoreDao {

    @Query("SELECT * FROM scores")
    public ArrayList<Score> obtenirListeScores();

    @Insert
    public void insererUnScore(Score score);

    @Delete
    public void supprimerUnScore(Score score);
}
