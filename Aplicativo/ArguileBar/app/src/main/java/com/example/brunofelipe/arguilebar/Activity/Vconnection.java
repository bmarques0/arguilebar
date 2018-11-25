package com.example.brunofelipe.arguilebar.Activity;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

public class Vconnection {

    private static Vconnection nInstance;
    private RequestQueue rq;
    private Context ctx;


    private Vconnection(Context context)
    {
        ctx = context;
        rq = getRequestQue();

    }

    public RequestQueue getRequestQue()
    {
        if(rq == null){
            rq = Volley.newRequestQueue(ctx.getApplicationContext());

        }
        return rq;
    }

    public static synchronized  Vconnection getInstance(Context context)
    {
        if(nInstance == null ){
            nInstance = new Vconnection(context);

        }
        return nInstance;
    }

    public <T> void addRequestQue (Request<T> request){
        rq.add(request);
    }

}
