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

    private String messageInformatif;
    private String messageReussite;
    private String messageEchec;

    private GrilleJeu grille;
    private TextView compteur;
    private HashMap listeMotsInseres;
    private TextView resultat;
    private GridView motTrouve;
    private ArrayList<String> listeDeMotsTrouves = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu_normal);

        this.initialiserMessagesJeu();

        Button btnValiderMot = (Button) findViewById(R.id.activityJeuNormal_btnValiderMot);
        btnValiderMot.setOnClickListener(this);

        List<Mot> listeMotsComplete = this.recupererListeMots();

        /*Partie grille de jeu*/
        this.grille = new GrilleJeu(10, listeMotsComplete);

        TextView textView = (TextView) findViewById(R.id.view_jeu);
        textView.setText(this.grille.affichageGrilleJeu());

        this.listeMotsInseres = this.grille.getListeMot();

        this.compteur = (TextView) findViewById(R.id.view_compteur);
        this.compteur.setText(this.messageInformatif + listeMotsInseres.size());

        this.resultat = (TextView) findViewById(R.id.view_resultat);
        this.motTrouve = (GridView) findViewById(R.id.word_find);

    }

    @Override
    public void onClick(View v){

        switch (v.getId()){
            case R.id.activityJeuNormal_btnValiderMot:
                EditText reponse = (EditText) findViewById(R.id.saisieMot);
                String mot = reponse.getText().toString();

                Iterator<Map.Entry<String, MotJeu>> iterator2 = this.listeMotsInseres.entrySet().iterator();
                boolean isFound = false;

                while (iterator2.hasNext() && !isFound) {
                    Map.Entry<String, MotJeu> motInsere = iterator2.next();

                    if(motInsere.getValue().getMot().equals(mot)){
                        isFound = true;

                        //jeu
                        this.listeMotsInseres.remove(motInsere.getKey());
                        this.compteur.setText(this.messageInformatif + listeMotsInseres.size());
                        this.resultat.setText(this.messageReussite);

                        //mise à jour GridView
                        updateGridView(motInsere);

                        //vibreur
                        this.declencherVibreur(100);
                    }
                }

                //en cas d'échec
                if(!isFound){
                    this.resultat.setText(this.messageEchec);
                    this.declencherVibreur(400);
                }

                //vider champ de saisie
                reponse.setText("");

                //enlever le clavier
                removeKeyboard();
                break;
        }
    }

    private void removeKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    /**
     * Fonction permettant de mettre à jour notre vue lorsqu'un mot est inséré
     */
    private void updateGridView(Map.Entry<String, MotJeu> motInsere) {
        this.listeDeMotsTrouves.add(motInsere.getValue().getMot());

        //maj Grid
        final List<String> listeMotTrouve = new ArrayList<String>(this.listeDeMotsTrouves);
        final ArrayAdapter<String> gridViewArrayAdapter = new ArrayAdapter<String>
                (this,android.R.layout.simple_list_item_1, listeMotTrouve);
        this.motTrouve.setAdapter(gridViewArrayAdapter);
    }

    /**
     * Fonction permettant de récupérer la liste de mot complète depuis la base de données
     */
    private List<Mot> recupererListeMots() {
        MotRepository motRepository = new MotRepository(this.getApplication());
        List<Mot> listeMots = motRepository.obtenirListeMots();

        if(listeMots == null){
            throw new NullPointerException("Erreur de la récupération des données");
        }

        return listeMots;
    }

    /**
     * Fonction permettant de déclencher le vibreur selon une durée déterminée.
     * Les préférences de l'utilisateur quant à l'utilisation des vibrations sont prises en compte.
     */
    private void declencherVibreur(int temps) {
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        SharedPreferences prefs = getSharedPreferences("parametres", MODE_PRIVATE);
        boolean vibreur = prefs.getBoolean("vibreur", true);

        if (vibreur) {
            vibrator.vibrate(temps);
        }
    }

    /**
     * Fonction permettant d'initialiser les messages dynamiques de la partie
     */
    private void initialiserMessagesJeu() {
        this.messageInformatif = getResources().getString(R.string.activityJeuNormal_messageInformatif);
        this.messageReussite = getResources().getString(R.string.activityJeuNormal_messageReussite);
        this.messageEchec = getResources().getString(R.string.activityJeuNormal_messageEchec);
    }
}
