package com.example.bertiwi.readiswhatilove.utilities;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Bertiwi on 28/02/2018.
 */

public class VolleySingleton {

    // Atributos
    private static VolleySingleton singleton;
    private RequestQueue requestQueue;
    private static Context mCtx;


    private VolleySingleton(Context context) {
        mCtx = context;
        requestQueue = getRequestQueue();

    }

    public static synchronized VolleySingleton getInstance(Context context) {
        if (singleton == null) {
            singleton = new VolleySingleton(context);
        }
        return singleton;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null)
            requestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        return requestQueue;
    }

    public void addToRequestQueue(Request req) {

        req.setRetryPolicy(new DefaultRetryPolicy(
                20*1000,
                -1,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        getRequestQueue().add(req);
    }

}
