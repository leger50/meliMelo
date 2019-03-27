package charldevelopment.melimelo.database.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import charldevelopment.melimelo.database.MeliMeloDatabase;
import charldevelopment.melimelo.database.dao.MotDao;
import charldevelopment.melimelo.database.models.Mot;

public class MotDataRepository {

    private final MotDao motDao;
    private LiveData<List<Mot>> listeMots;

    public MotDataRepository(Application application) {
        MeliMeloDatabase db = MeliMeloDatabase.getDatabase(application);
        this.motDao = db.motDao();
        this.listeMots = motDao.obtenirListeMots();
    }

    public LiveData<List<Mot>> obtenirListeMots() {
        return this.listeMots;
    }

    public void insert(Mot mot) {
        new insertAsyncTask(this.motDao).execute(mot);
    }

    /*
    * Méthode insertion doit être appeler dans un thread autre que le main,
    * nous définissons donc notre tache d'insertion en mode asynchrone.
     */
    private static class insertAsyncTask extends AsyncTask<Mot, Void, Void> {

        private MotDao motAsyncTaskDao;

        insertAsyncTask(MotDao dao) {
            motAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Mot... params) {
            motAsyncTaskDao.insererUnMot(params[0]);
            return null;
        }
    }

    /*public MotDataRepository(MotDao motDao) {
        this.motDao = motDao;
    }

    //Méthodes de MotDao
    public ArrayList<Mot> obtenirListeMots() { return this.motDao.obtenirListeMots(); }*/
}
