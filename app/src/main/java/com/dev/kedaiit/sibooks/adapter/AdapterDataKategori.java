package com.dev.kedaiit.sibooks.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.dev.kedaiit.sibooks.R;
import com.dev.kedaiit.sibooks.model.DataKategori;
import com.dev.kedaiit.sibooks.ui.kategori.InsertKategori;

import java.util.List;

public class AdapterDataKategori extends RecyclerView.Adapter<AdapterDataKategori.HolderData> {
    private List<DataKategori> mItems;
    private Context context;

    public AdapterDataKategori(Context context, List<DataKategori> items){
        this.mItems = items;
        this.context = context;
    }

    @Override
    public HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_data_kategori,parent,false);
        HolderData holderData = new HolderData(layout);
        return holderData;
    }

    @Override
    public void onBindViewHolder(HolderData holder, int position){
        DataKategori md = mItems.get(position);
        holder.tv_kode_kategori.setText(md.getKode_kategori());
        holder.tv_kategori.setText(md.getKategori());
        holder.md = md;
    }

    @Override
    public int getItemCount(){
        return mItems.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        TextView tv_kategori, tv_kode_kategori;
        DataKategori md;
        public HolderData (View view){
            super(view);
            tv_kode_kategori = (TextView) view.findViewById(R.id.tv_kode_kategori);
            tv_kategori = (TextView) view.findViewById(R.id.tv_kategori);
            view.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    Intent update = new Intent(context, InsertKategori.class);
                    update.putExtra("update", 1);
                    update.putExtra("kode_kategori", md.getKode_kategori());
                    update.putExtra("kategori",md.getKategori());
                }
            });
        }
    }
}