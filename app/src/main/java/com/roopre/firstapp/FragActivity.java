package com.roopre.firstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import java.util.Random;

public class FragActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frag);

        FragmentManager fragmentManager = getSupportFragmentManager();

        ColorFragment colorFragment = (ColorFragment) fragmentManager.findFragmentById(R.id.color_fragment);

        colorFragment.setColor(Color.BLUE);
    }

    public void change(View view) {

        ColorFragment colorFragment = new ColorFragment();

        int red = new Random().nextInt(256);
        int green = new Random().nextInt(256);
        int blue = new Random().nextInt(256);

        colorFragment.setColor(Color.rgb(red, green, blue));
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, colorFragment)
                .commit();


    }

    @Override
    public void onBackPressed() {

       ExitDialogFragment exitDialogFragment = new ExitDialogFragment();

        exitDialogFragment.show(getSupportFragmentManager(), "exit");

    }
}
