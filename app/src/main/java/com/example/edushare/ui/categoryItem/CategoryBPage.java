package com.example.edushare.ui.categoryItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;

import com.example.edushare.MainActivity;
import com.example.edushare.R;
import com.example.edushare.ui.bottomNav.WrittingActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoryBPage extends AppCompatActivity {

    private Toolbar B_toolbar;
    private ActionBar B_actionBar;
    private TextView B_toolbarTitle;
    RecyclerView B_recyclerView = null;
    ArticleAdapter B_articleAdapter = null;
    ArrayList<ArticleForm> B_mList = new ArrayList<ArticleForm>();
    FloatingActionButton B_home_fab;
    FloatingActionButton B_writing_fab;
    FloatingActionButton B_main_fab;
    private boolean isOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoryitem_page);

        // ========================================== fab 버튼 설정 ( 디자인 수정 필요) =======================================
        B_home_fab = findViewById(R.id.home_fab);
        B_writing_fab = findViewById(R.id.writing_fab);
        B_main_fab = findViewById(R.id.main_fab);

        B_writing_fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent writingPage_intent = new Intent(getApplicationContext(), WrittingActivity.class);
                startActivity(writingPage_intent);
            }
        });

        B_home_fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent mainPage_intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainPage_intent);
            }
        });

        B_main_fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(!isOpen){
                    ObjectAnimator.ofFloat(B_home_fab,"translationY",-400f).start();
                    ObjectAnimator.ofFloat(B_writing_fab,"translationY",-200f).start();
                    isOpen = true;
                }else {
                    ObjectAnimator.ofFloat(B_home_fab,"translationY",0f).start();
                    ObjectAnimator.ofFloat(B_writing_fab,"translationY",0f).start();
                    isOpen = false;
                }

            }
        });



        // ============================== 리싸이클러뷰 어댑터 설정 ===================================
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
        String nowTime = sdf.format(date);

        B_recyclerView = findViewById(R.id.recyclerView);

        B_articleAdapter = new ArticleAdapter(B_mList);

        B_recyclerView.setAdapter(B_articleAdapter);

        // 레이아웃 매니저를 설정해서 리싸이클러뷰가 보일 기본적인 형태 설정 -> VERTICAL 로 설정해서 세로 방향 스크롤임 ( 격자 모양을 원하면 GridLayoutManager 사용 )
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        B_recyclerView.setLayoutManager(layoutManager);

        final Intent toArticle = new Intent(this,ArticlePage.class);

        // =========== 클릭 시 게시글로 넘어가는 부분 ====================
        B_articleAdapter.setOnItemClickListener(new OnArticleItemClickListener() {
            @Override
            public void onItemClick(ArticleAdapter.ViewHolder holder, View view, int position) {
                ArticleForm item = B_articleAdapter.getItem(position);
                toArticle.putExtra("article",item);
                startActivity(toArticle);

            }
        });

        B_toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(B_toolbar);
        B_actionBar = getSupportActionBar();
        B_actionBar.setDisplayHomeAsUpEnabled(true);
        B_actionBar.setDisplayShowCustomEnabled(true);

        B_toolbarTitle = findViewById(R.id.toolbar_title);
        B_toolbarTitle.setText(R.string.Acategory);
        B_toolbarTitle.setTextSize(21);

        //B_articleAdapter.addItem(new ArticleForm("Btitle","Bcontent","Bwriter",2,"여기는 time"));

        // ============================  Retrofit: 기본 설정 ====================================================

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8090")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        // ============================  Retrofit: 안드로이드에서 서버에 있는 데이터 가져오기 ====================================================
        ArticleService articleService = retrofit.create(ArticleService.class);
        articleService.getBArticleData().enqueue(new Callback<List<ArticleForm>>() {
            @Override
            public void onResponse(Call<List<ArticleForm>> call, retrofit2.Response<List<ArticleForm>> response) {
                if(response.isSuccessful()){

                    for(int i=0;i<response.body().size();i++){

                        Log.d("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAa",response.body().get(i).getArticle_title()+"");

                        B_articleAdapter.addItem(new ArticleForm(response.body().get(i).getArticle_title(),response.body().get(i).getArticle_content(),
                                response.body().get(i).getArticle_writer(),response.body().get(i).getArticle_num(),
                                response.body().get(i).getArticle_time(),response.body().get(i).getCategory_name()));

                        B_articleAdapter.notifyDataSetChanged();
                    }
                    Log.d("RetrofitData",response.body()+"");
                }
            }

            @Override
            public void onFailure(Call<List<ArticleForm>> call, Throwable t) {
                Log.d("Fail Messaage",t.getMessage());

            }
        });
        B_articleAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_actionbar,menu);
        return super.onCreateOptionsMenu(menu);
    }
}