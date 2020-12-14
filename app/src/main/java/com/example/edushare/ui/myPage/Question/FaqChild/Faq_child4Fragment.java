package com.example.edushare.ui.myPage.Question.FaqChild;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.edushare.R;

public class Faq_child4Fragment extends Fragment {
    private Button child4_btn1;
    private Button child4_btn2;
    private Button child4_btn3;
    private LinearLayout layout1;
    private LinearLayout layout2;
    private LinearLayout layout3;

    public static Faq_child4Fragment newInstance() {
        return new Faq_child4Fragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_faq_child4, container, false);

        init(root);

        child4_btn1.setOnClickListener(new View.OnClickListener(){

            int i = 0;
            @Override
            public void onClick(View view) {
                if(i==0){
                    layout1.setVisibility(View.VISIBLE);
                    i++;
                }else{
                    layout1.setVisibility(View.GONE);
                    i--;
                }
            }
        });

        child4_btn2.setOnClickListener(new View.OnClickListener(){
            int i = 0;
            @Override
            public void onClick(View view) {
                if(i==0){
                    layout2.setVisibility(View.VISIBLE);
                    i++;
                }else{
                    layout2.setVisibility(View.GONE);
                    i--;
                }
            }
        });

        child4_btn3.setOnClickListener(new View.OnClickListener(){
            int i = 0;
            @Override
            public void onClick(View view) {
                if(i==0){
                    layout3.setVisibility(View.VISIBLE);
                    i++;
                }else{
                    layout3.setVisibility(View.GONE);
                    i--;
                }
            }
        });

        return root;
    }
    public void init(View root){
        child4_btn1 = root.findViewById(R.id.child4_btn1);
        child4_btn2 = root.findViewById(R.id.child4_btn2);
        child4_btn3 = root.findViewById(R.id.child4_btn3);
        layout1 = root.findViewById(R.id.btn1_layout);
        layout2 = root.findViewById(R.id.btn2_layout);
        layout3 = root.findViewById(R.id.btn3_layout);

    }
}