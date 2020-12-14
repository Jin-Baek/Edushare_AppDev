package com.example.edushare;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.example.edushare.ui.categoryMain.CategoryActivity;
import com.example.edushare.ui.bottomNav.WrittingActivity;
import com.example.edushare.ui.myPage.MyPageFragment;
import com.example.edushare.ui.home.HomeFragment;
import com.example.edushare.ui.news.NewspageActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener , FragmentCallback{

    DrawerLayout drawer;
    HomeFragment homeFragment;
    MyPageFragment myPageFragment;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        homeFragment = new HomeFragment();
        myPageFragment = new MyPageFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.container,homeFragment).commit();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        // -------------------- Bottom Navigation Control ---------------------------------------

        final BottomNavigationView bottomNavigationView = findViewById(R.id.buttom_navigation);
        final Intent writting_intent = new Intent(this,WrittingActivity.class);
        final Intent news_intent = new Intent(this, NewspageActivity.class);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.b_pointrank:
                        break;
                    case R.id.b_News:
                        startActivity(news_intent);
                        break;
                    case R.id.b_easywritting:
                        startActivity(writting_intent);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_actionbar, menu);
        return true;
    }


    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.nav_home){
            onFragmentSelected(0,null);
        }else if(id == R.id.nav_mypage){
            onFragmentSelected(1,null);
        }else if(id==R.id.nav_board){
            Intent intent = new Intent(this, CategoryActivity.class);
            startActivity(intent);
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentSelected(int position,Bundle bundle){
        Fragment currentFragment = null;

        switch(position){
            case 0:
                currentFragment = homeFragment;
                toolbar.setTitle("Home");
                break;
            case 1:
                currentFragment = myPageFragment;
                toolbar.setTitle("마이페이지");
                break;
            default:
                currentFragment = homeFragment;
                break;
                // Board 는 프래그먼트가 아니라 액티비티로 구성하엿음.
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.container,currentFragment).commit();
    }
}