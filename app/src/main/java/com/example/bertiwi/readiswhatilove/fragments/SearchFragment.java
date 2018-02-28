package com.example.bertiwi.readiswhatilove.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.bertiwi.readiswhatilove.R;
import com.example.bertiwi.readiswhatilove.utilities.VolleySingleton;
import com.github.ybq.android.spinkit.SpinKitView;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    private static String VOLUMES_URL = "https://www.googleapis.com/books/v1/volumes?q=";
    private android.widget.SearchView mSearchView;
    private SpinKitView spinKitView;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragmentç
        View v = inflater.inflate(R.layout.fragment_search, container, false);

        mSearchView = v.findViewById(R.id.searchview_libros);
        spinKitView = v.findViewById(R.id.spin_kit_buscarLibros);



        return v;
    }


    public void parseVolumes(String response) throws JSONException {

    }
    public void getVolumes(){

        StringRequest stringRequest1 = new StringRequest(Request.Method.POST, VOLUMES_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            parseVolumes(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){

            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<>();
                params.put("q", "q");
                return params;
            }
        };

        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest1);

    }

}
