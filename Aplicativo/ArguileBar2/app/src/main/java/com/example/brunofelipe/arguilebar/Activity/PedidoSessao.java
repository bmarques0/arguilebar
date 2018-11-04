package com.example.brunofelipe.arguilebar.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.support.v7.widget.SearchView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.brunofelipe.arguilebar.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PedidoSessao extends AppCompatActivity {

    ListView listViewSessao;
    ListViewAdapter adpter;
    String[] title;
    String[] description;
    int[] icon;
    ArrayList<ModelItem> arrayList = new ArrayList<ModelItem>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_sessao);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        title = new String[]{ "Zomo", "Adalya", "FML"};
        description = new String[]{"Descricao zomo", "Descricao Adalya", "Descricao FML"};
        icon = new int[]{R.drawable.essencia_zomo_timao, R.drawable.essencia_adalya_love66, R.drawable.essencia_fml_red};

        listViewSessao = findViewById(R.id.listViewSessao);

        //criar a lista inteira de acordo com o row criada
        for(int i=0; i<title.length; i++){
            ModelItem model = new ModelItem(title[i], description[i], icon[i]);
            //vicular todas as strings no array
            arrayList.add(model);

        }
        //apresentando o resultado na listviewadpter
        adpter = new ListViewAdapter(this, arrayList);


        //vincular o adapter com a lista
        listViewSessao.setAdapter(adpter);

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
                    adpter.filter("");
                    listViewSessao.clearTextFilter();
                }
                else {
                    adpter.filter(newText);
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

        if(radioMeio.isChecked()==true) {
            radioInteiro.setChecked(false);
            radioMeio.setChecked(true);

        }else {
            radioInteiro.setChecked(true);
            radioMeio.setChecked(false);
        }

    }
}