package com.example.bertiwi.readiswhatilove.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bertiwi.readiswhatilove.R;

public class BookProfileActivity extends AppCompatActivity {

    private ImageView imageProfile, star1, star2, star3, star4, star5;
    private TextView titleProfile, authorProfile, publisherProfile, dateProfile, descriptionProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_profile);

        imageProfile = findViewById(R.id.profile_image);
        titleProfile = findViewById(R.id.profile_name);
        authorProfile = findViewById(R.id.profile_autor);
        publisherProfile = findViewById(R.id.profile_publisher);
        dateProfile = findViewById(R.id.profile_date);
        descriptionProfile = findViewById(R.id.profile_description);

    }
}
