package charldevelopment.melimelo.views;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import charldevelopment.melimelo.database.models.Mot;
import charldevelopment.melimelo.database.repositories.MotDataRepository;

public class MotViewModel extends AndroidViewModel {

    private MotDataRepository motRepository;
    private LiveData<List<Mot>> listeMots;

    public MotViewModel (Application application) {
        super(application);
        this.motRepository = new MotDataRepository(application);
        this.listeMots = motRepository.obtenirListeMots();
    }

    public LiveData<List<Mot>> obtenirListeMots() {
        return this.listeMots;
    }

    public void insert(Mot mot) {
        motRepository.insert(mot);
    }

}
