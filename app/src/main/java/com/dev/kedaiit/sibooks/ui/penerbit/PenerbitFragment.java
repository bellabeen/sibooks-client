package com.dev.kedaiit.sibooks.ui.penerbit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.dev.kedaiit.sibooks.R;
import com.dev.kedaiit.sibooks.adapter.AdapterDataKategori;
import com.dev.kedaiit.sibooks.adapter.AdapterDataPenerbit;
import com.dev.kedaiit.sibooks.model.DataKategori;
import com.dev.kedaiit.sibooks.model.DataPenerbit;
import com.dev.kedaiit.sibooks.ui.kategori.DeleteKategori;
import com.dev.kedaiit.sibooks.ui.kategori.InsertKategori;
import com.dev.kedaiit.sibooks.util.ServerAPI;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PenerbitFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<DataPenerbit> list;
    private RecyclerView.Adapter adapter;
    private FloatingActionButton fab, delPnb;

    public PenerbitFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_penerbit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerViewPenerbit);
        fab =  view.findViewById(R.id.fab);
        delPnb =  view.findViewById(R.id.delPnb);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), InsertPenerbit.class);
                startActivity(intent);
            }
        });

        delPnb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DeletePenerbit.class);
                startActivity(intent);
            }
        });

        list = new ArrayList<>();
        adapter = new AdapterDataPenerbit(getContext(), list);


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        getData();
    }

    private void getData() {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        JsonObjectRequest my_request = new JsonObjectRequest(Request.Method.GET, ServerAPI.URL_DATA_PENERBIT, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    JSONArray jsonArray = response.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject Jobj = jsonArray.getJSONObject(i);

                        DataPenerbit obj = new DataPenerbit();
                        obj.setKode_penerbit(Jobj.getString("kode_penerbit"));
                        obj.setPenerbit(Jobj.getString("penerbit"));

                        list.add(obj);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    progressDialog.dismiss();
                }
                adapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", "Error: " + error.getMessage());
                progressDialog.dismiss();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));
        requestQueue.add(my_request);
    }
}