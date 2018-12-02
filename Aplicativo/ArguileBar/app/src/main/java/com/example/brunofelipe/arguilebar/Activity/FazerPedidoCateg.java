package com.example.brunofelipe.arguilebar.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.brunofelipe.arguilebar.R;

public class FazerPedidoCateg extends AppCompatActivity {

    ImageButton idImageSessao;
    ImageButton idImageBebida;
    int countEssencia=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fazer_pedido_categ);



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

    public void fazerPedidoBebida (View view) {
        idImageBebida = (ImageButton) findViewById(R.id.idImageBebidas);

        idImageBebida.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent it = new Intent(FazerPedidoCateg.this, PedidoBebida.class);
                it.putExtra("countEssencia", countEssencia);
                startActivity(it);
            }

        });
    }



}