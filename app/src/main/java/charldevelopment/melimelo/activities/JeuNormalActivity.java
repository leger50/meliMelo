package charldevelopment.melimelo.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import java.lang.reflect.Array;
import java.util.Random;

import charldevelopment.melimelo.R;
import charldevelopment.melimelo.classes.GrilleJeu;
import charldevelopment.melimelo.database.models.Mot;
import charldevelopment.melimelo.database.repositories.MotDataRepository;
import charldevelopment.melimelo.views.MotViewModel;

public class JeuNormalActivity extends AppCompatActivity  {

    private List<Mot> listeMots;
    private GrilleJeu grille;

    GridView gridView;


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

        this.grille = new GrilleJeu();

//        gridView = (GridView) findViewById(R.id.gridviewWord);
//
//        for (int i=0; i<10; i++) {
//            for (int j=0; j<10; j++) {
//                TextView dynamicTextView = new TextView(this);
//                dynamicTextView.setText(String.valueOf(i) + String.valueOf(j));
//
//                gridView.addView(dynamicTextView);
//            }
//        }

//        Ne fonctionne pas !!!!!




    }
}
