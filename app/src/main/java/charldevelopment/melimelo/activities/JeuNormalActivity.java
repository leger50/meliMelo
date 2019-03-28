package charldevelopment.melimelo.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import java.lang.reflect.Array;
import java.util.Map;
import java.util.Random;

import charldevelopment.melimelo.R;
import charldevelopment.melimelo.classes.GrilleJeu;
import charldevelopment.melimelo.database.models.Mot;
import charldevelopment.melimelo.database.repositories.MotDataRepository;
import charldevelopment.melimelo.views.MotViewModel;

public class JeuNormalActivity extends AppCompatActivity implements View.OnClickListener {

    private List<Mot> listeMots;
    private GrilleJeu grille;
    private TextView compteur;
    private HashMap listeMotInsere;
    private TextView resultat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu_normal);

        Button jouer = (Button) findViewById(R.id.btn_play);
        jouer.setOnClickListener(this);
/*
        MotViewModel motView = new MotViewModel(this.getApplication());
        this.listeMots = motView.obtenirListeMots().getValue();
        System.out.println(this.listeMots.toString());
        System.out.println("Size : " + this.listeMots.size());*/

        this.grille = new GrilleJeu();

        TextView textView = (TextView) findViewById(R.id.view_jeu);

        textView.setText(this.grille.toStringActivity());

        this.listeMotInsere = this.grille.getListeMot();

        this.compteur = (TextView) findViewById(R.id.view_compteur);

        this.compteur.setText("Mots à trouver : "+listeMotInsere.size());

        this.resultat = (TextView) findViewById(R.id.view_resultat);

    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_play:
                EditText reponse = (EditText) findViewById(R.id.saisieMot);
                String mot = reponse.getText().toString();

                Iterator<Map.Entry<String, charldevelopment.melimelo.classes.Mot>> iterator = this.listeMotInsere.entrySet().iterator();
                boolean isFound = false;
                while (iterator.hasNext() && !isFound) {
                    Map.Entry<String, charldevelopment.melimelo.classes.Mot> motInsere = (Map.Entry<String, charldevelopment.melimelo.classes.Mot>) iterator.next();
                    if(motInsere.getValue().getMot().equals(mot)){
                        isFound = true;
                        this.listeMotInsere.remove(motInsere.getKey());
                        this.compteur.setText("Mots à trouver : "+listeMotInsere.size());
                        this.resultat.setText("Bravo !");
                    }
                }
                if(!isFound){
                    this.resultat.setText("Dommage ...");
                }
               break;
        }
    }
}
