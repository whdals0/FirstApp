package com.roopre.firstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class SignupActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();
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

                    new HttpAsyncTask().execute();

                }else{
                    //값이 입력 안되어 있음
                    Log.d("SignupActivity", "OnClick CheckValid is false");
                }
            }
        });
    }

    // 지역함수, boolean 값을 리턴값으로 가짐
    private boolean CheckValid(){
        if(email_et.getText().toString().length() < 5 || pw_et.getText().toString().length() < 2){
            Toast.makeText(this, "이메일과 패스워드를 확하세요.", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            return true;
        }
    }
    class HttpAsyncTask extends AsyncTask<String, Void, String>{

        OkHttpClient client = new OkHttpClient();

        @Override
        protected String doInBackground(String... params) {

            String result = null;

            HttpUrl httpUrl = new HttpUrl.Builder()
                    .scheme("http")
                    .host("13.209.236.238")
                    .addPathSegment("user_join.php")
                    .addQueryParameter("userid", email_et.getText().toString())
                    .addQueryParameter("passwd", pw_et.getText().toString())
                    .build();

            try {
                Request request = new Request.Builder()
                        .addHeader("accept", "application/json")
                        .url(httpUrl)
                        .build();
                Response response = client.newCall(request).execute();
                result = response.body().string();
                //Log.d(TAG, "doInBackground = "+result);

            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d(TAG, "result = "+s);
        }
    }

//    class HttpAsyncTask extends AsyncTask<String, Void, String> {
//
//        OkHttpClient client = new OkHttpClient();
//
//        @Override
//        protected String doInBackground(String... params) {
//
//            String result = null;
//            OkHttpClient client = new OkHttpClient();
//
//            HttpUrl httpUrl = new HttpUrl.Builder()
//                    .scheme("http")
//                    .host("54.180.108.217")
//                    .addPathSegment("user_join.php")
//                    .addQueryParameter("userid", email_et.getText().toString())
//                    .addQueryParameter("passwd", pw_et.getText().toString())
//                    // Each addPathSegment separated add a / symbol to the final url
//                    // finally my Full URL is:
//                    // https://subdomain.apiweb.com/api/v1/students/8873?auth_token=71x23768234hgjwqguygqew
//                    .build();
//
//            System.out.println(httpUrl.toString());
//
//            try {
//                Request requesthttp = new Request.Builder()
//                        .addHeader("accept", "application/json")
//                        .url(httpUrl) // <- Finally put httpUrl in here
//                        .build();
//
//                Response response = null;
//                response = client.newCall(requesthttp).execute();
//                result = response.body().string();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return result;
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//
//            Log.d(TAG, s);
//            if(s.contains("failed") || s.contains("error")){
//                Toast.makeText(SignupActivity.this, "잠시 후 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
//            }else
//            {
//                Toast.makeText(SignupActivity.this, "회원가입 되었습니다.", Toast.LENGTH_SHORT).show();
//
//                Intent intent = new Intent();
//                intent.putExtra("email", email_et.getText().toString());
//                intent.putExtra("pw", pw_et.getText().toString());
//                setResult(RESULT_OK, intent);
//                finish();
//            }
//
//        }
//    }



}
