package com.roopre.firstapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button signin_btn;
    Button signup_btn;
    ImageView main_iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signin_btn = findViewById(R.id.signin_btn);
        signup_btn = findViewById(R.id.signup_btn);
        main_iv = findViewById(R.id.main_iv);

        signin_btn.setOnClickListener(this);
        signup_btn.setOnClickListener(this);
        main_iv.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.signup_btn:
                //Toast.makeText(this, "Signup Button Clicked!!!!!!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.signin_btn:
                Toast.makeText(this, "SignIn Clicked!!!!!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_iv:
                Toast.makeText(this, "Image Clicked!!!!!", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
