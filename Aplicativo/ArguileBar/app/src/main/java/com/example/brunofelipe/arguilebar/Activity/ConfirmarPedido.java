package com.example.brunofelipe.arguilebar.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
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

    String server_url = "http://192.168.0.104:8081/tcc2/requestVolley.php";


    private RequestQueue mQueue;

    Button BtnConfirmarPedido;
    Button BtnConfirmarPedidoBebida;
    ListView listViewConfirPedido;
    ModelItem modelItem;
    ModelItemBebida modelItemBebida;
    ListView listViewSessao;
    ListView listViewBebida;
    ListViewAdapterConfPedido adapter;
    ArrayList<ModelItemBebida> modellistBebida;
    ModelItem item;
    ModelItem item2;
    ModelItemBebida itemBebida1, itemBebida2, itemBebida3, itemBebida4,itemBebida5;
    List<ModelItem> modelItemArrayJson;
    List<ModelItemBebida> modelItemBebidaArrayJson;
    String newModelItemArrayJson;
    String newModelItemBebidaArrayJson;
    int idEssencia, idBebida;
    String sabor, marca, preco, qtdade;
    ArrayList modelist;
    Intent itEssenciaBebida, itEssenciaBebidaConf;
    List<ModelItemBebEssencia> listProdutos;
    ModelItemBebEssencia model1, model2; //essencias
    int countBebida=0, countEssencia=0;
    ModelItemBebEssencia model3, model4, model5, model6, model7;   //bebidas

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_pedido);

        BtnConfirmarPedido = (Button)findViewById(R.id.btnSelecionar) ;
        listViewConfirPedido = (ListView)findViewById(R.id.listViewSessao);

//        int defaultCount=0, defaultCountEssencia=0;
//        int countEssencia=0, countBebida=0;

//        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//        SharedPreferences.Editor ed1 = prefs.edit();
//        SharedPreferences.Editor ed2 = prefs.edit();
//
//        countEssencia = prefs.getInt("countEssencia", defaultCount);
//        countBebida = prefs.getInt("countBebida", defaultCount);


        //se for só essencia entra
        itEssenciaBebida = getIntent();

        ////se for essencia e bebida entra
        itEssenciaBebidaConf = getIntent();
        itemBebida1 = (ModelItemBebida) itEssenciaBebidaConf.getSerializableExtra("Bebida1");

        if(itemBebida1==null){
            countEssencia = (int) itEssenciaBebida.getSerializableExtra("countEssencia");
        }
        if(itemBebida1!=null) {
            countEssencia = (int) itEssenciaBebidaConf.getSerializableExtra("countEssencia");
            countBebida = (int) itEssenciaBebidaConf.getSerializableExtra("countBebida");
        }


        if(countEssencia == 1) {
            item = (ModelItem) itEssenciaBebida.getSerializableExtra("Essencia");
        }


        if(countEssencia == 2) {
            item = (ModelItem) itEssenciaBebida.getSerializableExtra("Essencia");
            item2 = (ModelItem) itEssenciaBebida.getSerializableExtra("Essencia2");
        }



        if( countBebida == 1 ) {
            itemBebida1 = (ModelItemBebida) itEssenciaBebidaConf.getSerializableExtra("Bebida1");

        }

        if( countBebida == 2 ) {
            itemBebida1 = (ModelItemBebida) itEssenciaBebidaConf.getSerializableExtra("Bebida1");
            itemBebida2 = (ModelItemBebida) itEssenciaBebidaConf.getSerializableExtra("Bebida2");
        }

        if( countBebida == 3 ) {
            itemBebida1 = (ModelItemBebida) itEssenciaBebidaConf.getSerializableExtra("Bebida1");
            itemBebida2 = (ModelItemBebida) itEssenciaBebidaConf.getSerializableExtra("Bebida2");
            itemBebida3 = (ModelItemBebida) itEssenciaBebidaConf.getSerializableExtra("Bebida3");
        }

        if( countBebida == 4 ) {
            itemBebida1 = (ModelItemBebida) itEssenciaBebidaConf.getSerializableExtra("Bebida1");
            itemBebida2 = (ModelItemBebida) itEssenciaBebidaConf.getSerializableExtra("Bebida2");
            itemBebida3 = (ModelItemBebida) itEssenciaBebidaConf.getSerializableExtra("Bebida3");
            itemBebida4 = (ModelItemBebida) itEssenciaBebidaConf.getSerializableExtra("Bebida4");
        }

        if( countBebida == 5 ) {
            itemBebida1 = (ModelItemBebida) itEssenciaBebidaConf.getSerializableExtra("Bebida1");
            itemBebida1 = (ModelItemBebida) itEssenciaBebidaConf.getSerializableExtra("Bebida1");
            itemBebida2 = (ModelItemBebida) itEssenciaBebidaConf.getSerializableExtra("Bebida2");
            itemBebida3 = (ModelItemBebida) itEssenciaBebidaConf.getSerializableExtra("Bebida3");
            itemBebida4 = (ModelItemBebida) itEssenciaBebidaConf.getSerializableExtra("Bebida4");
            itemBebida5 = (ModelItemBebida) itEssenciaBebidaConf.getSerializableExtra("Bebida5");

        }

        //Se tiver essencia e bebida
