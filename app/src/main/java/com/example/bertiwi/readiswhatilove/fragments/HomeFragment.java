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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.bertiwi.readiswhatilove.fragments.SearchFragment.VOLUMES_URL;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    private RecyclerView mRecyclerView;
    private ArrayList<Bookshelf> mBookshelfArrayList;
    private BookshelfAdapter mBookshelfAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mRecyclerView = view.findViewById(R.id.home_bookshelf_recyclerview);

        ArrayList<Book> librus = new ArrayList<>();

        Bookshelf bookshelf1 = new Bookshelf("Adventure",new ArrayList<Book>());
        Bookshelf bookshelf2 = new Bookshelf("Jungle",new ArrayList<Book>());
        Bookshelf bookshelf3 = new Bookshelf("Harry Potter",new ArrayList<Book>());
        Bookshelf bookshelf4 = new Bookshelf("Mythology",new ArrayList<Book>());
        Bookshelf bookshelf5 = new Bookshelf("Psicology",new ArrayList<Book>());
        Bookshelf bookshelf6 = new Bookshelf("Food",new ArrayList<Book>());
        Bookshelf bookshelf7 = new Bookshelf("Travel",new ArrayList<Book>());

        mBookshelfArrayList = new ArrayList<>();
        mBookshelfArrayList.add(bookshelf1);
        mBookshelfArrayList.add(bookshelf2);
        mBookshelfArrayList.add(bookshelf3);
        mBookshelfArrayList.add(bookshelf4);
        mBookshelfArrayList.add(bookshelf5);
        mBookshelfArrayList.add(bookshelf6);
        mBookshelfArrayList.add(bookshelf7);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        mBookshelfAdapter = new BookshelfAdapter(mBookshelfArrayList);
        mRecyclerView.setAdapter(mBookshelfAdapter);

        for (int i = 0; i < mBookshelfArrayList.size(); i++) {
            Bookshelf bookshelf = mBookshelfArrayList.get(i);

            getVolumes task = new getVolumes();
            task.execute(bookshelf.getTitle(),String.valueOf(i));
        }


        return view;
    }

    private class getVolumes extends AsyncTask<String, String, String> {



        @Override
        protected String doInBackground(String... params) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(VOLUMES_URL + params[0] + "&maxResults=10&printType=books")
                    .build();
            Response response = null;
            try {
                response = client.newCall(request).execute();
                String responseStr = response.body().string();
                try {
                    parseJSONVolumes(responseStr, params[1]);
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
            mBookshelfAdapter.notifyDataSetChanged();
        }
    }

    public void parseJSONVolumes(String response, String position) throws JSONException {
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
            mBookshelfArrayList.get(Integer.valueOf(position)).getBookArrayList().add(book);
        }



    }



}
