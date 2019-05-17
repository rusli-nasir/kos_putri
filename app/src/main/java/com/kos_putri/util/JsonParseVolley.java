package com.kos_putri.util;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonParseVolley {

    private RequestQueue queue;
    private static JSONObject jObj = null;

    public JsonParseVolley(Context ctx){
        super();
        if(this.queue == null){
            this.queue = Volley.newRequestQueue(ctx);
        }
    }

    private void setQueue(RequestQueue queue) {
       this.queue  = queue;
    }

    public JSONObject getJsonURL(String str_url) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, str_url, null,
          new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    jObj = new JSONObject(String.valueOf(response));
//                    Log.d("test","Error dilewati");
//                    Log.d("jObj", String.valueOf(response));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("myTag", "Error:"+error.toString());
                error.printStackTrace();
            }
        });
        jsonObjectRequest.setTag("VolleyBlockingRequestActivity");

        queue.add(jsonObjectRequest);

        return jObj;
    }

    public JSONObject getJsonURLbyParams(String str_url, JSONObject jsonParams){

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, str_url, jsonParams,
           new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                jObj = response;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        queue.add(jsonObjectRequest);
        return jObj;
    }

    public void stopRequest(String tag){
        queue.cancelAll(tag);
    }

}
