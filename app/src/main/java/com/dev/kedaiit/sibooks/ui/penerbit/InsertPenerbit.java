package com.dev.kedaiit.sibooks.ui.penerbit;

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
import com.dev.kedaiit.sibooks.ui.kategori.KategoriFragment;
import com.dev.kedaiit.sibooks.util.AppController;
import com.dev.kedaiit.sibooks.util.ServerAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class InsertPenerbit extends AppCompatActivity {
    EditText id_penerbit, penerbit;
    Button btnbatal, btnsimpan;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_penerbit);

        /*get data from intent*/
        Intent data = getIntent();
        final int update = data.getIntExtra("update",0);
        final String intent_idpenerbit = data.getStringExtra("id_penerbit");
        String intent_penerbit = data.getStringExtra("penerbit");
        /*end get data from intent*/

        penerbit = (EditText) findViewById(R.id.edt_penerbit);
        btnbatal = (Button) findViewById(R.id.btn_cancel_penerbit);
        btnsimpan = (Button) findViewById(R.id.btn_simpan_penerbit);
        pd = new ProgressDialog(InsertPenerbit.this);

        /*kondisi update / insert*/
        if(update == 1)
        {
            btnsimpan.setText("Update Data");
//            id_penerbit.setText(intent_idpenerbit);
//            id_penerbit.setVisibility(View.GONE);
            penerbit.setText(intent_penerbit);

        }


        btnsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(update == 1)
                {
                    Update_data(intent_idpenerbit);
                }else {
                    simpanData(intent_idpenerbit);
                }
            }
        });

        btnbatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(InsertPenerbit.this, PenerbitFragment.class);
                startActivity(main);
            }
        });
    }

    private void Update_data(final String id)
    {
        pd.setMessage("Update Data");
        pd.setCancelable(false);
        pd.show();

        StringRequest updateReq = new StringRequest(Request.Method.POST, ServerAPI.URL_UPDATE_PENERBIT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.cancel();
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(InsertPenerbit.this, ""+   res.getString("message") , Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        startActivity( new Intent(InsertPenerbit.this,PenerbitFragment.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Toast.makeText(InsertPenerbit.this, "Gagal Insert Data", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("id_penerbit",id);
                map.put("penerbit",penerbit.getText().toString());

                return map;
            }
        };

        AppController.getInstance().addToRequestQueue(updateReq, "update");
    }

    private void simpanData(final String id)
    {

        pd.setMessage("Menyimpan Data");
        pd.setCancelable(false);
        pd.show();

        StringRequest sendData = new StringRequest(Request.Method.POST, ServerAPI.URL_INSERT_PENERBIT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.cancel();
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(InsertPenerbit.this, ""+   res.getString("message") , Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        startActivity( new Intent(InsertPenerbit.this,PenerbitFragment.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Toast.makeText(InsertPenerbit.this, "Gagal Insert Data", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("id_penerbit",id);
                map.put("penerbit",penerbit.getText().toString());

                return map;
            }
        };

        AppController.getInstance().addToRequestQueue(sendData, "send");
    }
}
