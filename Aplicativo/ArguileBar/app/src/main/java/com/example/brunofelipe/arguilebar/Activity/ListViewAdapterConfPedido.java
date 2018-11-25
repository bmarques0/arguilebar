package com.example.brunofelipe.arguilebar.Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.brunofelipe.arguilebar.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListViewAdapterConfPedido extends BaseAdapter {

    //variaveis
    Context mContext;
    LayoutInflater inflater;
    List<ModelItem> modelist;
    ArrayList<ModelItem> arrayList;
//    Switch st;
    int idEssencia;
    String marca;
    String sabor;
    String preco;

    public ListViewAdapterConfPedido(Context context, List<ModelItem> modellist) {
        mContext = context;
        this.modelist = modellist;
        inflater = LayoutInflater.from(mContext);
        this.arrayList = new ArrayList<ModelItem>();
        this.arrayList.addAll(modellist);
    }


    public class ViewHolder{
        TextView marca;
        TextView sabor;
        TextView preco;
        ImageView mIconSessao;
       // Switch st;
    }

    @Override
    public int getCount() {
        return modelist.size();
    }

    @Override
    public Object getItem(int i) {
        return modelist.get(i);
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
            view = inflater.inflate(R.layout.row_list_view_confirmar, null);

            //Localizar as views da row.xml
            holder.marca = view.findViewById(R.id.marcaId);
            holder.sabor = view.findViewById(R.id.saborId);
            holder.preco = view.findViewById(R.id.valorId);
            //holder.st = view.findViewById(R.id.swichButtonId);
            view.setTag(holder);

            idEssencia = modelist.get(position).idEssencia;
            marca = modelist.get(position).marca;
            sabor = modelist.get(position).sabor;
            preco = modelist.get(position).preco;

            ModelItem modelItem  = new ModelItem(idEssencia, sabor, marca, preco);

        }
        else {
            holder = (ViewHolder)view.getTag();
        }
        holder.marca.setText(marca);
        holder.sabor.setText(sabor);
        holder.preco.setText(preco);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Apresentar as telas de acordo com o click do usuário

            }
        });
        return view;
    }
}

