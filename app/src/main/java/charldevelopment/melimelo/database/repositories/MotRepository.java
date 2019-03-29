package charldevelopment.melimelo.database.repositories;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import charldevelopment.melimelo.database.MeliMeloDatabase;
import charldevelopment.melimelo.database.dao.MotDao;
import charldevelopment.melimelo.database.models.Mot;

public class MotRepository {

    private final MotDao motDao;

    public MotRepository(Application application) {
        MeliMeloDatabase db = MeliMeloDatabase.getDatabase(application);
        this.motDao = db.motDao();
    }

    public List<Mot> obtenirListeMots() {
        return this.motDao.obtenirListeMots();
    }

    public void insererUnMot(Mot mot) {
        new insertAsyncTask(this.motDao).execute(mot);
    }

    /**
     * Méthode d'insertion : doit être appelée dans un thread autre que le main,
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
}
