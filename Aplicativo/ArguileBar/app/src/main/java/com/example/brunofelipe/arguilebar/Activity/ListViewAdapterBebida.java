package com.example.brunofelipe.arguilebar.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
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

public class ListViewAdapterBebida extends BaseAdapter {

    //variaveis
    Context mContext;
    LayoutInflater inflater;
    List<ModelItemBebida> modellist;
    ArrayList<ModelItemBebida> arrayList;
    Switch st;
    int idBebida;
    String marcaBebida;
    String precoBebida;
    ArrayList<ModelItemBebida> modellistSeleciona = new ArrayList<>();
    ModelItemBebida itemBebida;
    List<ModelItemBebida> listaModellist = new ArrayList<ModelItemBebida>();


    public ListViewAdapterBebida(Context context, List<ModelItemBebida> modellist) {
        mContext = context;
        this.modellist = modellist;
        inflater = LayoutInflater.from(mContext);
        this.arrayList = new ArrayList<ModelItemBebida>();
        this.arrayList.addAll(modellist);
    }


    public class ViewHolder{

        TextView marcaBebida;
        TextView precoBebida;
        ImageView mIconBebida;
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
            view = inflater.inflate(R.layout.row_list_view_bebida, null);

            //Localizar as views da row.xml
            holder.marcaBebida = view.findViewById(R.id.marcaIdBebida);
            holder.precoBebida = view.findViewById(R.id.valorIdBebida);
            holder.st = view.findViewById(R.id.swichButtonIdBebida);
            view.setTag(holder);

