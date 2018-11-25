package com.example.brunofelipe.arguilebar.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.brunofelipe.arguilebar.R;

public class DetalhesProduto extends AppCompatActivity {

    Button botaoVoltar;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_produto);

//        Intent intent = getIntent();
//        essenciaSelecionada = (EssenciaSelecionada) intent.getSerializableExtra("essencia");
//        id = essenciaSelecionada.getId();
    }

    public void VoltarPedido(View view) {

        //onClick foto produto para detalhes do produto
        botaoVoltar = (Button) findViewById(R.id.btnVoltar);
        botaoVoltar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent it = new Intent(DetalhesProduto.this, PedidoSessao.class);
                startActivity(it);



            }

        });
    }
}
