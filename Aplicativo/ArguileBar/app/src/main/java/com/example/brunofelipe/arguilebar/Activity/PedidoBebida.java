package com.example.brunofelipe.arguilebar.Activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.brunofelipe.arguilebar.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PedidoBebida extends AppCompatActivity {

    private static final String URL_DATA="http://192.168.0.104:8081/tcc2/sendVolleyBebida.php";
    //private static final String URL_DATA = "http://192.168.64.2/tcc2/json.php";
    private List<ModelItemBebida> modelItemsBebida;

    ListView listViewBebida;
    ModelItemBebida itemBebida1, itemBebida2, itemBebida3, itemBebida4,itemBebida5;
    ArrayList<ModelItemBebida> arrayList = new ArrayList<ModelItemBebida>();
    ArrayList<ModelItemBebida> modellistIntent;
    ListViewAdapterBebida adapterBebida;
    ImageButton imageProdutoBebida;
    Button btnSelecionarBebida;
    int id;
    Switch st;
    int idBebida;
    ModelItem item;
    ModelItem item2;
    String marcaBebida, precoBebida;
    Intent itEssenciaBebida;
    Intent itEssenciaBebidaConf;
    Intent it;
    SharedPreferences prefs;
    int countPref, countEssencia=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_bebida);
        
        imageProdutoBebida = findViewById(R.id.mainIconBebida);
        btnSelecionarBebida =  findViewById(R.id.selecionarPedidoIdBebida);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listViewBebida = findViewById(R.id.listViewBebida);
        modelItemsBebida = new ArrayList<>();

        loadListViewAdapter();

        //apresentando o resultado na listviewadpter
        adapterBebida = new ListViewAdapterBebida(this, arrayList);

        //vincular o adapter com a lista
        listViewBebida.setAdapter(adapterBebida);

        itEssenciaBebida = getIntent();
        countEssencia = (int) itEssenciaBebida.getSerializableExtra("countEssencia");

