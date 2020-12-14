package com.example.edushare.ui.news;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.example.edushare.R;

public class news4 extends AppCompatActivity {

    private WebView myWebView;
    private WebSettings myWebSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newspage_webview);

        myWebView = (WebView)findViewById(R.id.news_webView);
        myWebView.setWebViewClient(new WebViewClient()); // 클릭시 새창 안뜨게
        myWebSettings = myWebView.getSettings();

        myWebSettings.setJavaScriptEnabled(true); // 웹페이지 자바스크립트  허용 여부
        myWebSettings.setSupportMultipleWindows(false); // 새창 띄우기 허용 여부
        myWebSettings.setUseWideViewPort(true); // 화면 사이즈 맞추기 허용 여부
        myWebSettings.setSupportZoom(true); // 화면 줌 허용 여부
        myWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); // 컨텐츠 사이즈 맞추기
        myWebSettings.setCacheMode(myWebSettings.LOAD_NO_CACHE); // 브라우저 캐쉬 허용 여부
        myWebSettings.setDomStorageEnabled(true); // 로컬저장소 허용 여부

        myWebView.loadUrl("http://www.hani.co.kr/");
    }
}