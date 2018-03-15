package com.example.bertiwi.readiswhatilove.fragments;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.service.voice.VoiceInteractionSessionService;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.bertiwi.readiswhatilove.R;
import com.example.bertiwi.readiswhatilove.activities.BookProfileActivity;
import com.example.bertiwi.readiswhatilove.adapters.BookRecycler;
import com.example.bertiwi.readiswhatilove.model.Book;
import com.example.bertiwi.readiswhatilove.utilities.SharedPrefManager;
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

import static com.example.bertiwi.readiswhatilove.fragments.SearchFragment.SELECTED_BOOK;

/**
 * A simple {@link Fragment} subclass.
 */
public class StarredFragment extends Fragment implements SearchView.OnQueryTextListener {

    private String BOOKSHELF_URL = "https://www.googleapis.com/books/v1/mylibrary/bookshelves/0/volumes";
    private android.widget.SearchView mSearchView;
    private SpinKitView spinKitView;
    RecyclerView recyclerView;
    private ArrayList<Book> bookArrayList = new ArrayList<>();

    private BookRecycler adapter;

    public StarredFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_starred, container, false);

        mSearchView = v.findViewById(R.id.searchview_bookshelf);
        spinKitView = v.findViewById(R.id.spin_kit_cargando);
        recyclerView = v.findViewById(R.id.recV_BookShelf);

        spinKitView.setVisibility(View.VISIBLE);
        mSearchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSearchView.setIconified(false);
            }
        });
        mSearchView.setQueryHint("Buscar favorito");
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

        new GetBookShelf().execute();

        return v;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adapter.filter(s);
        return false;
    }

    private class GetBookShelf extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(BOOKSHELF_URL)
                    .build();
            Response response = null;
            try {
                response = client.newCall(request).execute();
                String responseStr = response.body().string();
                try {
                    parseJSONBookshelfs(responseStr);
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


    public void parseJSONBookshelfs(String response) throws JSONException {
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

}
