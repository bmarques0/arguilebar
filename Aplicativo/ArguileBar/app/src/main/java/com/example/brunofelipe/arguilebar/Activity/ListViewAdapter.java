package com.example.brunofelipe.arguilebar.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.brunofelipe.arguilebar.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListViewAdapter extends BaseAdapter {

    //variaveis
    Context mContext;
    LayoutInflater inflater;
    List<ModelItem> modellist;
    ArrayList<ModelItem> arrayList;
    Switch st;
    int idEssencia;
    String marca;
    String sabor;
    String preco;
    ArrayList<ModelItem> modellistSeleciona = new ArrayList<>();
    ModelItem item;
    List<ModelItem> listaModellist = new ArrayList<ModelItem>();


    public ListViewAdapter(Context context, List<ModelItem> modellist) {
        mContext = context;
        this.modellist = modellist;
        inflater = LayoutInflater.from(mContext);
        this.arrayList = new ArrayList<ModelItem>();
        this.arrayList.addAll(modellist);
    }


    public class ViewHolder{

        TextView sabor;
        TextView descricao;
        TextView preco;
        ImageView mIconSessao;
        Switch st;
    }

    @Override
    public int getCount() {
        return modellist.size();
    }

    @Override
    public Object getItem(int i) {
        return modellist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view==null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.row_list_view, null);

            //Localizar as views da row.xml
            holder.sabor = view.findViewById(R.id.saborId);
            holder.descricao = view.findViewById(R.id.descricaoId);
            holder.preco = view.findViewById(R.id.valorId);
            holder.st = view.findViewById(R.id.swichButtonId);
            view.setTag(holder);

            st = view.findViewById(R.id.swichButtonId);
            st.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if (isChecked) {

                        //Toast.makeText(mContext, "Checbox de ON!", Toast.LENGTH_SHORT).show();
                        idEssencia = modellist.get(position).idEssencia;
                        sabor = modellist.get(position).sabor;
                        marca = modellist.get(position).marca;
                        preco = modellist.get(position).preco;

                        item = new ModelItem(idEssencia, sabor, marca, preco);
                        listaModellist.add(item);

                        //modellistSeleciona.add(item);

                        int count = listaModellist.size();
                        if(count > 0){
                            SharedPreferences prefs;
                            prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
                            if(count == 1){
                                SharedPreferences.Editor ed1 = prefs.edit();
                                ed1.putInt("idEssencia1", listaModellist.get(0).getIdEssencia());
                                ed1.putString("sabor1", listaModellist.get(0).getSabor());
                                ed1.putString("marca1", listaModellist.get(0).getMarca());
                                ed1.putString("preco1", listaModellist.get(0).getPreco());
                                ed1.putInt("countEssencia", count);
                                ed1.apply();
                            }
                            if(count == 2) {
                                SharedPreferences.Editor ed2 = prefs.edit();
                                ed2.putInt("idEssencia1", listaModellist.get(0).getIdEssencia());
                                ed2.putString("sabor1", listaModellist.get(0).getSabor());
                                ed2.putString("marca1", listaModellist.get(0).getMarca());
                                ed2.putString("preco1", listaModellist.get(0).getPreco());
                                ed2.putInt("countEssencia", count);
                                ed2.apply();

                                SharedPreferences.Editor ed3 = prefs.edit();
                                ed3.putInt("idEssencia2", listaModellist.get(1).getIdEssencia());
                                ed3.putString("sabor2", listaModellist.get(1).getSabor());
                                ed3.putString("marca2", listaModellist.get(1).getMarca());
                                ed3.putString("preco2", listaModellist.get(1).getPreco());
                                ed3.apply();
                            }

                        }

                        //Intent intent = new Intent(mContext, ConfirmarPedido.class);
                        //intent.putExtra("Essencia", item);
                        //ModelItem modelItem  = new ModelItem(idEssencia, sabor, marca, preco);


                    } else {
                        Toast.makeText(mContext, "Checbox de OFF!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else {
            holder = (ViewHolder)view.getTag();
        }

        //setar o resultado nos textsviews
        holder.sabor.setText(modellist.get(position).getSabor());
        holder.descricao.setText(modellist.get(position).getDescricao());
        holder.preco.setText(modellist.get(position).getPreco());
        //set the result in imageview
        //holder.mIconSessao.setText(modellist.get(postition).getIcon());


        //Apresentar as telas de acordo com o click do usuário
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //code later
                //onclick das imagens
                
            }
        });


        return view;
    }

    //Filtro
    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        modellist.clear();
        if (charText.length()==0){
            modellist.addAll(arrayList);
        }
        else {
            for (ModelItem model : arrayList){
                if (model.getSabor().toLowerCase(Locale.getDefault())
                        .contains(charText)){
                    modellist.add(model);
                }
            }
        }
        notifyDataSetChanged();
    }

}

