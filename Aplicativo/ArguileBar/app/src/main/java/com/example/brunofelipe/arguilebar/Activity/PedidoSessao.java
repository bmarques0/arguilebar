package com.example.brunofelipe.arguilebar.Activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

import java.util.ArrayList;
import java.util.List;

public class PedidoSessao extends AppCompatActivity {

    private static final String URL_DATA="http://192.168.1.3:8081/tcc2/webservice/sendVolley.php";
    private List<ModelItem> modelItems;

    ListView listViewSessao;
    ListViewAdapter adapter;
    String[] title;
    String[] description;
    int[] icon;
    ArrayList<ModelItem> arrayList = new ArrayList<ModelItem>();
    ImageButton imageProduto;
    Switch btnSwitch;
    Button btnSelecionar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_sessao);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        title = new String[]{ "Zomo", "Adalya", "FML"};
//        description = new String[]{"Descricao zomo", "Descricao Adalya", "Descricao FML"};
//        icon = new int[]{R.drawable.essencia_zomo_timao, R.drawable.essencia_adalya_love66, R.drawable.essencia_fml_red};

        listViewSessao = findViewById(R.id.listViewSessao);

        modelItems = new ArrayList<>();
        loadListViewAdapter();

        //apresentando o resultado na listviewadpter
        adapter = new ListViewAdapter(this, arrayList);

        //vincular o adapter com a lista
        listViewSessao.setAdapter(adapter);


    }

    private void loadListViewAdapter() {

        ProgressDialog progressDialog = new ProgressDialog(this);
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

                                        //[{"id_essencia":"5","sabor":"Morango","marca":"Marca3","preco":"42","categoria":"Premium",
                                        // "essenciaImg":null,"quantidade":"10"}
                                        o.getString("sabor"),
                                        o.getString("marca"),
                                        o.getString("essenciaImg")

                                );

                                modelItems.add(item);

                            }
                            adapter = new ListViewAdapter(getApplicationContext(), modelItems);
                            listViewSessao.setAdapter(adapter);

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
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_settings) {
            //
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void RadioCheck (View view) {

        RadioButton radioMeio = (RadioButton)findViewById(R.id.radiobutIdMeio);
        RadioButton radioInteiro = ((RadioButton)findViewById(R.id.radiobuttonIdInteiro));

        final RadioGroup group = (RadioGroup) findViewById(R.id.idRadioGroup);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton button = (RadioButton) group.findViewById(checkedId);
                String resposta = button.getText().toString();
            }
        });

    }

    public void DetalheProduto (View view) {


        //onClick foto produto para detalhes do produto
        imageProduto = (ImageButton) findViewById(R.id.mainIcon);
        imageProduto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent it = new Intent(PedidoSessao.this, DetalhesProduto.class);
                startActivity(it);
            }

        });

    }


    public void onClickNumberButton (View view) {


           btnSwitch = (Switch) findViewById(R.id.swichButtonId);
           btnSwitch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                    String statusSwitch1;
                    if (btnSwitch.isChecked())
                        statusSwitch1 = btnSwitch.getTextOn().toString();
                    else
                        statusSwitch1 = btnSwitch.getTextOff().toString();
                        Toast.makeText(getApplicationContext(), "Switch1 :" + statusSwitch1, Toast.LENGTH_LONG).show();
                }
            });


            }


    public void selecionarPedido (View view) {


        btnSelecionar = (Button) findViewById(R.id.selecionarPedidoId);
        btnSelecionar.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                Intent it = new Intent(PedidoSessao.this, DetalhesProduto.class);
                startActivity(it);

                //Montar o objeto e transferir de intent



                //Mensagem de confirmação

                AlertDialog.Builder builder = new AlertDialog.Builder(PedidoSessao.this);

                builder.setTitle("Fazer novo pedido");
                builder.setMessage("Deseja Solicitar algo para beber?");

                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                        // Salvar o objeto selecionado
                        // Transferir para a tela de bebidas
                        Intent it = new Intent(PedidoSessao.this, ConfirmarPedido.class);
                        startActivity(it);

                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Salvar o objeto selecionado
                        // Transferir para a tela de Confirmar pedido
                        Intent it = new Intent(PedidoSessao.this, ConfirmarPedido.class);
                        startActivity(it);
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }

        });




    }
}