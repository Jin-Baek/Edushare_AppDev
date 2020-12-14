package com.example.edushare.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.edushare.R;
import com.example.edushare.ui.categoryItem.ArticleAdapter;
import com.example.edushare.ui.categoryItem.ArticleForm;
import com.example.edushare.ui.categoryItem.ArticlePage;
import com.example.edushare.ui.categoryItem.ArticleService;
import com.example.edushare.ui.categoryItem.OnArticleItemClickListener;
import com.example.edushare.ui.home.viewpager.Event1Fragment;
import com.example.edushare.ui.home.viewpager.Event2Fragment;
import com.example.edushare.ui.home.viewpager.Event3Fragment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private ViewPager2 viewPager;
    private MyPagerAdapter pagerAdpater;
    private int num_page=3;
    private CircleIndicator3 indicator;
    private RecyclerView recyclerView;
    private ArticleAdapter homeadapter;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        final TextView textView = root.findViewById(R.id.textView);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        viewPager = root.findViewById(R.id.viewPager);
        pagerAdpater = new MyPagerAdapter(this,num_page);

        indicator = root.findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);
        indicator.createIndicators(num_page,0);

        viewPager.setAdapter(pagerAdpater);
        viewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        viewPager.setCurrentItem(50);
        viewPager.setOffscreenPageLimit(2);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback(){
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if(positionOffsetPixels ==0){
                    viewPager.setCurrentItem(position);
                }
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                indicator.animatePageSelected(position % num_page);
            }
        });

        // 뷰페이저 프래먼트 간 애니메이션 맞춤설정
        final float pageMargin = getResources().getDimensionPixelOffset(R.dimen.pageMargin);
        final float pageOffset = getResources().getDimensionPixelOffset(R.dimen.offset);

        viewPager.setPageTransformer(new ViewPager2.PageTransformer(){
            @Override
            public void transformPage(@NonNull View page, float position) {
                float myOffset = position * -(2 *pageOffset+pageMargin);
                if(viewPager.getOrientation() == ViewPager2.ORIENTATION_HORIZONTAL){
                    if(ViewCompat.getLayoutDirection(viewPager)==ViewCompat.LAYOUT_DIRECTION_RTL){
                        page.setTranslationX(-myOffset);
                    }else{
                        page.setTranslationX(myOffset);
                    }
                }else{
                    page.setTranslationY(myOffset);
                }
            }
        });

        // ===================== 리싸이클러뷰 세팅 ==================
        recyclerView = root.findViewById(R.id.homeFragment_recyclerview);

        ArrayList<ArticleForm> home_list = new ArrayList<>();
        homeadapter = new ArticleAdapter(home_list);

        recyclerView.setAdapter(homeadapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

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
        articleService.getAllArticleData().enqueue(new Callback<List<ArticleForm>>() {
            @Override
            public void onResponse(Call<List<ArticleForm>> call, retrofit2.Response<List<ArticleForm>> response) {
                if(response.isSuccessful()){

                    for(int i=0;i<response.body().size();i++){

                        //Log.d("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAa",response.body().get(i).getArticle_title()+"");

                        homeadapter.addItem(new ArticleForm(response.body().get(i).getArticle_title(),response.body().get(i).getArticle_content(),
                                response.body().get(i).getArticle_writer(),response.body().get(i).getArticle_num(),
                                response.body().get(i).getArticle_time(),response.body().get(i).getCategory_name()));

                        homeadapter.notifyDataSetChanged();
                    }
                    Log.d("RetrofitData",response.body()+"");
                }
            }

            @Override
            public void onFailure(Call<List<ArticleForm>> call, Throwable t) {
                Log.d("Fail Messaage",t.getMessage());

            }
        });
        homeadapter.notifyDataSetChanged();

        final Intent toArticle = new Intent(getActivity(), ArticlePage.class);

        homeadapter.setOnItemClickListener(new OnArticleItemClickListener() {
            @Override
            public void onItemClick(ArticleAdapter.ViewHolder holder, View view, int position) {
                ArticleForm item = homeadapter.getItem(position);
                toArticle.putExtra("article",item);
                startActivity(toArticle);
            }
        });

        return root;
    }

    public class MyPagerAdapter extends FragmentStateAdapter {

        public int mCount;

        public MyPagerAdapter(Fragment fragment, int mCount) {
            super(fragment);
            this.mCount = mCount;
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            int index = position % mCount;
            if(index==0){
                return new Event1Fragment();
            }else if(index ==1){
                return new Event2Fragment();
            }else{
                return new Event3Fragment();
            }
        }

        @Override
        public int getItemCount() {
            return 99;
        }
    }
}