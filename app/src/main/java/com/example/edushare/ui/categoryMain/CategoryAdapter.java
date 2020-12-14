package com.example.edushare.ui.categoryMain;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edushare.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> implements OnCategoryItemClickListener {

    ArrayList<Category> items = new ArrayList<Category>();
    OnCategoryItemClickListener listener;

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 인플레이션을 통해 뷰 객체 만들기
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.category_item,parent,false);
        // 뷰 홀더 객체를 생성하면서 뷰 객체를 전달하고 그 뷰 홀더 객체를 반환
        return new ViewHolder(itemView,this);
    }

    // 외부에서 리스너를 설정 할 수 있도록 메서드 추가하기 = OnPersonItemClickListener 객체 참조를 어댑터에 전달하는 메서드
    public void setOnItemClickListener(OnCategoryItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if(listener != null){
            listener.onItemClick(holder,view,position);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        Category item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    // 아이템 추가
    public void addItem(Category item){
        items.add(item);
    }

    // 아이템들을 모아둔 리스트 설정
    public void setItems(ArrayList<Category> items){
        this.items=items;
    }

    // 아이템 가져오기
    public Category getItem(int position){
        return items.get(position);
    }

    // 아이템 설정하기
    public void setItem(int position,Category item){
        items.set(position,item);
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView textView1;
        ImageView mainImage;

        // 생성자로 뷰 객체가 전달 받음 & 리스너를 생성자에 추가해서 뷰 객체 전달 받을 때 해당 인터페이스의 메서드 오버라이드 하기
        public ViewHolder(@NonNull View itemView, final OnCategoryItemClickListener listener) {
            super(itemView);

            // 아이템 클릭시 리스너 객체 메서드 호출 -> MainActivity 에서
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition(); // 뷰홀더에 표시할 아이템이 어댑터에서 몇 번째 인지 정보 = 아이템 인덱스 정보 를 반환
                    if(listener != null){
                        listener.onItemClick(ViewHolder.this,view,position);
                    }
                }
            });

            textView1 = itemView.findViewById(R.id.textView1);
            mainImage = itemView.findViewById(R.id.imageView);
        }

        public void setItem(Category item){
            textView1.setText(item.getName());
            mainImage.setImageResource(item.getImage());
        }
    }
}
