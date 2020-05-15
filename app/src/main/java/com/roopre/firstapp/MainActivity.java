package com.roopre.firstapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = getClass().getSimpleName();

    // 버튼 이나 뷰 선언 부분
    Button signin_btn;
    Button signup_btn;
    ImageView main_iv;
    EditText email_et, pw_et;
    CheckBox checkBox;

    SharedPreferences spref;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        Log.d(TAG, "LifeCycle - onCreate");

        // 선언한 뷰 초기화
        signin_btn = findViewById(R.id.signin_btn);
        signup_btn = findViewById(R.id.signup_btn);
        main_iv = findViewById(R.id.main_iv);
        email_et = findViewById(R.id.email_et);
        pw_et = findViewById(R.id.pw_et);
        checkBox = findViewById(R.id.checkbox);

        // 초기화한 뷰에 대한 이벤트 리스터 추가
        signin_btn.setOnClickListener(this);
        signup_btn.setOnClickListener(this);
        main_iv.setOnClickListener(this);

        spref = PreferenceManager.getDefaultSharedPreferences(this);


        //값을 넣는 방법
//        SharedPreferences.Editor editor = spref.edit();
//        editor.putString("test", "1234");
//        editor.putBoolean("checked", true);
//        editor.apply();
//
//        //값을 가져오는 방법
//        String testData = spref.getString("test", "");
//        boolean checked = spref.getBoolean("checked", false);



        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    // 클릭해서 체크박스가 활성화된 상태
                    SharedPreferences.Editor editor = spref.edit();
                    editor.putBoolean("auto", true);
                    editor.apply();

                }else
                {
                    // 클릭해서 체크박스가 비활성화된 상태
                    SharedPreferences.Editor editor = spref.edit();
                    editor.putBoolean("auto", false);
                    editor.apply();
                }
            }
        });
        Log.d(TAG, "onCreate");

    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG, "onResume");
        Log.d(TAG, "auto spref value = "
                +spref.getBoolean("auto", false));

        boolean auto = spref.getBoolean("auto", false);
        checkBox.setChecked(auto);

    }

    // 실질적인 이벤트 구현부
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.signup_btn:
                //Toast.makeText(this, "Signup Button Clicked!!!!!!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, SignupActivity.class);
                startActivityForResult(intent, 100);

                break;
            case R.id.signin_btn:

                new HttpAsyncTask().execute();
//                Toast.makeText(this, "SignIn Clicked!!!!!", Toast.LENGTH_SHORT).show();
//                SharedPreferences.Editor editor = spref.edit();
//
//                editor.putString("email", email_et.getText().toString());
//                editor.putString("pw", pw_et.getText().toString());
//
//                editor.apply();

                break;
            case R.id.main_iv:
                Toast.makeText(this, "Image Clicked!!!!!", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    class HttpAsyncTask extends AsyncTask<String, Void, String> {

        OkHttpClient client = new OkHttpClient();

        @Override
        protected String doInBackground(String... params) {

            String result = null;
            OkHttpClient client = new OkHttpClient();

            HttpUrl httpUrl = new HttpUrl.Builder()
                    .scheme("http")
                    .host("54.180.108.217")
                    .addPathSegment("user_login.php")
                    .addQueryParameter("userid", email_et.getText().toString())
                    .addQueryParameter("passwd", pw_et.getText().toString())
                    // Each addPathSegment separated add a / symbol to the final url
                    // finally my Full URL is:
                    // https://subdomain.apiweb.com/api/v1/students/8873?auth_token=71x23768234hgjwqguygqew
                    .build();

            System.out.println(httpUrl.toString());

            try {
                Request requesthttp = new Request.Builder()
                        .addHeader("accept", "application/json")
                        .url(httpUrl) // <- Finally put httpUrl in here
                        .build();

                Response response = null;
                response = client.newCall(requesthttp).execute();
                result = response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d(TAG, s);
            if(s.contains("no user") || s.contains("error")){
                Toast.makeText(MainActivity.this, "아이디와 비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show();
            }else
            {
                Toast.makeText(MainActivity.this, "로그인 되었습니다.", Toast.LENGTH_SHORT).show();
                LoginProcess();
            }

        }
    }

    private void LoginProcess(){
        Log.d(TAG, "Login Process");
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100) {
            if(resultCode == RESULT_OK){
                String email = data.getStringExtra("email");
                String pw = data.getStringExtra("pw");

                email_et.setText(email);
                pw_et.setText(pw);

            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.signup_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.add_menu:
                Toast.makeText(this, "add_menu Clicked!!!", Toast.LENGTH_SHORT).show();

                break;
            case R.id.refresh_menu:
                //Toast.makeText(this, "refresh_menu Clicked!!!", Toast.LENGTH_SHORT).show();
                Refresh();
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("종료 확인");
        builder.setMessage("정말 종료하시겠습니까?");
        builder.setPositiveButton("종료", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.super.onBackPressed();
            }
        });
        builder.setNegativeButton("취소", null);
        builder.show();

    }

    private void Refresh(){
        // 리플레시 함수

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);


    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        outState.putString("email", email_et.getText().toString());

        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart");
        super.onStart();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();

    }
}
