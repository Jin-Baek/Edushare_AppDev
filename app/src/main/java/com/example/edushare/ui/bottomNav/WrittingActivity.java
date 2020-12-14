package com.example.edushare.ui.bottomNav;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.edushare.R;
import com.example.edushare.ui.categoryItem.ArticleService;
import com.example.edushare.ui.categoryItem.CategoryAPage;
import com.example.edushare.ui.categoryItem.CategoryBPage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WrittingActivity extends AppCompatActivity {

    EditText title;
    EditText content;
    TextView writer;
    Spinner spinner;
    Button bt_cancel;
    Button bt_register;
    Button bt_revise;
    String selected_category;
    Toolbar toolbar;
    ActionBar actionBar;
    TextView actionbarTitle;
    String category_name;
    private int articleNum;
    private ArticleService articleService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writting);

        title = findViewById(R.id.edit_title);
        content = findViewById(R.id.edit_content);
        writer = findViewById(R.id.edit_writer);
        spinner = (Spinner)findViewById(R.id.spinner);

        bt_register = (Button)findViewById(R.id.bt_register);

        toolbar = findViewById(R.id.toolbar);

        articleNum = 30;

        //------------ Spinner ----------------------------
        ArrayAdapter listAdapter = ArrayAdapter.createFromResource(this,R.array.category,android.R.layout.simple_spinner_dropdown_item);

        listAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(listAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selected_category = spinner.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getApplicationContext(),"카테고리를 선택해주세요",Toast.LENGTH_SHORT);
            }
        });

        bt_register.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                Intent writeToCategory = null;

                if(selected_category.equals(getString(R.string.Acategory))){
                    category_name = "A";
                    writeToCategory = new Intent(getApplicationContext(),CategoryAPage.class);
                }else if(selected_category.equals(getString(R.string.Bcategory))){
                    category_name = "B";
                    writeToCategory=new Intent(getApplicationContext(), CategoryBPage.class);
                }

                // ============================  Retrofit: 기본 설정 ====================================================

                Gson gson = new GsonBuilder()
                        .setLenient()
                        .create();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://10.0.2.2:8090")
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();

                // ============================  Retrofit: 서버 게시글 중 마지막 게시글 번호 가져오기 =========================================
                articleService = retrofit.create(ArticleService.class);

                articleService.getLastArticleNum().enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if(response.isSuccessful()){
                            Log.d("This Log for get last number from server",response.body());
                            articleNum = Integer.parseInt(response.body());

                           // Log.d("PARAPAPRPRAPRPAPRAPRPARPAPRPARP",number+"");
                        }
                    }
                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                    }
                });

                // ============================  Retrofit: 안드로이드에서 서버로 데이터 넣어주기 ====================================================

                Handler mHandler = new Handler();
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        HashMap<String,String> map = new HashMap<>();
                        map.put("article_title",title.getText().toString());
                        map.put("article_content",content.getText().toString());
                        map.put("article_writer",writer.getText().toString());
                        map.put("article_num",articleNum+1+"");
                        map.put("category_name",category_name);
                        // 글을 작성한 시간 같은 경우에는 서버의 query 에서 sysdate 를 이용해서 자동으로 입력됨
                        // map.put("article_time",articleTime);

                        articleService.putArticleData(map).enqueue(new Callback<String>(){

                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                Log.d("!!!!!!!!!!!연결 정상적!!!!!!!!!!!!!!!!!!!!!", response.body());

                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                Log.d("!!!!!!!!!!!연결 실패!!!!!!!!!!!!!!!!!!!!!", t.getMessage());
                            }
                        });

                    }
                },3000);


                startActivity(writeToCategory);
            }
        });


        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);

        actionbarTitle=findViewById(R.id.title_textView);
        actionbarTitle.setText(R.string.writing_title);
        actionbarTitle.setTextSize(21);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_actionbar,menu);
        return super.onCreateOptionsMenu(menu);
    }
}