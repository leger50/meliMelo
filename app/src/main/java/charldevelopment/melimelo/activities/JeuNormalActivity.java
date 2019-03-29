package charldevelopment.melimelo.activities;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.GridView;

import android.widget.TextView;


import java.util.ArrayList;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


import java.util.Map;


import charldevelopment.melimelo.R;
import charldevelopment.melimelo.classes.GrilleJeu;
import charldevelopment.melimelo.database.models.Mot;
import charldevelopment.melimelo.database.repositories.MotRepository;

public class JeuNormalActivity extends AppCompatActivity implements View.OnClickListener{

    private List<Mot> listeMots;
    private GrilleJeu grille;
    private TextView compteur;
    private HashMap listeMotInsere;
    private TextView resultat;
    private GridView motTrouve;
    private ArrayList<String> listDeMotTrouve = new ArrayList<>();

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
    public void onClick(View v){

        switch (v.getId()){
            case R.id.btn_play:
                EditText reponse = (EditText) findViewById(R.id.saisieMot);
                String mot = reponse.getText().toString();

                Iterator<Map.Entry<String, charldevelopment.melimelo.classes.Mot>> iterator2 = this.listeMotInsere.entrySet().iterator();
                boolean isFound = false;
                while (iterator2.hasNext() && !isFound) {
                    Map.Entry<String, charldevelopment.melimelo.classes.Mot> motInsere = (Map.Entry<String, charldevelopment.melimelo.classes.Mot>) iterator2.next();

                    if(motInsere.getValue().getMot().equals(mot)){
                        isFound = true;

                        //jeu
                        this.listeMotInsere.remove(motInsere.getKey());
                        this.compteur.setText("Mots à trouver : "+listeMotInsere.size());
                        this.resultat.setText("Bravo !");

                        listDeMotTrouve.add(motInsere.getValue().getMot());

                        //maj Grid
                        final List<String> listeMotTrouve = new ArrayList<String>(listDeMotTrouve);
                        final ArrayAdapter<String> gridViewArrayAdapter = new ArrayAdapter<String>
                                (this,android.R.layout.simple_list_item_1, listeMotTrouve);
                        motTrouve.setAdapter(gridViewArrayAdapter);

                        //vibreur
                        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        SharedPreferences prefs = getSharedPreferences("parametres", MODE_PRIVATE);
                        boolean vibreur = prefs.getBoolean("vibreur",true);//"No name defined" is the default value
                        if(vibreur){
                            vibrator.vibrate(100);
                        }
                    }
                }
                if(!isFound){
                    this.resultat.setText("Dommage ...");
                    Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    SharedPreferences prefs = getSharedPreferences("parametres", MODE_PRIVATE);
                    boolean vibreur = prefs.getBoolean("vibreur",true);//"No name defined" is the default value
                    if(vibreur){
                        vibrator.vibrate(400);
                    }
                }
                reponse.setText("");
                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
               break;
        }
    }
            case R.id.activityJeuNormal_btnValiderMot:
                Toast.makeText(getApplicationContext(), "bouton", Toast.LENGTH_SHORT).show();
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
