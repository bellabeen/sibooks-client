package com.dev.kedaiit.sibooks.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.kedaiit.sibooks.R;
import com.dev.kedaiit.sibooks.model.DataKategori;
import com.dev.kedaiit.sibooks.model.DataPenerbit;
import com.dev.kedaiit.sibooks.ui.penerbit.InsertPenerbit;

import org.w3c.dom.Text;

import java.util.List;

public class AdapterDataPenerbit extends RecyclerView.Adapter<AdapterDataPenerbit.HolderData> {
    private List<DataPenerbit> mItems;
    private Context context;

    public AdapterDataPenerbit(Context context, List<DataPenerbit> items) {
        this.mItems = items;
        this.context = context;

    }

    @Override
    public HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_data_penerbit, parent, false);
        HolderData holderData = new HolderData(layout);
        return holderData;
    }

    @Override
    public void onBindViewHolder(HolderData holder, int position) {
        DataPenerbit md = mItems.get(position);
        holder.tv_kode_penerbit.setText(md.getKode_penerbit());
        holder.tv_penerbit.setText(md.getPenerbit());
        holder.md = md;

    }


    @Override
    public int getItemCount(){
        return mItems.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        TextView tv_penerbit, tv_kode_penerbit;
        DataPenerbit md;
        public HolderData(View view) {
            super(view);

            tv_kode_penerbit = (TextView) view.findViewById(R.id.tv_kode_penerbit);
            tv_penerbit = (TextView) view.findViewById(R.id.tv_penerbit);
            view.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    Intent update = new Intent(context, InsertPenerbit.class);
                    update.putExtra("update",1);
                    update.putExtra("kode_penerbit", md.getKode_penerbit());
                    update.putExtra("penerbit", md.getPenerbit());
                }
            });
        }
    }
}
