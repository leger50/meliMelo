package charldevelopment.melimelo.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import java.lang.reflect.Array;
import java.util.Random;

import charldevelopment.melimelo.R;
import charldevelopment.melimelo.database.models.Mot;
import charldevelopment.melimelo.database.repositories.MotDataRepository;
import charldevelopment.melimelo.views.MotViewModel;

public class JeuNormalActivity extends AppCompatActivity {

    private List<Mot> listeMots;

    private final int TAILLE = 10;
    private String[][] grille;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu_normal);

        /*Button jouer = (Button) findViewById(R.id.btn_play);
        jouer.setOnClickListener(this);

        MotViewModel motView = new MotViewModel(this.getApplication());
        this.listeMots = motView.obtenirListeMots().getValue();
        System.out.println(this.listeMots.toString());
        System.out.println("Size : " + this.listeMots.size());*/


        this.grille = new String[this.TAILLE][this.TAILLE];

        for (int i = 0; i < this.TAILLE;i++){
            for (int j = 0; j < this.TAILLE; j++){
                this.grille[i][j] = "." ;
            }
        }

        this.insererMot("coucou");
        this.insererMot("Charlie");
        this.insererMot("Nathan");
        this.insererMot("Quentin");
        this.insererMot("Lara");
        this.insererMot("Pokemon");

        this.toString();
    }

    public String toString(){

        String retour = "";

        for (int i = 0; i < this.TAILLE;i++){
            for (int j = 0; j < this.TAILLE; j++){
               retour += this.grille[i][j];
            }
            System.out.println(retour);
            retour = "";

        }

        return retour;
    }

    public void insererMot (String mot){
        Random random = new Random();

        String[] tabMot = mot.split("");

        int ligne = random.nextInt(this.TAILLE);

        int colonneMax = this.TAILLE-mot.length();

        for(int k = 0; k < colonneMax; k++){
            if(!this.grille[ligne][k].equals(".")){
                ligne = random.nextInt(this.TAILLE);
                k=0;
            }
        }

        int colonne = random.nextInt(colonneMax);

        int index = 1;
        for(int i = colonne; i < mot.length()+colonne; i++){
            this.grille[ligne][i]=tabMot[index];
            index++;
        }
    }
}
