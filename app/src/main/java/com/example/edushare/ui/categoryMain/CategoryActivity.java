package com.example.edushare.ui.categoryMain;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.edushare.R;
import com.example.edushare.ui.categoryItem.CategoryAPage;
import com.example.edushare.ui.categoryItem.CategoryBPage;

public class CategoryActivity extends AppCompatActivity {

    private ImageView imageView;
    private Toolbar toolbar;
    private ActionBar actionBar;
    private TextView actionbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        // 레이아웃 매니저를 설정해서 리싸이클러뷰가 보일 기본적인 형태 설정 -> VERTICAL 로 설정해서 세로 방향 스크롤임 ( 격자 모양을 원하면 GridLayoutManager 사용 )
        LinearLayoutManager layoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(layoutManager);

        final CategoryAdapter categoryAdapter = new CategoryAdapter();

        categoryAdapter.setOnItemClickListener(new OnCategoryItemClickListener() {
            @Override
            public void onItemClick(CategoryAdapter.ViewHolder holder, View view, int position) {
                Category item = categoryAdapter.getItem(position);
                if(item.getName().equals(getString(R.string.Acategory))){
                    Intent intent = new Intent(getApplicationContext(), CategoryAPage.class);
                    startActivity(intent);
                }else if(item.getName().equals(getString(R.string.Bcategory))){
                   Intent intent = new Intent(getApplicationContext(), CategoryBPage.class);
                    startActivity(intent);
                }else if(item.getName().equals(getString(R.string.Ccategory))){
                    //Intent intent = new Intent(getApplicationContext(), CategoryCPage.class);
                   // startActivity(intent);
                }else if(item.getName().equals(getString(R.string.Ecategory))){
                    Toast.makeText(getApplicationContext(),"아이템 선택됨: "+item.getName(),Toast.LENGTH_SHORT).show();
                }
            }
        });
        // 아래 있는 요소들 배열로 만들어서 인덱스로 즉 int 로 배열에 접근할 수 있게끔 설정해보자.
        categoryAdapter.addItem(new Category(R.drawable.a_category,getString(R.string.Acategory),1));
        categoryAdapter.addItem(new Category(R.drawable.b_category,getString(R.string.Bcategory),2));
        categoryAdapter.addItem(new Category(R.drawable.c_category,getString(R.string.Ccategory),3));
        categoryAdapter.addItem(new Category(R.drawable.d_category,getString(R.string.Dcategory),4));
        categoryAdapter.addItem(new Category(R.drawable.e_category,getString(R.string.Ecategory),5));
        categoryAdapter.addItem(new Category(R.drawable.f_category,getString(R.string.Fcategory),6));
        categoryAdapter.addItem(new Category(R.drawable.g_category,getString(R.string.Gcategory),7));
        categoryAdapter.addItem(new Category(R.drawable.h_category,getString(R.string.Hcategory),8));
        categoryAdapter.addItem(new Category(R.drawable.i_category,getString(R.string.Icategory),9));


        // 리싸이클러뷰와 어댑터 연결 시키기
        recyclerView.setAdapter(categoryAdapter);

        imageView = findViewById(R.id.imageView);

        // 액션바 설정하기
        toolbar = findViewById(R.id.board_toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        actionbarTitle = findViewById(R.id.title_textView);
        actionbarTitle.setText(R.string.category_title);
        actionbarTitle.setTextSize(21);


    }
    // 액션바 메뉴 설정
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_actionbar,menu);
        return super.onCreateOptionsMenu(menu);
    }
}