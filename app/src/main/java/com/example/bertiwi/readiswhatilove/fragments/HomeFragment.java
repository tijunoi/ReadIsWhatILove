package com.example.bertiwi.readiswhatilove.fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bertiwi.readiswhatilove.R;
import com.example.bertiwi.readiswhatilove.adapters.BookshelfAdapter;
import com.example.bertiwi.readiswhatilove.model.Book;
import com.example.bertiwi.readiswhatilove.model.Bookshelf;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    private RecyclerView mRecyclerView;
    private ArrayList<Bookshelf> mBookshelfArrayList;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mRecyclerView = view.findViewById(R.id.home_bookshelf_recyclerview);

        //MOCK CODE
        Book book = new Book("1","Titul","autor","Publi","date","dascripsio",4.0,"imagen");
        ArrayList<Book> librus = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            librus.add(book);
        }
        Bookshelf bookshelf1 = new Bookshelf("Aventura",librus);
        Bookshelf bookshelf2 = new Bookshelf("Misterio",librus);
        Bookshelf bookshelf3 = new Bookshelf("Fantasia",librus);
        Bookshelf bookshelf4 = new Bookshelf("Drama",librus);
        Bookshelf bookshelf5 = new Bookshelf("Policiaca",librus);
        Bookshelf bookshelf6 = new Bookshelf("Psicologia",librus);
        Bookshelf bookshelf7 = new Bookshelf("Ramen",librus);
        Bookshelf bookshelf8 = new Bookshelf("Viajes",librus);

        mBookshelfArrayList = new ArrayList<>();
        mBookshelfArrayList.add(bookshelf1);
        mBookshelfArrayList.add(bookshelf2);
        mBookshelfArrayList.add(bookshelf3);
        mBookshelfArrayList.add(bookshelf4);
        mBookshelfArrayList.add(bookshelf5);
        mBookshelfArrayList.add(bookshelf6);
        mBookshelfArrayList.add(bookshelf7);
        mBookshelfArrayList.add(bookshelf8);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        mRecyclerView.setAdapter(new BookshelfAdapter(mBookshelfArrayList));
        return view;
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
}
