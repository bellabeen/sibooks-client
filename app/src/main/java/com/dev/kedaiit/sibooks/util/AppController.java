package com.dev.kedaiit.sibooks.util;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class AppController extends Application {
    private static final String TAG = AppController.class.getSimpleName();
    private static AppController instance;
    RequestQueue mRequestQueue;

    @Override
    public void onCreate(){
        super.onCreate();
        instance = this;
    }

    public static synchronized AppController getInstance() {
        return instance;
    }

    private RequestQueue getmRequestQueue() {
        if (mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue((getApplicationContext()));
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue (Request<T> req) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(req);
        }
    }
}
