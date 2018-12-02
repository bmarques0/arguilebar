package com.example.brunofelipe.arguilebar.Activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ListView;
import android.support.v7.widget.SearchView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.brunofelipe.arguilebar.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PedidoSessao extends AppCompatActivity {

    private static final String URL_DATA="http://192.168.0.104:8081/tcc2/sendVolleyEssencia.php";
    //private static final String URL_DATA = "http://192.168.64.2/tcc2/json.php";
    private List<ModelItem> modelItems;

    ListView listViewSessao;
    ModelItem modelItem;
    ModelItem item2;
    ModelItem item;
    ArrayList<ModelItem> arrayList = new ArrayList<ModelItem>();
    ArrayList<ModelItem> modellistIntent;
    ListViewAdapter adapter;
    ImageButton imageProduto;

    Button btnSelecionar;
    int id;
    RadioButton radiobutMeioaMeio;
    RadioButton radiobutInteiro;
    RadioGroup radioGroup;
    Switch st;
    int idEssencia;
    String sabor, marca, preco;
    Intent itEssenciaBebida;
    SharedPreferences prefs;
    int countEssencia;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_sessao);


        imageProduto = findViewById(R.id.mainIcon);
        radioGroup = findViewById(R.id.idRadioGroup);
        radiobutMeioaMeio = findViewById(R.id.radiobutIdMeio);
        radiobutInteiro = findViewById(R.id.radiobuttonIdInteiro);
        btnSelecionar =  findViewById(R.id.selecionarPedidoId);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listViewSessao = findViewById(R.id.listViewSessao);
        modelItems = new ArrayList<>();

        loadListViewAdapter();

        //apresentando o resultado na listviewadpter
        adapter = new ListViewAdapter(this, arrayList);

        //vincular o adapter com a lista
        listViewSessao.setAdapter(adapter);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(radiobutMeioaMeio.isChecked()){
                        Toast.makeText(getApplicationContext(), "Selecione até 2 essência!", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), "Selecione somente 1 essência", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void loadListViewAdapter()
    {

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
                            JSONArray array = jsonObject.getJSONArray("essencia");

                            for(int i = 0; i<array.length(); i++){
                                JSONObject o = array.getJSONObject(i);
                                ModelItem item = new ModelItem(
                                o.getInt("id_essencia"),
                                o.getString("sabor"),
                                o.getString("marca"),
                                o.getString("preco"),
                                o.getString("categoria"),
                                o.getString("quantidade"),
                                o.getString("essenciaImg"),
                                o.getString("descricao")

                                );
                                modelItems.add(item);
                            }
                            adapter = new ListViewAdapter(getApplicationContext(), modelItems);
                            listViewSessao.setAdapter(adapter);
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
    public boolean onCreateOptionsMenu(Menu menu)
    {
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
                    adapter.filter("");
                    listViewSessao.clearTextFilter();
                }
                else {
                    adapter.filter(newText);
                }
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if(id == R.id.action_settings)
        {
            //
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void DetalheProduto (View view)
    {
        //onClick foto produto para detalhes do produto
        imageProduto = (ImageButton) findViewById(R.id.mainIcon);
        imageProduto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent it = new Intent(PedidoSessao.this, DetalhesProduto.class);
                startActivity(it);

                //intent.putExtra("idEssencia",id);

                //intent.putExtra("sabor",sabor);
                //intent.putExtra("marca",marca);
                //intent.putExtra("preco",preco);

                //startActivity(intent);

                //modelItems = new ArrayList();
                //modelItems.add(item);
                //Intent intent = new Intent(getApplicationContext(), ConfirmarPedido.class);
                //intent.putExtra("arrayEssencia", (Serializable) modelItems);

            }

        });
    }

    public void selecionarPedido (View view)
    {

        btnSelecionar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                int defaultid = 0, defaultCount=0;
                String defaultSabor = null, defaultMarca = null, defaultPreco = null;

                //Montar o objeto e transferir de intent

                prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                countEssencia = prefs.getInt("countEssencia", defaultCount);
                itEssenciaBebida = new Intent(PedidoSessao.this, ConfirmarPedido.class);

                if(countEssencia == 1) {
                    int id = prefs.getInt("idEssencia1", defaultid);
                    String sabor = prefs.getString("sabor1", defaultSabor);
                    String marca = prefs.getString("marca1", defaultMarca);
                    String preco = prefs.getString("preco1", defaultPreco);
                    item = new ModelItem(id, sabor, marca, preco);

                    itEssenciaBebida.putExtra("Essencia", item);
                    itEssenciaBebida.putExtra("countEssencia", countEssencia);
                }
                if(countEssencia == 2) {

                    int id = prefs.getInt("idEssencia1", defaultid);
                    String sabor = prefs.getString("sabor1", defaultSabor);
                    String marca = prefs.getString("marca1", defaultMarca);
                    String preco = prefs.getString("preco1", defaultPreco);
                    item = new ModelItem(id, sabor, marca, preco);

                        int id2 = prefs.getInt("idEssencia2", defaultid);
                        String sabor2 = prefs.getString("sabor2", defaultSabor);
                        String marca2 = prefs.getString("marca2", defaultMarca);
                        String preco2 = prefs.getString("preco2", defaultPreco);
                        item2 = new ModelItem(id2, sabor2, marca2, preco2);

                    itEssenciaBebida.putExtra("Essencia", item);
                    itEssenciaBebida.putExtra("Essencia2", item2);
                    itEssenciaBebida.putExtra("countEssencia", countEssencia);

                }



                MensagemConfirmacao(view);
            }

        });
    }

    public void MensagemConfirmacao (View view)
    {
                //Mensagem de confirmação
                AlertDialog.Builder builder = new AlertDialog.Builder(PedidoSessao.this);
                builder.setTitle("Fazer novo pedido");
                builder.setMessage("Deseja Solicitar algo para beber?");


                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {


                        // Salvar o objeto selecionado
                        // Transferir para a tela de bebidas
                        Intent itEssenciaBebida = new Intent(PedidoSessao.this, PedidoBebida.class);
                        itEssenciaBebida.putExtra("Essencia", item);
                        itEssenciaBebida.putExtra("Essencia2", item2);
                        itEssenciaBebida.putExtra("countEssencia", countEssencia);
                        startActivity(itEssenciaBebida);
                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Salvar o objeto selecionado
                        // Transferir para a tela de Confirmar pedido
                        startActivity(itEssenciaBebida);
                        dialog.dismiss();

                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
    }

}