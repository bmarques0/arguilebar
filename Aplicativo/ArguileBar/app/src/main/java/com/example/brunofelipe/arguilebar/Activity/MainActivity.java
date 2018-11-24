package com.example.brunofelipe.arguilebar.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.brunofelipe.arguilebar.R;

public class MainActivity extends Activity {

     Button btn_inicio;

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
            }
        });
    }


}
