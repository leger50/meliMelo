package charldevelopment.melimelo.activities;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import charldevelopment.melimelo.classes.MotJeu;
import charldevelopment.melimelo.database.models.Mot;
import charldevelopment.melimelo.database.repositories.MotRepository;

public class JeuNormalActivity extends AppCompatActivity implements View.OnClickListener{

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

        List<Mot> listeMotsComplete = this.recupererListeMots();

        /*Partie grille de jeu*/
        this.grille = new GrilleJeu(10, listeMotsComplete);

        TextView textView = (TextView) findViewById(R.id.view_jeu);
        textView.setText(this.grille.toStringActivity());

        this.listeMotInsere = this.grille.getListeMot();

        this.compteur = (TextView) findViewById(R.id.view_compteur);
        this.compteur.setText("Mots à trouver : "+listeMotInsere.size());

        this.resultat = (TextView) findViewById(R.id.view_resultat);
        this.motTrouve = (GridView) findViewById(R.id.word_find);

    }

    @Override
    public void onClick(View v){

        switch (v.getId()){
            case R.id.activityJeuNormal_btnValiderMot:
                EditText reponse = (EditText) findViewById(R.id.saisieMot);
                String mot = reponse.getText().toString();

                Iterator<Map.Entry<String, MotJeu>> iterator2 = this.listeMotInsere.entrySet().iterator();
                boolean isFound = false;
                while (iterator2.hasNext() && !isFound) {
                    Map.Entry<String, MotJeu> motInsere = iterator2.next();

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

    private List<Mot> recupererListeMots() {
        MotRepository motRepository = new MotRepository(this.getApplication());
        List<Mot> listeMots = motRepository.obtenirListeMots();

        if(listeMots == null){
            throw new NullPointerException("Erreur de la récupération des données");
        }

        return listeMots;
    }
}
