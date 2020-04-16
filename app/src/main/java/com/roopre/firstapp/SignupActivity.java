package com.roopre.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    // 선언
    EditText email_et, pw_et;
    Button signup_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signup);

        // 초기화 & 할당
        email_et = findViewById(R.id.email_et);
        pw_et = findViewById(R.id.pw_et);
        signup_btn = findViewById(R.id.signup_btn);

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CheckValid()){
                    //정상적으로 값이 입력되어 있음
                    Log.d("SignupActivity", "Success");
                    Intent intent = new Intent();
                    intent.putExtra("email", email_et.getText().toString());
                    intent.putExtra("pw", pw_et.getText().toString());
                    setResult(RESULT_OK, intent);
                    finish();

                }else{
                    //값이 입력 안되어 있음
                    Log.d("SignupActivity", "OnClick CheckValid is false");
                }
            }
        });
    }

    // 지역함수, boolean 값을 리턴값으로 가짐
    private boolean CheckValid(){
//        if(email_et.getText().toString().length() < 5){
//            Toast.makeText(this, "이메일은 5자 이상 입력하세요.", Toast.LENGTH_SHORT).show();
//            return false;
//        }else if(pw_et.getText().toString().length() < 2){
//            Toast.makeText(this, "비밀번호는 2자 이상 입력하세요.", Toast.LENGTH_SHORT).show();
//            return false;
//        }else{인

        if(email_et.getText().toString().length() < 5 || pw_et.getText().toString().length() < 2){
            Toast.makeText(this, "이메일과 패스워드를 확하세요.", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            return true;
        }
    }



}