//        if (countEssencia==0) {
//            countEssencia = (int) itEssenciaBebida.getSerializableExtra("countEssencia");
//        }
        if(countEssencia==1){
            item = (ModelItem) itEssenciaBebida.getSerializableExtra("Essencia");
        }
        if(countEssencia==2) {
            item = (ModelItem) itEssenciaBebida.getSerializableExtra("Essencia");
            item2 = (ModelItem) itEssenciaBebida.getSerializableExtra("Essencia2");
        }

    }

    private void loadListViewAdapter() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Carregando dados..");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONArray array = jsonObject.getJSONArray("bebida");

                            for(int i = 0; i<array.length(); i++){
                                JSONObject o = array.getJSONObject(i);
                                ModelItemBebida item = new ModelItemBebida(

                                o.getInt("id_prodDiversos"),
                                o.getString("marca"),
                                o.getString("preco"),
                                o.getString("quantidade"),
                                o.getString("bebidaImg")


                                );
                                modelItemsBebida.add(item);
                            }
                            adapterBebida = new ListViewAdapterBebida(getApplicationContext(), modelItemsBebida);
                            listViewBebida.setAdapter(adapterBebida);
                            progressDialog.dismiss();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
                    RequestQueue requestQueue = Volley.newRequestQueue(this);
                    requestQueue.add(stringRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView)myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(TextUtils.isEmpty(newText)) {
                    adapterBebida.filter("");
                    listViewBebida.clearTextFilter();
                }
                else {
                    adapterBebida.filter(newText);
                }
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_settings) {
            //
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void selecionarPedidoBebida (View view)
    {
        btnSelecionarBebida =  findViewById(R.id.selecionarPedidoIdBebida);
        btnSelecionarBebida.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int defaultidBebida = 0, defaultCount=0;
                String defaultMarcaBebida = null, defaultPrecoBebida = null;

                //Montar o objeto e transferir de intent
                prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                countPref = prefs.getInt("count", defaultCount);

                //1
                if(countPref == 1) {
                    int idBebida1 = prefs.getInt("idBebida1", defaultidBebida);
                    String marcaBebida1 = prefs.getString("marca1", defaultMarcaBebida);
                    String precoBebida1 = prefs.getString("preco1", defaultPrecoBebida);
                    itemBebida1 = new ModelItemBebida(idBebida1, marcaBebida1, precoBebida1);
                }//2
                //if (prefs.getString("marca2", defaultMarcaBebida) != null)
                if(countPref == 2)
                {

                    int idBebida1 = prefs.getInt("idBebida1", defaultidBebida);
                    String marcaBebida1 = prefs.getString("marca1", defaultMarcaBebida);
                    String precoBebida1 = prefs.getString("preco1", defaultPrecoBebida);
                    itemBebida1 = new ModelItemBebida(idBebida1, marcaBebida1, precoBebida1);

                    int idBebida2 = prefs.getInt("idBebida2", defaultidBebida);
                    String marcaBebida2 = prefs.getString("marca2", defaultMarcaBebida);
                    String precoBebida2 = prefs.getString("preco2", defaultPrecoBebida);
                    itemBebida2 = new ModelItemBebida(idBebida2, marcaBebida2, precoBebida2);

                }
                //3
                //if (prefs.getString("marca3", defaultMarcaBebida) != null)
                if(countPref == 3)
                {

                    int idBebida1 = prefs.getInt("idBebida1", defaultidBebida);
                    String marcaBebida1 = prefs.getString("marca1", defaultMarcaBebida);
                    String precoBebida1 = prefs.getString("preco1", defaultPrecoBebida);
                    itemBebida1 = new ModelItemBebida(idBebida1, marcaBebida1, precoBebida1);

                    int idBebida2 = prefs.getInt("idBebida2", defaultidBebida);
                    String marcaBebida2 = prefs.getString("marca2", defaultMarcaBebida);
                    String precoBebida2 = prefs.getString("preco2", defaultPrecoBebida);
                    itemBebida2 = new ModelItemBebida(idBebida2, marcaBebida2, precoBebida2);


                    int idBebida3 = prefs.getInt("idBebida3", defaultidBebida);
                    String marcaBebida3 = prefs.getString("marca3", defaultMarcaBebida);
                    String precoBebida3 = prefs.getString("preco3", defaultPrecoBebida);
                    itemBebida3 = new ModelItemBebida(idBebida3, marcaBebida3, precoBebida3);
                }
                //4
                //if (prefs.getString("marca4", defaultMarcaBebida) != null)
                if(countPref == 4)
                {
                    int idBebida1 = prefs.getInt("idBebida1", defaultidBebida);
                    String marcaBebida1 = prefs.getString("marca1", defaultMarcaBebida);
                    String precoBebida1 = prefs.getString("preco1", defaultPrecoBebida);
                    itemBebida1 = new ModelItemBebida(idBebida1, marcaBebida1, precoBebida1);

                    int idBebida2 = prefs.getInt("idBebida2", defaultidBebida);
                    String marcaBebida2 = prefs.getString("marca2", defaultMarcaBebida);
                    String precoBebida2 = prefs.getString("preco2", defaultPrecoBebida);
                    itemBebida2 = new ModelItemBebida(idBebida2, marcaBebida2, precoBebida2);


                    int idBebida3 = prefs.getInt("idBebida3", defaultidBebida);
                    String marcaBebida3 = prefs.getString("marca3", defaultMarcaBebida);
                    String precoBebida3 = prefs.getString("preco3", defaultPrecoBebida);
                    itemBebida3 = new ModelItemBebida(idBebida3, marcaBebida3, precoBebida3);

                    int idBebida4 = prefs.getInt("idBebida4", defaultidBebida);
                    String marcaBebida4 = prefs.getString("marca4", defaultMarcaBebida);
                    String precoBebida4 = prefs.getString("preco4", defaultPrecoBebida);
                    itemBebida4 = new ModelItemBebida(idBebida4, marcaBebida4, precoBebida4);

                }
                //5
                //if (prefs.getString("marca5", defaultMarcaBebida) != null)
                if(countPref == 5)
                {

                    int idBebida1 = prefs.getInt("idBebida1", defaultidBebida);
                    String marcaBebida1 = prefs.getString("marca1", defaultMarcaBebida);
                    String precoBebida1 = prefs.getString("preco1", defaultPrecoBebida);
                    itemBebida1 = new ModelItemBebida(idBebida1, marcaBebida1, precoBebida1);

                    int idBebida2 = prefs.getInt("idBebida2", defaultidBebida);
                    String marcaBebida2 = prefs.getString("marca2", defaultMarcaBebida);
                    String precoBebida2 = prefs.getString("preco2", defaultPrecoBebida);
                    itemBebida2 = new ModelItemBebida(idBebida2, marcaBebida2, precoBebida2);


                    int idBebida3 = prefs.getInt("idBebida3", defaultidBebida);
                    String marcaBebida3 = prefs.getString("marca3", defaultMarcaBebida);
                    String precoBebida3 = prefs.getString("preco3", defaultPrecoBebida);
                    itemBebida3 = new ModelItemBebida(idBebida3, marcaBebida3, precoBebida3);

                    int idBebida4 = prefs.getInt("idBebida4", defaultidBebida);
                    String marcaBebida4 = prefs.getString("marca4", defaultMarcaBebida);
                    String precoBebida4 = prefs.getString("preco4", defaultPrecoBebida);
                    itemBebida4 = new ModelItemBebida(idBebida4, marcaBebida4, precoBebida4);

                    int idBebida5 = prefs.getInt("idBebida5", defaultidBebida);
                    String marcaBebida5 = prefs.getString("marca5", defaultMarcaBebida);
                    String precoBebida5 = prefs.getString("preco5", defaultPrecoBebida);
                    itemBebida5 = new ModelItemBebida(idBebida5, marcaBebida5, precoBebida5);

                }

                //bebida
                itEssenciaBebidaConf = new Intent(PedidoBebida.this, ConfirmarPedido.class);


                //bebida 1
                if( countPref == 1 ) {
                    itEssenciaBebidaConf.putExtra("Bebida1", (Serializable) itemBebida1);
                    itEssenciaBebidaConf.putExtra("countBebida", (Serializable) countPref);
                } if (countPref == 2 ) {

                    itEssenciaBebidaConf.putExtra("Bebida1", (Serializable) itemBebida1);
                    itEssenciaBebidaConf.putExtra("Bebida2", (Serializable) itemBebida2);
                    itEssenciaBebidaConf.putExtra("countBebida", (Serializable) countPref);
                }if (countPref == 3 ) {

                    itEssenciaBebidaConf.putExtra("Bebida1", (Serializable) itemBebida1);
                    itEssenciaBebidaConf.putExtra("Bebida2", (Serializable) itemBebida2);
                    itEssenciaBebidaConf.putExtra("Bebida3", (Serializable) itemBebida3);
                    itEssenciaBebidaConf.putExtra("countBebida", (Serializable) countPref);
                }if (countPref == 4 ) {

                    itEssenciaBebidaConf.putExtra("Bebida1", (Serializable) itemBebida1);
                    itEssenciaBebidaConf.putExtra("Bebida2", (Serializable) itemBebida2);
                    itEssenciaBebidaConf.putExtra("Bebida3", (Serializable) itemBebida3);
                    itEssenciaBebidaConf.putExtra("Bebida4", (Serializable) itemBebida4);
                    itEssenciaBebidaConf.putExtra("countBebida", (Serializable) countPref);
                }if (countPref == 5 ) {

                    itEssenciaBebidaConf.putExtra("Bebida1", (Serializable) itemBebida1);
                    itEssenciaBebidaConf.putExtra("Bebida2", (Serializable) itemBebida2);
                    itEssenciaBebidaConf.putExtra("Bebida3", (Serializable) itemBebida3);
                    itEssenciaBebidaConf.putExtra("Bebida4", (Serializable) itemBebida4);
                    itEssenciaBebidaConf.putExtra("Bebida5", (Serializable) itemBebida5);
                    itEssenciaBebidaConf.putExtra("countBebida", (Serializable) countPref);
                }

                if(countEssencia==0){
                    itEssenciaBebidaConf.putExtra("Essencia", (Serializable) item);
                    itEssenciaBebidaConf.putExtra("countEssencia", (Serializable) countEssencia);
                }
                if(countEssencia==1){
                    itEssenciaBebidaConf.putExtra("Essencia", (Serializable) item);
                    itEssenciaBebidaConf.putExtra("countEssencia", (Serializable) countEssencia);
                }
                if(countEssencia==2) {
                    itEssenciaBebidaConf.putExtra("Essencia", (Serializable) item);
                    itEssenciaBebidaConf.putExtra("Essencia2", (Serializable) item2);
                    itEssenciaBebidaConf.putExtra("countEssencia", (Serializable) countEssencia);
                }



                //Intent com OU SÓ BEBIDA OU BEBIDA E ESSENCIA
                startActivity(itEssenciaBebidaConf);
            }


        });

    }


}