package com.example.brunofelipe.arguilebar.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.brunofelipe.arguilebar.R;
import com.google.gson.internal.bind.ObjectTypeAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListViewAdapterConfPedido extends BaseAdapter {

    //variaveis

    Context mContext;
    LayoutInflater inflater;
    List<ModelItemBebEssencia> modelist;
    ArrayList<ModelItemBebEssencia> arrayList;
    List<ModelItemBebEssencia> modelistBebida;
    ArrayList<ModelItemBebEssencia> arrayListBebida;


    public ListViewAdapterConfPedido(Context context, List<ModelItemBebEssencia> modellist) {
        mContext = context;
        this.modelist = modellist;
        inflater = LayoutInflater.from(mContext);
        this.arrayList = new ArrayList<ModelItemBebEssencia>();
        this.arrayList.addAll(modellist);

    }

        public class ViewHolder {
            TextView marcaField;
            TextView saborField;
            TextView precoField;
            ImageView mIconSessao;
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

//    @Override
//    public int getViewTypeCount() {
//        return 2; // The number of distinct view types the getView() will return.
//    }

//    @Override
//    public int getItemViewType(int position) {
//
//        if (getItem(position) instanceof ModelItem){
//            return 0;
//        }else{
//            return 1;
//        }
//    }
//
//    @Override
//    public View getView(final int position, View view, ViewGroup parent) {
//        //ViewHolder holder;
//
//        //if (view==null) {
//
//            item = getItem(position);
//            //holder = new ViewHolder();
//
//
//            if (item instanceof ModelItem) {
//
//                //modelist.add((ModelItem) listaDupla.get(position));
//
//                view = inflater.inflate(R.layout.row_list_view, null);
//
//                //Localizar as views da row.xml
//                marcaField = view.findViewById(R.id.saborId);
//                //holder.sabor = view.findViewById(R.id.saborId);
//                precoField = view.findViewById(R.id.valorId);
//                //holder.st = view.findViewById(R.id.swichButtonId);
//                //view.setTag(holder);
//                marca = ((ModelItem) item).marca;
//                preco = ((ModelItem) item).preco;
//                //idEssencia = modelist.get(position).idEssencia;
//                 //ModelItem m = listaDupla.get(position);
//                //preco = item.getPreco();
//                ModelItem essencia = new ModelItem(marca, preco);
//
//                marcaField.setText(essencia.getMarca());
//                precoField.setText(essencia.getPreco());
//
//                //ModelItem modelItem = new ModelItem(idEssencia, sabor, marca, preco);
//            } else if (item instanceof ModelItemBebida) {
//
//                view = inflater.inflate(R.layout.row_list_view_bebida, null);
//
//                //Localizar as views da row.xml
//                marcaField = view.findViewById(R.id.marcaIdBebida);
//                precoField = view.findViewById(R.id.valorIdBebida);
//                //holder.st = view.findViewById(R.id.swichButtonIdBebida);
//                marca = ((ModelItemBebida) item).marcaBebida;
//                preco = ((ModelItemBebida) item).precoBebida;
//
//                ModelItemBebida bebida = new ModelItemBebida(marca, preco);
//
//                marcaField.setText(bebida.getMarcaBebida());
//                precoField.setText(bebida.getPrecoBebida());
//
//            }




//        } else {
//            holder = (ViewHolder)view.getTag();
//        }
//
//        holder.sabor.setText(modellist.get(position).getSabor());


            // listviewadapter com modellist

        @Override
        public View getView(final int position, View view, ViewGroup parent) {
            ViewHolder holder;
            if (view==null){
                holder = new ViewHolder();
                view = inflater.inflate(R.layout.row_list_view_confirmar, null);

                //Localizar as views da row.xml
                holder.marcaField = view.findViewById(R.id.marcaId);
                holder.saborField = view.findViewById(R.id.saborId);
                holder.precoField = view.findViewById(R.id.valorId);
                view.setTag(holder);


            }
            else {
                holder = (ListViewAdapterConfPedido.ViewHolder)view.getTag();
            }

            //setar o resultado nos textsviews
            holder.marcaField.setText(arrayList.get(position).getMarca());
            holder.precoField.setText(arrayList.get(position).getPreco());
            //set the result in imageview
            //holder.mIconSessao.setText(modellist.get(postition).getIcon());
//
//
//            view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //Apresentar as telas de acordo com o click do usuário
//
//            }
//        });
        return view;
    }
}