            st = view.findViewById(R.id.swichButtonIdBebida);
            st.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if (isChecked)
                {

                        //Toast.makeText(mContext, "Checbox de ON!", Toast.LENGTH_SHORT).show();
                        idBebida = modellist.get(position).idBebida;
                        marcaBebida = modellist.get(position).marcaBebida;
                        precoBebida = modellist.get(position).precoBebida;

                        itemBebida = new ModelItemBebida(idBebida, marcaBebida, precoBebida);
                        listaModellist.add(itemBebida);

                        //modellistSeleciona.add(item);

                        int count = listaModellist.size();
                        if(count > 0) {
                            SharedPreferences prefs;
                            prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
                            if (count == 1) {
                                SharedPreferences.Editor ed1 = prefs.edit();
                                ed1.putInt("idBebida1", listaModellist.get(0).getIdBebida());
                                ed1.putString("marca1", listaModellist.get(0).getMarcaBebida());
                                ed1.putString("preco1", listaModellist.get(0).getPrecoBebida());
                                ed1.putInt("count", count);
                                ed1.apply();
                            }
                            if (count == 2) {
                                SharedPreferences.Editor ed1 = prefs.edit();
                                ed1.putInt("idBebida1", listaModellist.get(0).getIdBebida());
                                ed1.putString("marca1", listaModellist.get(0).getMarcaBebida());
                                ed1.putString("preco1", listaModellist.get(0).getPrecoBebida());
                                ed1.putInt("count", count);
                                ed1.apply();

                                SharedPreferences.Editor ed2 = prefs.edit();
                                ed2.putInt("idBebida2", listaModellist.get(1).getIdBebida());
                                ed2.putString("marca2", listaModellist.get(1).getMarcaBebida());
                                ed2.putString("preco2", listaModellist.get(1).getPrecoBebida());
                                ed2.apply();
                            }if (count == 3) {
                                SharedPreferences.Editor ed1 = prefs.edit();
                                ed1.putInt("idBebida1", listaModellist.get(0).getIdBebida());
                                ed1.putString("marca1", listaModellist.get(0).getMarcaBebida());
                                ed1.putString("preco1", listaModellist.get(0).getPrecoBebida());
                                ed1.putInt("count", count);
                                ed1.apply();

                                SharedPreferences.Editor ed2 = prefs.edit();
                                ed2.putInt("idBebida2", listaModellist.get(1).getIdBebida());
                                ed2.putString("marca2", listaModellist.get(1).getMarcaBebida());
                                ed2.putString("preco2", listaModellist.get(1).getPrecoBebida());
                                ed2.apply();

                                SharedPreferences.Editor ed3 = prefs.edit();
                                ed3.putInt("idBebida3", listaModellist.get(2).getIdBebida());
                                ed3.putString("marca3", listaModellist.get(2).getMarcaBebida());
                                ed3.putString("preco3", listaModellist.get(2).getPrecoBebida());
                                ed3.apply();
                            }if (count == 4) {
                                SharedPreferences.Editor ed1 = prefs.edit();
                                ed1.putInt("idBebida1", listaModellist.get(0).getIdBebida());
                                ed1.putString("marca1", listaModellist.get(0).getMarcaBebida());
                                ed1.putString("preco1", listaModellist.get(0).getPrecoBebida());
                                ed1.putInt("count", count);
                                ed1.apply();

                                SharedPreferences.Editor ed2 = prefs.edit();
                                ed2.putInt("idBebida2", listaModellist.get(1).getIdBebida());
                                ed2.putString("marca2", listaModellist.get(1).getMarcaBebida());
                                ed2.putString("preco2", listaModellist.get(1).getPrecoBebida());
                                ed2.apply();

                                SharedPreferences.Editor ed3 = prefs.edit();
                                ed3.putInt("idBebida3", listaModellist.get(2).getIdBebida());
                                ed3.putString("marca3", listaModellist.get(2).getMarcaBebida());
                                ed3.putString("preco3", listaModellist.get(2).getPrecoBebida());
                                ed3.apply();

                                SharedPreferences.Editor ed4 = prefs.edit();
                                ed4.putInt("idBebida4", listaModellist.get(3).getIdBebida());
                                ed4.putString("marca4", listaModellist.get(3).getMarcaBebida());
                                ed4.putString("preco4", listaModellist.get(3).getPrecoBebida());
                                ed4.apply();
                            }if (count == 5) {
                                SharedPreferences.Editor ed1 = prefs.edit();
                                ed1.putInt("idBebida1", listaModellist.get(0).getIdBebida());
                                ed1.putString("marca1", listaModellist.get(0).getMarcaBebida());
                                ed1.putString("preco1", listaModellist.get(0).getPrecoBebida());
                                ed1.putInt("count", count);
                                ed1.apply();

                                SharedPreferences.Editor ed2 = prefs.edit();
                                ed2.putInt("idBebida2", listaModellist.get(1).getIdBebida());
                                ed2.putString("marca2", listaModellist.get(1).getMarcaBebida());
                                ed2.putString("preco2", listaModellist.get(1).getPrecoBebida());
                                ed2.apply();

                                SharedPreferences.Editor ed3 = prefs.edit();
                                ed3.putInt("idBebida3", listaModellist.get(2).getIdBebida());
                                ed3.putString("marca3", listaModellist.get(2).getMarcaBebida());
                                ed3.putString("preco3", listaModellist.get(2).getPrecoBebida());
                                ed3.apply();

                                SharedPreferences.Editor ed4 = prefs.edit();
                                ed4.putInt("idBebida4", listaModellist.get(3).getIdBebida());
                                ed4.putString("marca4", listaModellist.get(3).getMarcaBebida());
                                ed4.putString("preco4", listaModellist.get(3).getPrecoBebida());
                                ed4.apply();

                                SharedPreferences.Editor ed5 = prefs.edit();
                                ed5.putInt("idBebida5", listaModellist.get(4).getIdBebida());
                                ed5.putString("marca5", listaModellist.get(4).getMarcaBebida());
                                ed5.putString("preco5", listaModellist.get(4).getPrecoBebida());
                                ed5.apply();
                            }
                     }
                 }else {
                        Toast.makeText(mContext, "Checbox de OFF!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else {
            holder = (ViewHolder)view.getTag();
        }

        //setar o resultado nos textsviews
        holder.marcaBebida.setText(modellist.get(position).getMarcaBebida());
        holder.precoBebida.setText(modellist.get(position).getPrecoBebida());
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
            for (ModelItemBebida model : arrayList){
                if (model.getMarcaBebida().toLowerCase(Locale.getDefault())
                        .contains(charText)){
                    modellist.add(model);
                }
            }
        }
        notifyDataSetChanged();
    }

}

