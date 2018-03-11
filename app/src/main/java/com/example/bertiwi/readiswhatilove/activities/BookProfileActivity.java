package com.example.bertiwi.readiswhatilove.activities;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.bertiwi.readiswhatilove.R;
import com.example.bertiwi.readiswhatilove.fragments.SearchFragment;
import com.example.bertiwi.readiswhatilove.model.Book;
import com.example.bertiwi.readiswhatilove.utilities.VolleySingleton;
import com.like.LikeButton;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class BookProfileActivity extends AppCompatActivity {

    private ImageView imageProfile, star1, star2, star3, star4, star5;
    private TextView titleProfile, authorProfile, publisherProfile, dateProfile, descriptionProfile;
    private LikeButton likeButton;
    private RatingBar ratingBar;

    public static String ADD_URL = "https://www.googleapis.com/books/v1/mylibrary/bookshelves/0/addVolume";
    public static String REMOVE_URL = "https://www.googleapis.com/books/v1/mylibrary/bookshelves/0/removeVolume";

    private Book book;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_profile);

        android.support.v7.widget.Toolbar appbar = (android.support.v7.widget.Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(appbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setHomeButtonEnabled(true);

        book = (Book) getIntent().getParcelableExtra(SearchFragment.SELECTED_BOOK);

        imageProfile = findViewById(R.id.profile_image);
        titleProfile = findViewById(R.id.profile_name);
        authorProfile = findViewById(R.id.profile_autor);
        publisherProfile = findViewById(R.id.profile_publisher);
        dateProfile = findViewById(R.id.profile_date);
        descriptionProfile = findViewById(R.id.profile_description);
        likeButton = findViewById(R.id.heart_button);
        ratingBar = findViewById(R.id.ratingBar);

        if (likeButton.isLiked()){
            requestAddMethod();
        }else{
            requestRemoveMethod();
        }

        Picasso.with(getApplicationContext())
                .load(book.getImage())
                .into(imageProfile);

        titleProfile.setText(book.getTitle());
        if (book.getAuthor().isEmpty()){
            authorProfile.setVisibility(View.GONE);
        }else{
            authorProfile.setText(book.getAuthor());
        }
        if (book.getPublisher().isEmpty()){
            publisherProfile.setVisibility(View.GONE);
        }else{
            publisherProfile.setText(book.getPublisher());
        }
        if (book.getPublishedDate().isEmpty()){
            dateProfile.setVisibility(View.GONE);
        }else{
            dateProfile.setText(book.getPublishedDate());
        }
        if (book.getDescription().isEmpty()){
            descriptionProfile.setVisibility(View.GONE);
        }else{
            descriptionProfile.setText(book.getDescription());
        }

        ratingBar.setRating(Float.parseFloat(book.getAverageRating().toString()));
    }

    public void requestAddMethod() {
        StringRequest stringRequest2 = new StringRequest(Request.Method.POST, ADD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), "Libro añadido a favoritos", Toast.LENGTH_SHORT).show();
                        Log.d("----------ASDASAGENDA", response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("shelf", "0");
                params.put("volumeId", book.getId());
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest2);
    }

    public void requestRemoveMethod() {
        StringRequest stringRequest2 = new StringRequest(Request.Method.POST, REMOVE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), "Libro eliminado de favoritos", Toast.LENGTH_SHORT).show();
                        Log.d("----------ASDASAGENDA", response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("shelf", "0");
                params.put("volumeId", book.getId());
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest2);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
