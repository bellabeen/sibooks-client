package com.dev.kedaiit.sibooks.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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
        final DataKategori md = mItems.get(position);
        holder.tv_kategori.setText(md.getKategori());
        holder.md = md;

        holder.edit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent update = new Intent(context, InsertKategori.class);
                update.putExtra("update", 1);
                update.putExtra("id_kategori", md.getId_kategori());
                update.putExtra("kategori",md.getKategori());
                context.startActivity(update);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG", "deleted");
                // make your API call for deleting the record
            }
        });
    }

    @Override
    public int getItemCount(){
        return mItems.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        TextView tv_kategori;
        DataKategori md;

        LinearLayout edit, delete;


        public HolderData (View view){
            super(view);

            edit = view.findViewById(R.id.edit_k);
            delete = view.findViewById(R.id.delete_k);

            tv_kategori = (TextView) view.findViewById(R.id.tv_kategori);

        }
    }
}