package com.example.bertiwi.readiswhatilove.fragments;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.bertiwi.readiswhatilove.R;
import com.example.bertiwi.readiswhatilove.activities.BookProfileActivity;
import com.example.bertiwi.readiswhatilove.adapters.BookRecycler;
import com.example.bertiwi.readiswhatilove.model.Book;
import com.example.bertiwi.readiswhatilove.utilities.SimpleDividerItemDecoration;
import com.github.ybq.android.spinkit.SpinKitView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements SearchView.OnQueryTextListener {

    private static String VOLUMES_URL = "https://www.googleapis.com/books/v1/volumes?q=";
    private android.widget.SearchView mSearchView;
    private SpinKitView spinKitView;
    RecyclerView recyclerView;
    private ArrayList<Book> bookArrayList = new ArrayList<>();


    public static final String SELECTED_BOOK= "BOOK SELECCIONADO";

    private BookRecycler adapter;

    private String finalQuery;


    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragmentç
        View v = inflater.inflate(R.layout.fragment_search, container, false);

        mSearchView = v.findViewById(R.id.searchview_libros);
        spinKitView = v.findViewById(R.id.spin_kit_buscarLibros);
        recyclerView = v.findViewById(R.id.recV_Libros);

        mSearchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSearchView.setIconified(false);
            }
        });
        mSearchView.setQueryHint("Buscar libro");
        mSearchView.setOnQueryTextListener(this);

        adapter = new BookRecycler(bookArrayList, getContext(), new BookRecycler.OnItemClickListener() {
            @Override
            public void onItemClick(Book book) {
                Intent intent = new Intent(getActivity(), BookProfileActivity.class);
                intent.putExtra(SELECTED_BOOK, book);
                startActivity(intent);

            }
        });

        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getContext()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        return v;
    }

    private class getVolumes extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(VOLUMES_URL + finalQuery + "&maxResults=10&printType=books")
                    .build();
            Response response = null;
            try {
                response = client.newCall(request).execute();
                String responseStr = response.body().string();
                try {
                    parseJSONVolumes(responseStr);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (isAdded() && getActivity() != null) {
                adapter.notifyDataSetChanged();
                spinKitView.setVisibility(View.GONE);
            }
        }
    }
    /*public void getdfsVolumes(){

        StringRequest stringRequest1 = new StringRequest(Request.Method.GET, VOLUMES_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            parseJSONVolumes(response);
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
                params.put("q", finalQuery);
                params.put("maxResults", "10");
                params.put("printType", "books");
                return params;
            }
        };

        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest1);

    }*/

    public void parseJSONVolumes(String response) throws JSONException {
        JSONObject jsonObject = new JSONObject(response);

        String kind = jsonObject.getString("kind");
        int totalItems = jsonObject.getInt("totalItems");
        JSONArray jsonArray = new JSONArray(jsonObject.getString("items"));


        for (int i = 0; i < jsonArray.length(); i++) {
            Book book = new Book();
            JSONObject datosBook = jsonArray.getJSONObject(i);
            String id = datosBook.getString("id");
            JSONObject volumeInfo = new JSONObject(datosBook.getString("volumeInfo"));
            String title = volumeInfo.getString("title");
            JSONArray authors = new JSONArray(volumeInfo.optString("authors"));
            String author = String.valueOf(authors.get(0));
            String published = volumeInfo.optString("publisher");
            String publishedDate = volumeInfo.optString("publishedDate");
            String description = volumeInfo.optString("description");
            Double averageRating = volumeInfo.optDouble("averageRating");
            JSONObject imageLinks = new JSONObject(volumeInfo.optString("imageLinks"));
            String image = imageLinks.optString("thumbnail");
            //String image = String.valueOf(imageLinks.get(0));
            /*JSONArray volumeInfo = new JSONArray(datosBook.getString("volumeInfo"));
            for (int j = 0; j < volumeInfo.length(); j++) {
                JSONObject volumeInfoObject = volumeInfo.getJSONObject(j);
                String title = volumeInfoObject.getString("title");
                JSONArray authors = new JSONArray(volumeInfoObject.getString("authors"));
                String author = String.valueOf(authors.get(0));
                String published = volumeInfoObject.getString("publisher");
                String publishedDate = volumeInfoObject.getString("publishedDate");
                String description = volumeInfoObject.getString("description");
                Double averageRating = volumeInfoObject.getDouble("averageRating");
                JSONArray imageLinks = new JSONArray(volumeInfoObject.getString("imageLinks"));
                String image = String.valueOf(imageLinks.get(0));

                book.setTitle(title);
                book.setAuthor(author);
                book.setPublisher(published);
                book.setPublishedDate(publishedDate);
                book.setDescription(description);
                book.setAverageRating(averageRating);
                book.setImage(image);

            }*/

            book.setId(id);
            book.setTitle(title);
            book.setAuthor(author);
            book.setPublisher(published);
            book.setPublishedDate(publishedDate);
            book.setDescription(description);
            book.setAverageRating(averageRating);
            book.setImage(image);

            bookArrayList.add(book);

        }

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        finalQuery = query;
        if (!finalQuery.isEmpty()) {
            bookArrayList.clear();
            spinKitView.setVisibility(View.VISIBLE);
            new getVolumes().execute();
        } else {
            Toast.makeText(getContext(), "ESTO NO VA COÑO", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
