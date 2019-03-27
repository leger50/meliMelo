package charldevelopment.melimelo.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import charldevelopment.melimelo.R;
import charldevelopment.melimelo.database.models.Mot;
import charldevelopment.melimelo.database.repositories.MotDataRepository;
import charldevelopment.melimelo.views.MotViewModel;

public class JeuNormalActivity extends AppCompatActivity {

    private List<Mot> listeMots;

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

    }
}
