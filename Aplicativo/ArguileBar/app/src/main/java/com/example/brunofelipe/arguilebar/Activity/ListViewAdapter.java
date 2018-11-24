package com.example.brunofelipe.arguilebar.Activity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.brunofelipe.arguilebar.R;

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


    public ListViewAdapter(Context context, List<ModelItem> modellist) {
        mContext = context;
        this.modellist = modellist;
        inflater = LayoutInflater.from(mContext);
        this.arrayList = new ArrayList<ModelItem>();
        this.arrayList.addAll(modellist);
    }


    public class ViewHolder{
        TextView mTitleSessao, mDescSessao;
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
    public View getView(final int postition, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view==null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.row_list_view, null);

            //Localizar as views da row.xml
            holder.mTitleSessao = view.findViewById(R.id.mainTitle);
            holder.mDescSessao = view.findViewById(R.id.mainDesc);
            holder.mIconSessao = view.findViewById(R.id.mainIcon);
            holder.st = view.findViewById(R.id.swichButtonId);
            view.setTag(holder);


            st = view.findViewById(R.id.swichButtonId);

            st.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(st.isChecked()) {
                        Toast.makeText(mContext, "Checbox de  marcado!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(mContext, "Checbox de desmarcado!", Toast.LENGTH_SHORT).show();
                    }
                }
            });



        }
        else {
            holder = (ViewHolder)view.getTag();
        }
        //setar o resultado nos textsviews
        holder.mTitleSessao.setText(modellist.get(postition).getTitle());
        holder.mDescSessao.setText(modellist.get(postition).getDesc());

        //set the result in imageview
        //holder.mIconSessao.setText(modellist.get(postition).getIcon());




        //Apresentar as telas de acordo com o click do usuário
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //code later
                //onclick das imagens
//                if (modellist.get(postition).getTitle().equals("Battery")){
//                    //start NewActivity with title for actionbar and text for textview
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
                if (model.getTitle().toLowerCase(Locale.getDefault())
                        .contains(charText)){
                    modellist.add(model);
                }
            }
        }
        notifyDataSetChanged();
    }

}

