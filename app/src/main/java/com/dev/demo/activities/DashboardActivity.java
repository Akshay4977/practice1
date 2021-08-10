package com.dev.demo.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.demo.R;
import com.dev.demo.adapter.CharacterAdapter;
import com.dev.demo.models.Characters;
import com.dev.demo.retrofit.APIClient;
import com.dev.demo.retrofit.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity implements CharacterAdapter.ItemClickListener {

    private CharacterAdapter characterAdapter;

    private RecyclerView recycleView;
    private ApiInterface apiInterface;
    private ProgressDialog dialog;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        apiInterface = APIClient.getRetrofitClient().create(ApiInterface.class);
        recycleView = findViewById(R.id.character_recycle_view);
        textView = findViewById(R.id.text_view);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getCharacters();
    }

    private void getCharacters() {
        showLoading();
        Call<List<Characters>> call = apiInterface.getCharacter();

        call.enqueue(new Callback<List<Characters>>() {
            @Override
            public void onResponse(Call<List<Characters>> call, Response<List<Characters>> response) {
                setData(response.body());
                recycleView.setVisibility(View.VISIBLE);
                textView.setVisibility(View.GONE);
                hideDialog();
            }

            @Override
            public void onFailure(Call<List<Characters>> call, Throwable t) {
                recycleView.setVisibility(View.GONE);
                textView.setVisibility(View.VISIBLE);
                hideDialog();
            }
        });
    }

    private void setData(List<Characters> characters) {
        if (characters != null) {
            recycleView.setLayoutManager(new LinearLayoutManager(this));
            characterAdapter = new CharacterAdapter(this, characters);
            characterAdapter.setClickListener(this);
            recycleView.setAdapter(characterAdapter);
        } else {
            textView.setVisibility(View.VISIBLE);
        }
    }

    private void showLoading() {
        dialog = new ProgressDialog(this);
        dialog.setTitle(getString(R.string.loading_text));
        dialog.setMessage(getString(R.string.loading_message));
        dialog.setCancelable(false);
        dialog.show();
    }

    private void hideDialog() {
        if(dialog != null) {
            dialog.dismiss();
        }

    }

    @Override
    public void onItemClick(View view, int position, Characters characters) {
        Intent intent = new Intent(DashboardActivity.this, ProfileActivity.class);
        intent.putExtra("data", characters);
        startActivity(intent);
    }
}