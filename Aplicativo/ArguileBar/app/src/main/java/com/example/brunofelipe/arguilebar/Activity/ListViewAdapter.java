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

                        //modellistSeleciona.add(item);

                        SharedPreferences prefs;
                        prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
                        SharedPreferences.Editor ed = prefs.edit();
                        ed.putInt("idEssencia", idEssencia);
                        ed.putString("sabor", sabor);
                        ed.putString("marca", marca);
                        ed.putString("preco", preco);
                        ed.apply();

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
                //if (modellist.get(getItemId()){
                  //    start NewActivity with title for actionbar and text for textview
//                    Intent intent = new Intent(mContext, MainActivity.class);
//                    intent.putExtra("actionBarTitle", "Battery");
//                    intent.putExtra("contentTv", "This is Battery detail...");
//                    mContext.startActivity(intent);
//                }
//                if (modellist.get(postition).getTitle().equals("Cpu")){
//                    //start NewActivity with title for actionbar and text for textview
//                    Intent intent = new Intent(mContext, MainActivity.class);
//                    intent.putExtra("actionBarTitle", "Cpu");
//                    intent.putExtra("contentTv", "This is Cpu detail...");
//                    mContext.startActivity(intent);
//                }
//                if (modellist.get(postition).getTitle().equals("Display")){
//                    //start NewActivity with title for actionbar and text for textview
//                    Intent intent = new Intent(mContext, MainActivity.class);
//                    intent.putExtra("actionBarTitle", "Display");
//                    intent.putExtra("contentTv", "This is Display detail...");
//                    mContext.startActivity(intent);
//                }
//                if (modellist.get(postition).getTitle().equals("Memory")){
//                    //start NewActivity with title for actionbar and text for textview
//                    Intent intent = new Intent(mContext, MainActivity.class);
//                    intent.putExtra("actionBarTitle", "Memory");
//                    intent.putExtra("contentTv", "This is Memory detail...");
//                    mContext.startActivity(intent);
//                }
//                if (modellist.get(postition).getTitle().equals("Sensor")){
//                    //start NewActivity with title for actionbar and text for textview
//                    Intent intent = new Intent(mContext, MainActivity.class);
//                    intent.putExtra("actionBarTitle", "Sensor");
//                    intent.putExtra("contentTv", "This is Sensor detail...");
//                    mContext.startActivity(intent);
//                }
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

