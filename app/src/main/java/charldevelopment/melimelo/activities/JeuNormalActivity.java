package charldevelopment.melimelo.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import charldevelopment.melimelo.R;
import charldevelopment.melimelo.classes.GrilleJeu;
import charldevelopment.melimelo.database.models.Mot;
import charldevelopment.melimelo.views.MotViewModel;

public class JeuNormalActivity extends AppCompatActivity implements View.OnClickListener{

    private List<Mot> listeMots;
    private GrilleJeu grille;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu_normal);

        Button btnValiderMot = (Button) findViewById(R.id.activityJeuNormal_btnValiderMot);
        btnValiderMot.setOnClickListener(this);

        /*MotViewModel motView = new MotViewModel(this.getApplication());
        this.listeMots = motView.obtenirListeMots().getValue();
        System.out.println(this.listeMots.toString());
        System.out.println("Size : " + this.listeMots.size());
        this.grille = new GrilleJeu();*/

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()) {

            case R.id.activityJeuNormal_btnValiderMot:
                Toast.makeText(getApplicationContext(), "bouton", Toast.LENGTH_SHORT).show();

                //A supprimer

                MotViewModel motView = new MotViewModel(this.getApplication());
                this.listeMots = motView.obtenirListeMots().getValue();

                if(this.listeMots == null){
                    System.out.println("Liste Nulle");
                }else{
                    System.out.println(this.listeMots.toString());
                    System.out.println("size : " + this.listeMots);
                }

                break;

            default:
                break;
        }
    }
}
