package com.example.brunofelipe.arguilebar.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfirmarPedido extends AppCompatActivity {

    String server_url = "http://192.168.1.5:8081/tcc2/requestVolley.php";

    private RequestQueue mQueue;

    Button BtnConfirmarPedido;
    Button BtnVoltar;
    ListView listViewConfirPedido;
    ModelItem modelItem;
    ListView listViewSessao;
    ListViewAdapterConfPedido adapter;
    //ArrayList<ModelItem> modellist;
    ModelItem item;
    List<ModelItem> modelItemArrayJson;
    String newModelItemArrayJson;
    int idEssencia;
    String sabor, marca, preco, qtdade;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_pedido);

        BtnConfirmarPedido = (Button)findViewById(R.id.btnSelecionar) ;
        listViewConfirPedido = (ListView)findViewById(R.id.listViewSessao);

        ArrayList modelist = new ArrayList();
        Intent intent = getIntent();
        item = (ModelItem) intent.getSerializableExtra("Essencia");


        modelist = new ArrayList();

        modelist.add(item);

        //apresentando o resultado na listviewadpter
        adapter = new ListViewAdapterConfPedido(this, modelist);

        //vincular o adapter com a lista
        listViewConfirPedido.setAdapter(adapter);

    }


    public void ConfirmarPedido (View view)
    {
        BtnConfirmarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                modelItemArrayJson = new ArrayList<ModelItem>();

                //popular o arrayList
                idEssencia = item.getIdEssencia();
                sabor = item.getSabor();
                marca = item.getMarca();
                preco = item.getPreco();
                qtdade = item.getQuantidade();

                modelItemArrayJson.add(item);

                Gson gson = new Gson();

                newModelItemArrayJson = gson.toJson(modelItemArrayJson);

                StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                final String result = response.toString();
                                Log.d("response", "result: " + result);

                            }
                        },

                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                                error.getMessage();

                            }
                        }
                ) {

                    @Override
                    public Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> param = new HashMap<String, String>();
                        param.put("array", newModelItemArrayJson);
                        return param;
                    }

                };

                Vconnection.getInstance(getBaseContext()).addRequestQue(stringRequest);

                Toast.makeText(getApplicationContext(), "Pedido Realizado com sucesso!", Toast.LENGTH_LONG).show();
                Intent it = new Intent(ConfirmarPedido.this, MainActivity.class);
                startActivity(it);


            }
        });
        }
}





