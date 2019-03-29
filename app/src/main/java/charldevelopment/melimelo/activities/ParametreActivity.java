package charldevelopment.melimelo.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import charldevelopment.melimelo.R;

public class ParametreActivity extends AppCompatActivity implements View.OnClickListener {

    private Switch switch_langueFrancais;
    private Switch switch_langueAnglais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametre_);

        Button retour = (Button) findViewById(R.id.button_retour);
        Switch switch_vibreur = (Switch) findViewById(R.id.switch_vibreur);
        Switch switch_son = (Switch) findViewById(R.id.switch_son);
        Switch switch_notifications = (Switch) findViewById(R.id.switch_notifications);

        SharedPreferences prefs = getSharedPreferences("parametres", MODE_PRIVATE);
        boolean vibreur = prefs.getBoolean("vibreur",true);
        boolean son = prefs.getBoolean("son",true);
        boolean notifications = prefs.getBoolean("notifications",true);

        switch_vibreur.setChecked(vibreur);
        switch_son.setChecked(son);
        switch_notifications.setChecked(notifications);

        retour.setOnClickListener(this);
        switch_vibreur.setOnClickListener(this);
        switch_son.setOnClickListener(this);
        switch_notifications.setOnClickListener(this);

        //Paramètre des langues
        this.switch_langueFrancais = (Switch) findViewById(R.id.switch_french);
        this.switch_langueAnglais = (Switch) findViewById(R.id.switch_english);

        this.switch_langueFrancais.setOnCheckedChangeListener(new OnCheckedChange_SwitchFrancais());
        this.switch_langueAnglais.setOnCheckedChangeListener(new OnCheckedChange_SwitchAnglais());
    }

    @Override
    public void onClick(View v) {
        SharedPreferences prefs = getSharedPreferences("parametres", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        switch(v.getId()) {
            case R.id.button_retour :
                finish();
                break;

            case R.id.switch_vibreur :
                boolean vibreur = prefs.getBoolean("vibreur",true);//"No name defined" is the default value
                editor.putBoolean("vibreur", !vibreur);
                editor.apply();

                if(!vibreur){
                    vibrator.vibrate(100);
                }
                break;

            case R.id.switch_son :
                boolean son = prefs.getBoolean("son",true);
                editor.putBoolean("son", !son);
                editor.apply();
                break;

            case R.id.switch_notifications :
                boolean notifications = prefs.getBoolean("notifications",true);
                editor.putBoolean("notifications", !notifications);
                editor.apply();
                break;
        }

    }

    private class OnCheckedChange_SwitchFrancais implements CompoundButton.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(isChecked)
                switch_langueAnglais.setChecked(false);

            else
                switch_langueAnglais.setChecked(true);
        }
    }

    private class OnCheckedChange_SwitchAnglais implements CompoundButton.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(isChecked)
                switch_langueFrancais.setChecked(false);

            else
                switch_langueFrancais.setChecked(true);
        }
    }
}
