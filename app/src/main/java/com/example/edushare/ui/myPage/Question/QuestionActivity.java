package com.example.edushare.ui.myPage.Question;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;

import com.example.edushare.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.w3c.dom.Text;

public class QuestionActivity extends AppCompatActivity {

    private ActionBar actionBar;
    private Toolbar toolbar;
    private TextView actionbarTitle;
    private TabLayout tabLayout;
    private Context context;
    private QuestionPagerAdapter questionPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        initActionbar();

        tabLayout = findViewById(R.id.tabLayout_questionPage);

        /*
        원래 탭 아아템을 설정해주어야 하지만 viewPager2의 경우 TabLayoutMediator 을 사용하기 때문에 의미 X

        context = getApplicationContext();
        tabLayout.addTab(tabLayout.newTab().setCustomView(createTabView(getString(R.string.mypage_button_QNA))));
        tabLayout.addTab(tabLayout.newTab().setCustomView(createTabView(getString(R.string.mypage_button_FAQ))));
         */

        // -------------- ViewPager ------------------------
        ViewPager2 viewPager = findViewById(R.id.questionPager);
        questionPagerAdapter = new QuestionPagerAdapter(this);

        viewPager.setAdapter(questionPagerAdapter);

        final String QNAtitle = getResources().getString(R.string.mypage_button_QNA);
        final String FAQtitle = getResources().getString(R.string.mypage_button_FAQ);

        final String[] tabList = {QNAtitle,FAQtitle};

        new TabLayoutMediator(tabLayout,viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(tabList[position]);
            }
        }).attach();

    }

    // TabLayout 뷰 설정
    private View createTabView(String tabName){
        View tabView = LayoutInflater.from(context).inflate(R.layout.question_custom_tab,null);
        TextView custom_textView = tabView.findViewById(R.id.custom_textView);
        custom_textView.setText(tabName);

        return tabView;
    }

    public void initActionbar(){

        toolbar = findViewById(R.id.toolbar_questionPage);

        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);

        actionbarTitle=findViewById(R.id.title_questionPage);
        actionbarTitle.setText("질문 게시판");
        actionbarTitle.setTextSize(18);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.sub_actionbar,menu);
        return super.onCreateOptionsMenu(menu);
    }
}