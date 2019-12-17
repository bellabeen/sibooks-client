package com.dev.kedaiit.sibooks.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.dev.kedaiit.sibooks.R;
import com.dev.kedaiit.sibooks.model.DataHome;
import com.dev.kedaiit.sibooks.model.DataKategori;
import com.dev.kedaiit.sibooks.ui.home.InsertHome;
import com.dev.kedaiit.sibooks.ui.kategori.InsertKategori;

import java.util.List;

public class AdapterDataHome extends RecyclerView.Adapter<AdapterDataHome.HolderData> {
    private List<DataHome> mItems;
    private Context context;

    public AdapterDataHome(Context context, List<DataHome> items){
        this.mItems = items;
        this.context = context;
    }

    @Override
    public HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_data_home,parent,false);
        HolderData holderData = new HolderData(layout);
        return holderData;
    }

    @Override
    public void onBindViewHolder(HolderData holder, int position){
        DataHome md = mItems.get(position);
        holder.tv_kode_buku.setText(md.getKode_buku());
        holder.tv_judul.setText(md.getJudul());
        holder.md = md;
    }

    @Override
    public int getItemCount(){
        return mItems.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        TextView tv_judul, tv_kode_buku;
        DataHome md;
        public HolderData (View view){
            super(view);
            tv_kode_buku = (TextView) view.findViewById(R.id.tv_kode_buku);
            tv_judul = (TextView) view.findViewById(R.id.tv_judul);
            view.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    Intent update = new Intent(context, InsertHome.class);
                    update.putExtra("update", 1);
                    update.putExtra("kode_buku", md.getKode_buku());
                    update.putExtra("judul",md.getJudul());
                }
            });
        }
    }
}