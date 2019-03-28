package charldevelopment.melimelo.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import java.util.Arrays;

import charldevelopment.melimelo.database.dao.MotDao;
import charldevelopment.melimelo.database.models.Mot;

@Database(entities = {Mot.class}, version = 1, exportSchema = false)
public abstract class MeliMeloDatabase extends RoomDatabase {

    public abstract MotDao motDao();

    /*
    * Méthodes statiques destinées à la création de la database
    * On utilise un singleton pour éviter d'avoir plusieurs instances de la base de données ouvertes en même temps
     */
    private static volatile MeliMeloDatabase INSTANCE;

    public static MeliMeloDatabase getDatabase(final Context context) {

        if (INSTANCE == null) {
            synchronized (MeliMeloDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MeliMeloDatabase.class, "meli_melo_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}




