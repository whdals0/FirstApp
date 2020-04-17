package com.roopre.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class WebviewActivity extends AppCompatActivity {

    // 선언
    EditText addr_et;
    Button action_btn;
    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        // 초기화
        addr_et = findViewById(R.id.addr_et);
        action_btn = findViewById(R.id.action_btn);
        webview = findViewById(R.id.webview);

        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient());

        action_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String addr = addr_et.getText().toString();

                if (addr.startsWith("http://")) {

                }else
                {
                    addr = "http://" + addr;
                }

                webview.loadUrl(addr);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(webview.canGoBack()){
            webview.goBack();
        }else {
            super.onBackPressed();
        }
    }
}
