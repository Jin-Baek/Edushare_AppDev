package com.example.edushare.ui.myPage.Question;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.edushare.R;
import com.example.edushare.ui.myPage.Question.FaqChild.Faq_child1Fragment;
import com.example.edushare.ui.myPage.Question.FaqChild.Faq_child2Fragment;
import com.example.edushare.ui.myPage.Question.FaqChild.Faq_child3Fragment;
import com.example.edushare.ui.myPage.Question.FaqChild.Faq_child4Fragment;

public class FaqFragment extends Fragment {

    private Spinner spinner;
    private String selected_item;
    private Context context;

    public static FaqFragment newInstance(){
        return new FaqFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_faq, container, false);

        spinner = root.findViewById(R.id.faq_spinner);
        ArrayAdapter listAdapter = ArrayAdapter.createFromResource(this.getActivity(),R.array.faq_section,android.R.layout.simple_spinner_dropdown_item);

        listAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(listAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selected_item = spinner.getItemAtPosition(i).toString();

                Fragment curFragment = null;

                switch(selected_item){
                    case "사용 목적":
                        curFragment = Faq_child1Fragment.newInstance();
                        setChildFragment(curFragment);
                        break;
                    case "포인트":
                        curFragment = Faq_child2Fragment.newInstance();
                        setChildFragment(curFragment);
                        break;
                    case "아이디어 채택":
                        curFragment = Faq_child3Fragment.newInstance();
                        setChildFragment(curFragment);
                        break;
                    case "창업 지원":
                        curFragment = Faq_child4Fragment.newInstance();
                        setChildFragment(curFragment);
                        break;
                    default:
                        curFragment = null;
                        break;
                }
            }

            private void setChildFragment(Fragment child){
                FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                if(!child.isAdded()){
                    fragmentTransaction.replace(R.id.child_fragment,child);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        return root;
    }
}