package charldevelopment.melimelo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import charldevelopment.melimelo.R;

public class ParametresPartieActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametres_partie);

        Button jouer = (Button) findViewById(R.id.btn_play);
        jouer.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn_play :
                Intent intent = new Intent(ParametresPartieActivity.this,JeuNormalActivity.class);
                startActivity(intent);
                finish();
                break;

            default:
        }

    }
}
