package com.example.brunofelipe.arguilebar.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.brunofelipe.arguilebar.R;

import java.lang.reflect.Array;
import java.util.Map;

public class ConfirmarPedido extends AppCompatActivity {

    Button BtnConfirmarPedido;
    Button BtnVoltar;
    ListView listViewConfirPedido;
    private static final String URL_POST="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_pedido);

        BtnConfirmarPedido = (Button)findViewById(R.id.selecionarPedidoId);
        BtnVoltar = (Button)findViewById(R.id.selecionarPedidoId);
        listViewConfirPedido = (ListView)findViewById(R.id.listViewConfirPedido);


        ConfirmarPedido();

    }

    public void CorrigirPedido(View view) {

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

    public void ConfirmarPedido () {


        BtnConfirmarPedido = (Button) findViewById(R.id.selecionarPedidoId);
        BtnConfirmarPedido.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                //Enviar Json para Painel Adm
                StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_POST, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
//                    @Override
//                    protected Map<Array> getParams() throws AuthFailureError {
//                        return (Map<Array>) super.getParams();
//                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(ConfirmarPedido.this);
                requestQueue.add(stringRequest);

            }
        });
    }

}


//
//                //Mensagem de sucesso
//                Toast.makeText(getApplicationContext(),"Pedido Realizado com sucesso! ",Toast.LENGTH_LONG).show();
//
//
//                //Retornar para a página inicial
//                Intent it = new Intent(ConfirmarPedido.this, MainActivity.class);
//                startActivity(it);