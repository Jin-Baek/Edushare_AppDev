package com.example.edushare.ui.idea;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;

import com.example.edushare.R;

public class Ideapage extends AppCompatActivity {

    private TextView article;
    private Toolbar ideaPage_toolbar;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ideapage);

        article = findViewById(R.id.ideaPage_GetArticle);

        Intent fromArticlePage = getIntent();

        article.setText("게시글 제목 : "+fromArticlePage.getStringExtra("article_title")+"     "+
                "작성자 :  "+fromArticlePage.getStringExtra("article_writer")+"\n\n"+
                fromArticlePage.getStringExtra("article_content"));

        ideaPage_toolbar = findViewById(R.id.ideapage_toolbar);
        setSupportActionBar(ideaPage_toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.sub_actionbar,menu);
        return super.onCreateOptionsMenu(menu);
    }
}