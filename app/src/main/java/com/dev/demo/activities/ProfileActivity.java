
package com.dev.demo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.dev.demo.R;
import com.dev.demo.models.Characters;
import com.mikhaellopez.circularimageview.CircularImageView;

public class ProfileActivity extends AppCompatActivity {

    private CircularImageView imageView;
    private TextView profileNameTextView, profileBirthdayTextView;
    private Characters profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        imageView = findViewById(R.id.profile_image_view);
        profileNameTextView = findViewById(R.id.profile_name_text_view);
        profileBirthdayTextView = findViewById(R.id.profile_birthday_text_view);
        Intent intent = getIntent();
        profile = (Characters)intent.getSerializableExtra("data");
        setData();
    }

    private void setData() {
        profileNameTextView.setText(profile.getName());
        profileBirthdayTextView.setText(profile.getBirthday());
        Glide.with(this)
                .load(profile.getImg())
                .into(imageView);
    }
}