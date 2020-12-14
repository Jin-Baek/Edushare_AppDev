package com.example.edushare.ui.categoryItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;

import com.example.edushare.R;
import com.example.edushare.ui.idea.Ideapage;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ArticlePage extends AppCompatActivity {

    private Toolbar toolbar;
    private ActionBar actionBar;
    private TextView articlePage_title;
    private TextView articlePage_content;
    private TextView articlePage_writer;
    private FloatingActionButton fab;
    private ArticleForm item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_page);

        toolbar = findViewById(R.id.articlePage_toolbar);
        articlePage_title = findViewById(R.id.each_article_title);
        articlePage_content = findViewById(R.id.each_article_content);
        articlePage_writer = findViewById(R.id.each_article_writer);
        fab = findViewById(R.id.articlePage_fab);

        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);

        // ===================== CategoryPage 에서 넘겨준 데이터 ==================================
        Intent fromCategoryItem = getIntent();
        item = (ArticleForm) fromCategoryItem.getSerializableExtra("article");

        Log.d("AAAAAAAADDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF",item.getArticle_content());
        articlePage_title.setText(item.getArticle_title());
        articlePage_content.setText(item.getArticle_content());
        articlePage_writer.setText(item.getArticle_writer());

        final Intent toIdeaPage = new Intent(this, Ideapage.class);

        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                toIdeaPage.putExtra("article_title",item.getArticle_title());
                toIdeaPage.putExtra("article_content",item.getArticle_content());
                toIdeaPage.putExtra("article_writer",item.getArticle_writer());
                startActivity(toIdeaPage);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.sub_actionbar,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
