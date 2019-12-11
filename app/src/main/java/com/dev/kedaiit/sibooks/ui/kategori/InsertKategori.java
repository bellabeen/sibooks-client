package com.dev.kedaiit.sibooks.ui.kategori;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.dev.kedaiit.sibooks.R;
import com.dev.kedaiit.sibooks.util.AppController;
import com.dev.kedaiit.sibooks.util.ServerAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class InsertKategori extends AppCompatActivity {
    EditText kode_kategori, kategori;
    Button btnBatal, btnSimpan;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_kategori);

        /*get data from intent*/
        Intent data = getIntent();
        final int update = data.getIntExtra("update",0);
        String intent_kodekategori = data.getStringExtra("kode_kategori");
        String intent_kategori = data.getStringExtra("kategori");
        /*end get data from intent*/

        kode_kategori = (EditText) findViewById(R.id.edt_kodekategori);
        kategori = (EditText) findViewById(R.id.edt_kategori);
        btnBatal = (Button) findViewById(R.id.btn_cancel);
        btnSimpan = (Button) findViewById(R.id.btn_simpan);
        pd = new ProgressDialog(InsertKategori.this);

        /*kondisi update / insert*/
        if(update == 1)
        {
            btnSimpan.setText("Update Data");
            kode_kategori.setText(intent_kodekategori);
            kode_kategori.setVisibility(View.VISIBLE);
            kategori.setText(intent_kategori);

        }


        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(update == 1)
                {
                    Update_data();
                }else {
                    simpanData();
                }
            }
        });

        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(InsertKategori.this,KategoriFragment.class);
                startActivity(main);
            }
        });
    }

    private void Update_data()
    {
        pd.setMessage("Update Data");
        pd.setCancelable(true);
        pd.show();

        StringRequest updateReq = new StringRequest(Request.Method.POST, ServerAPI.URL_UPDATE_KATEGORI,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.cancel();
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(InsertKategori.this, ""+   res.getString("message") , Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        startActivity( new Intent(InsertKategori.this,KategoriFragment.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Toast.makeText(InsertKategori.this, "Gagal Insert Data", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("kode_kategori",kode_kategori.getText().toString());
                map.put("kategori",kategori.getText().toString());

                return map;
            }
        };

        AppController.getInstance().addToRequestQueue(updateReq);
    }



    private void simpanData()
    {

        pd.setMessage("Menyimpan Data");
        pd.setCancelable(true);
        pd.show();

        StringRequest sendData = new StringRequest(Request.Method.POST, ServerAPI.URL_INSERT_KATEGORI,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.cancel();
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(InsertKategori.this, ""+   res.getString("message") , Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        startActivity( new Intent(InsertKategori.this,KategoriFragment.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Toast.makeText(InsertKategori.this, "Gagal Insert Data", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("kode_kategori",kode_kategori.getText().toString());
                map.put("kategori",kategori.getText().toString());
                return map;
            }
        };

        AppController.getInstance().addToRequestQueue(sendData);
    }

}
