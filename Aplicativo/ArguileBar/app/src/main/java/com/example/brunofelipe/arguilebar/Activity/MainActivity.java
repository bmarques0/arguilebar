package com.example.brunofelipe.arguilebar.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.brunofelipe.arguilebar.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    protected void onClick(View view) {
        Intent it = new Intent(this, FazerPedidoCateg.class);
        startActivity(it);
    }


}
