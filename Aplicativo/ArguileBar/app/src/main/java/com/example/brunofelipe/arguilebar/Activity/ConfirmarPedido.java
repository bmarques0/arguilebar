package com.example.brunofelipe.arguilebar.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.brunofelipe.arguilebar.R;

public class ConfirmarPedido extends AppCompatActivity {

    Button BtnConfirmarPedido;
    Button BtnVoltar;
    ListView listViewConfirPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_pedido);

        BtnConfirmarPedido = (Button)findViewById(R.id.voltarPedidoId);
        BtnVoltar = (Button)findViewById(R.id.selecionarPedidoId);
        listViewConfirPedido = (ListView)findViewById(R.id.listViewConfirPedido);

    }

    public void VoltarPedido(View view) {

        //onClick foto produto para detalhes do produto
        BtnVoltar = (Button) findViewById(R.id.btnVoltar);
        BtnVoltar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent it = new Intent(ConfirmarPedido.this, PedidoSessao.class);
                startActivity(it);
            }

        });
    }

    public void selecionarPedido (View view) {


        BtnConfirmarPedido = (Button) findViewById(R.id.selecionarPedidoId);
        BtnConfirmarPedido.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                //Enviar Json para Painel Adm


                //Mensagem de sucesso
                Toast.makeText(getApplicationContext(),"Pedido Realizado com sucesso! ",Toast.LENGTH_LONG).show();


                //Retornar para a página inicial
                Intent it = new Intent(ConfirmarPedido.this, MainActivity.class);
                startActivity(it);


                }
            });

        }




    }



