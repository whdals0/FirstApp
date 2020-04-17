package com.roopre.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void GoToWebView(View v){
        Intent intent = new Intent(this, WebviewActivity.class);
        startActivity(intent);
    }

    public void GoToListView(View v){
        Intent intent = new Intent(this, ListViewActivity.class);
        startActivity(intent);
    }

    public void GoToGridView(View v){
        Intent intent = new Intent(this, GridViewActivity.class);
        startActivity(intent);
    }
}