//
//        if(itemBebida1 != null) {
//            item = (ModelItem) itEssenciaBebida.getSerializableExtra("Essencia");
//            if (itEssenciaBebida.getSerializableExtra("Essencia2") != null) {
//                item2 = (ModelItem) itEssenciaBebida.getSerializableExtra("Essencia2");
//            }
//
//        //se so tiver essencia
//        }else {
//
//            item = (ModelItem) itEssenciaBebida.getSerializableExtra("Essencia");
//            if(itEssenciaBebida.getSerializableExtra("Essencia2") != null) {
//                item2 = (ModelItem) itEssenciaBebida.getSerializableExtra("Essencia2");
//            }
//        }

        listProdutos = new ArrayList<>();

        if(countEssencia == 1) {
            model1 = new ModelItemBebEssencia(item.getSabor(), item.getPreco());
            listProdutos.add(model1);
        }
        if(countEssencia == 2) {
            model1 = new ModelItemBebEssencia(item.getSabor(), item.getPreco());
            model2 = new ModelItemBebEssencia(item2.getSabor(), item2.getPreco());
            listProdutos.add(model1);
            listProdutos.add(model2);
        }



        if(countBebida == 1 ) {
            model3 = new ModelItemBebEssencia(itemBebida1.getMarcaBebida(), itemBebida1.getPrecoBebida());
            listProdutos.add(model3);
        }
        if(countBebida == 2) {
            model3 = new ModelItemBebEssencia(itemBebida1.getMarcaBebida(), itemBebida1.getPrecoBebida());
            model4 = new ModelItemBebEssencia(itemBebida2.getMarcaBebida(), itemBebida2.getPrecoBebida());

            listProdutos.add(model3);
            listProdutos.add(model4);

        }if(countBebida == 3) {
            model3 = new ModelItemBebEssencia(itemBebida1.getMarcaBebida(), itemBebida1.getPrecoBebida());
            model4 = new ModelItemBebEssencia(itemBebida2.getMarcaBebida(), itemBebida2.getPrecoBebida());
            model5 = new ModelItemBebEssencia(itemBebida3.getMarcaBebida(), itemBebida3.getPrecoBebida());

            listProdutos.add(model3);
            listProdutos.add(model4);
            listProdutos.add(model5);

        }if(countBebida == 4) {
            model3 = new ModelItemBebEssencia(itemBebida1.getMarcaBebida(), itemBebida1.getPrecoBebida());
            model4 = new ModelItemBebEssencia(itemBebida2.getMarcaBebida(), itemBebida2.getPrecoBebida());
            model5 = new ModelItemBebEssencia(itemBebida3.getMarcaBebida(), itemBebida3.getPrecoBebida());
            model6 = new ModelItemBebEssencia(itemBebida4.getMarcaBebida(), itemBebida4.getPrecoBebida());

            listProdutos.add(model3);
            listProdutos.add(model4);
            listProdutos.add(model5);
            listProdutos.add(model6);

        }if(countBebida == 5){
            model3 = new ModelItemBebEssencia(itemBebida1.getMarcaBebida(), itemBebida1.getPrecoBebida());
            model4 = new ModelItemBebEssencia(itemBebida2.getMarcaBebida(), itemBebida2.getPrecoBebida());
            model5 = new ModelItemBebEssencia(itemBebida3.getMarcaBebida(), itemBebida3.getPrecoBebida());
            model6 = new ModelItemBebEssencia(itemBebida4.getMarcaBebida(), itemBebida4.getPrecoBebida());
            model7 = new ModelItemBebEssencia(itemBebida5.getMarcaBebida(), itemBebida5.getPrecoBebida());

            listProdutos.add(model3);
            listProdutos.add(model4);
            listProdutos.add(model5);
            listProdutos.add(model6);
            listProdutos.add(model7);

    }


        adapter = new ListViewAdapterConfPedido(this, listProdutos);

        //vincular o adapter com a lista
        listViewConfirPedido.setAdapter(adapter);

    }

    public void ConfirmarPedido (View view)
    {
        BtnConfirmarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                modelItemArrayJson = new ArrayList<ModelItem>();


                //popular o arrayList essencias
                idEssencia = item.getIdEssencia();
                sabor = item.getSabor();
                marca = item.getMarca();
                preco = item.getPreco();
                qtdade = item.getQuantidade();

                modelItemArrayJson.add(item);

                if(item2.getIdEssencia() != 0) {
                    idEssencia = item2.getIdEssencia();
                    sabor = item2.getSabor();
                    marca = item2.getMarca();
                    preco = item2.getPreco();
                    qtdade = item2.getQuantidade();

                    modelItemArrayJson.add(item2);
                }

                //popular o arrayList bebidas

                idBebida = itemBebida1.getIdBebida();



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
                        param.put("idEssencia1", String.valueOf(item.getIdEssencia()));
                        param.put("idEssencia2", String.valueOf(item2.getIdEssencia()));
                        param.put("idBebida", String.valueOf(itemBebida1.getIdBebida()));
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





