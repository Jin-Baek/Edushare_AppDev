package com.example.edushare.ui.myPage;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.edushare.R;
import com.example.edushare.ui.myPage.Question.QuestionActivity;

public class MyPageFragment extends Fragment {

    private MyPageViewModel myPageViewModel;
    private Button QNA_button;
    private Button FAQ_button;
    private Button help_button;
    private Button logout_button;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        myPageViewModel =
                ViewModelProviders.of(this).get(MyPageViewModel.class);

        View root = inflater.inflate(R.layout.fragment_mypage, container, false);

        QNA_button = root.findViewById(R.id.QNA_button);

        QNA_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), QuestionActivity.class);
                startActivity(intent);
            }
        });

        FAQ_button = root.findViewById(R.id.FAQ_button);

        FAQ_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),QuestionActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }
}