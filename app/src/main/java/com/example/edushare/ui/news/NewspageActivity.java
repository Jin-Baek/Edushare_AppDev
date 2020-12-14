package com.example.edushare.ui.news;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.edushare.R;
import com.example.edushare.ui.categoryItem.ArticleAdapter;
import com.example.edushare.ui.categoryItem.ArticleForm;
import com.example.edushare.ui.categoryItem.OnArticleItemClickListener;

import java.util.ArrayList;

public class NewspageActivity extends AppCompatActivity {

    private Toolbar news_toolbar;
    private ActionBar actionBar;
    private TextView news_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newspage);

        news_title = findViewById(R.id.news_title);
        news_toolbar = findViewById(R.id.news_toolbar);

        // ------------------- Setting actionbar -------------------------------
        setSupportActionBar(news_toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);

        news_title.setText(R.string.news_title);
        news_title.setTextSize(21);


        //-------------------layoutButton -> WebView  ---------------------------------------
        LinearLayout layout1 = findViewById(R.id.layout_first);
        LinearLayout layout2 = findViewById(R.id.layout_second);
        LinearLayout layout3 = findViewById(R.id.layout_third);
        LinearLayout layout4 = findViewById(R.id.layout_fourth);

        layout1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),news1.class);
                startActivity(intent);

            }
        });

        layout2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),news2.class);
                startActivity(intent);
            }
        });

        layout3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),news3.class);
                startActivity(intent);
            }
        });

        layout4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),news4.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_actionbar,menu);
        return super.onCreateOptionsMenu(menu);
    }

}