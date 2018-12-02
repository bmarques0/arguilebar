package com.example.brunofelipe.arguilebar.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import com.example.brunofelipe.arguilebar.R;

public class MainActivity extends Activity {

     Button btn_inicio;
    int defaultIdMesa = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_inicio = (Button) findViewById(R.id.btFazPedId);
        btn_inicio.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent it = new Intent(MainActivity.this, FazerPedidoCateg.class);
                startActivity(it);

                SharedPreferences prefs;
                prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                int nrMesa = prefs.getInt("numeroMesa", defaultIdMesa);

            }
        });
    }




}
