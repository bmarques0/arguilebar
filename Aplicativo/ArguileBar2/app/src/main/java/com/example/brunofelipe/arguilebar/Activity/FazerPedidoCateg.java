package com.example.brunofelipe.arguilebar.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.brunofelipe.arguilebar.R;

public class FazerPedidoCateg extends AppCompatActivity {

    ImageButton idImageSessao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fazer_pedido_categ);


    }

    public void fazerPedido (View view) {
        idImageSessao = (ImageButton) findViewById(R.id.idImageSessao);
        idImageSessao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent it = new Intent(FazerPedidoCateg.this, PedidoSessao.class);
                startActivity(it);
            }

        });
    }


}