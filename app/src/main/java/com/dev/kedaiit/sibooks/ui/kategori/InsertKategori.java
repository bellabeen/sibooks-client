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
    EditText id_kategori,kategori;
    Button btnbatal, btnsimpan;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_kategori);

        //variable linking
        //id_kategori = findViewById(R.id.edt_kategori_kategori);


        /*get data from intent*/
        Intent data = getIntent();
        final int update = data.getIntExtra("update",0);
        final String intent_idkategori = data.getStringExtra("id_kategori");
        String intent_kategori = data.getStringExtra("kategori");
        /*end get data from intent*/

//        id_kategori = (EditText) findViewById(R.id.edt_idkategori);
        kategori = (EditText) findViewById(R.id.edt_kategori_kategori);
        btnbatal = (Button) findViewById(R.id.btn_cancel_kategori);
        btnsimpan = (Button) findViewById(R.id.btn_simpan_kategori);
        pd = new ProgressDialog(InsertKategori.this);

        /*kondisi update / insert*/
        if(update == 1)
        {
            btnsimpan.setText("Update Data");
            //id_kategori.setText(intent_idkategori);
            kategori.setText(intent_kategori);

        }


        btnsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(update == 1)
                {
                    Update_data(intent_idkategori);
                }else {
                    simpanData();
                }
            }
        });

        btnbatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(InsertKategori.this, KategoriFragment.class);
                startActivity(main);
            }
        });
    }

    private void Update_data(final String id)
    {
        pd.setMessage("Update Data");
        pd.setCancelable(false);
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

                        //startActivity( new Intent(InsertKategori.this,KategoriFragment.class));
                        finish();
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
                map.put("id_kategori", id);
                map.put("kategori", kategori.getText().toString());

                return map;
            }
        };

        AppController.getInstance().addToRequestQueue(updateReq, "update");
    }

    private void simpanData()
    {

        pd.setMessage("Menyimpan Data");
        pd.setCancelable(false);
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

                        finish();
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

                map.put("id_kategori","51"); //replace the 51 with your new id of data, Think of logic to generate id
                map.put("kategori",kategori.getText().toString());

                return map;
            }
        };

        AppController.getInstance().addToRequestQueue(sendData, "Sending");
    }
}
