package charldevelopment.melimelo.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import charldevelopment.melimelo.R;
import charldevelopment.melimelo.classes.GrilleJeu;
import charldevelopment.melimelo.database.models.Mot;
import charldevelopment.melimelo.database.repositories.MotRepository;

public class JeuNormalActivity extends AppCompatActivity implements View.OnClickListener{

    private List<Mot> listeMots;
    private GrilleJeu grille;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu_normal);

        Button btnValiderMot = (Button) findViewById(R.id.activityJeuNormal_btnValiderMot);
        btnValiderMot.setOnClickListener(this);

        // TODO : mettre en place
        this.recupererListeMots();


    }

    @Override
    public void onClick(View v) {

        switch(v.getId()) {

            case R.id.activityJeuNormal_btnValiderMot:
                Toast.makeText(getApplicationContext(), "bouton", Toast.LENGTH_SHORT).show();

                break;

            default:
                break;
        }
    }

    private void recupererListeMots() {
        MotRepository motRepository = new MotRepository(this.getApplication());
        this.listeMots = motRepository.obtenirListeMots();

        if(this.listeMots == null){
            System.out.println("Liste Nulle");
        }else{
            System.out.println(Mot.listerMots(this.listeMots));
            System.out.println("size : " + this.listeMots.size());
        }
    }
}
