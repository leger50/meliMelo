package charldevelopment.melimelo.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import charldevelopment.melimelo.R;

public class ParametreActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametre_);

        Button retour = (Button) findViewById(R.id.button_retour);
        Switch switch_vibreur = (Switch) findViewById(R.id.switch_vibreur);
        Switch switch_notifications = (Switch) findViewById(R.id.switch_notifications);

        SharedPreferences prefs = getSharedPreferences("parametres", MODE_PRIVATE);
        boolean vibreur = prefs.getBoolean("vibreur",true);//"No name defined" is the default value
        boolean notifications = prefs.getBoolean("notifications",true);


        switch_vibreur.setChecked(vibreur);
        switch_notifications.setChecked(notifications);

        retour.setOnClickListener(this);
        switch_notifications.setOnClickListener(this);
        switch_vibreur.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        SharedPreferences prefs = getSharedPreferences("parametres", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        switch(v.getId()) {
            case R.id.button_retour :
                finish();
                break;
            case R.id.switch_vibreur :
                boolean vibreur = prefs.getBoolean("vibreur",true);//"No name defined" is the default value
                editor.putBoolean("vibreur", !vibreur);
                editor.apply();
                break;
            case R.id.switch_notifications :
                boolean notifications = prefs.getBoolean("notifications",true);//"No name defined" is the default value
                editor.putBoolean("notifications", !notifications);
                editor.apply();
                break;
        }

    }
}
