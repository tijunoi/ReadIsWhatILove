package com.example.bertiwi.readiswhatilove.activities;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.bertiwi.readiswhatilove.R;
import com.example.bertiwi.readiswhatilove.fragments.SearchFragment;
import com.example.bertiwi.readiswhatilove.model.Book;
import com.like.LikeButton;
import com.squareup.picasso.Picasso;

public class BookProfileActivity extends AppCompatActivity {

    private ImageView imageProfile, star1, star2, star3, star4, star5;
    private TextView titleProfile, authorProfile, publisherProfile, dateProfile, descriptionProfile;
    private LikeButton likeButton;
    private RatingBar ratingBar;

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

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
