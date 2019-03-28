package charldevelopment.melimelo.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import charldevelopment.melimelo.BuildConfig;
import charldevelopment.melimelo.R;
import charldevelopment.melimelo.database.models.Mot;
import charldevelopment.melimelo.database.repositories.MotRepository;

public class AccueilActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        this.checkFirstRun();

        Button jouer = (Button) findViewById(R.id.btn_play);
        jouer.setOnClickListener(this);

        Button parametres = (Button) findViewById(R.id.btn_parameter);
        parametres.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn_play :
                Intent intent = new Intent(AccueilActivity.this, ParametresPartieActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_parameter :
                Intent intent2 = new Intent(AccueilActivity.this, ParametreActivity.class);
                startActivity(intent2);
                break;
            default:
                break;
        }

    }

    /*
    * On vérifie si l'application a déja été lancée pour pouvoir initialiser
    * la base de données contenant les mots
     */
    private void checkFirstRun() {

        final String PREFS_NAME = "MyPrefsFile";
        final String PREF_VERSION_CODE_KEY = "version_code";
        final int DOESNT_EXIST = -1;

        // Get current version code
        int currentVersionCode = BuildConfig.VERSION_CODE;

        // Get saved version code
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int savedVersionCode = prefs.getInt(PREF_VERSION_CODE_KEY, DOESNT_EXIST);

        // Check for first run or upgrade
        if (currentVersionCode == savedVersionCode) {

            // This is just a normal run
            return;

        } else if (savedVersionCode == DOESNT_EXIST) {

            // TODO This is a new install (or the user cleared the shared preferences)
            this.initialiserBaseDeDonnees();

        } else if (currentVersionCode > savedVersionCode) {

            // TODO This is an upgrade
            this.initialiserBaseDeDonnees();

        }

        // Update the shared preferences with the current version code
        prefs.edit().putInt(PREF_VERSION_CODE_KEY, currentVersionCode).apply();
    }

    private void initialiserBaseDeDonnees() {
        MotRepository motDataRepository = new MotRepository(this.getApplication());
        //motDataRepository.initDatabase();
        motDataRepository.insert(new Mot(1, "caMarchep"));


    }
}
