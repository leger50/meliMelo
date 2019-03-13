package charldevelopment.melimelo.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import charldevelopment.melimelo.R;

public class AccueilActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

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
}
