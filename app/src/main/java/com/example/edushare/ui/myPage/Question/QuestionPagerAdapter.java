package com.example.edushare.ui.myPage.Question;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class QuestionPagerAdapter extends FragmentStateAdapter{

    public QuestionPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        Fragment moveFragment=null;
        switch (position){
            case 0:
                QnaFragment qnaFragment = new QnaFragment();
                moveFragment = qnaFragment;
                break;
            case 1:
                FaqFragment faqFragment = new FaqFragment();
                moveFragment = faqFragment;
                break;
            default:
                moveFragment=null;
        }

        return moveFragment;
    }
}
