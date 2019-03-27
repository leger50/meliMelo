package charldevelopment.melimelo.database.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(
        tableName = "scores",
        foreignKeys = @ForeignKey(entity = Partie.class, parentColumns = "id", childColumns = "idPartie"))
public class Score {

    @PrimaryKey(autoGenerate = true)
    private int idScore;
    private int idPartie;
    private int score;

    public Score(int idScore, int idPartie, int score){
        this.idScore = idScore;
        this.idPartie = idPartie;
        this.score = score;
    }

    public int getIdScore() {
        return this.idScore;
    }

    public void setIdScore(int idScore) {
        this.idScore = idScore;
    }

    public int getIdPartie() {
        return this.idPartie;
    }

    public void setIdPartie(int idPartie) {
        this.idPartie = idPartie;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
