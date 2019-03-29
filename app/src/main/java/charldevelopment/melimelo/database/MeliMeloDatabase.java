package charldevelopment.melimelo.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.List;

import charldevelopment.melimelo.database.dao.MotDao;
import charldevelopment.melimelo.database.models.Mot;

@Database(entities = {Mot.class}, version = 1, exportSchema = false)
public abstract class MeliMeloDatabase extends RoomDatabase {

    public abstract MotDao motDao();

    /**
     *  Méthodes statiques destinées à la création de la database
     *  On utilise un singleton pour éviter d'avoir plusieurs instances de la base de données ouvertes en même temps
     */
    private static volatile MeliMeloDatabase INSTANCE;

    public static MeliMeloDatabase getDatabase(final Context context) {

        if (INSTANCE == null) {
            synchronized (MeliMeloDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MeliMeloDatabase.class, "meli_melo_database")
                            .addCallback(sRoomDatabaseCallback)
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * On redéfinit ici la méthode 'onCreate' pour pouvoir remplir notre base de données
     * lors de sa création
     */
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    /**
     * Remplit la base de données en arrière plan
     */
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final MotDao motDao;

        PopulateDbAsync(MeliMeloDatabase db) {
            motDao = db.motDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {

            List<Mot> listeMots = Mot.obtenirMotsInitialisation();
            motDao.insererListeMots(listeMots);

            motDao.supprimerUnMot(MotsParDefaut.creationBD);
            return null;
        }

    }

}




