package com.example.edushare.ui.categoryItem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edushare.R;

import java.util.ArrayList;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> implements OnArticleItemClickListener {

    ArrayList<ArticleForm> article_items = null;
    OnArticleItemClickListener listener;

    public ArticleAdapter(ArrayList<ArticleForm> list) {
        this.article_items = list;
    }

    @NonNull
    @Override
    public ArticleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.article_item,parent,false);
        return new ArticleAdapter.ViewHolder(itemView,this);
    }

    public void setOnItemClickListener(OnArticleItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onItemClick(ArticleAdapter.ViewHolder holder, View view, int position) {
        if(listener != null){
            listener.onItemClick(holder,view,position);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleAdapter.ViewHolder holder, int position) {
        ArticleForm item = article_items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return article_items.size();
    }

    // 아이템 추가
    public void addItem(ArticleForm item){
        article_items.add(item);
    }

    // 아이템들을 모아둔 리스트 설정
    public void setItems(ArrayList<ArticleForm> items){
        this.article_items=items;
    }

    // 아이템 가져오기
    public ArticleForm getItem(int position){
        return article_items.get(position);
    }

    // 아이템 설정하기
    public void setItem(int position,ArticleForm item){
        article_items.set(position,item);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView article_title;
        TextView article_writer;
        TextView article_time;

        public ViewHolder(@NonNull View itemView, final OnArticleItemClickListener listener) {
            super(itemView);

            // 아이템 클릭시 리스너 객체 메서드 호출 -> MainActivity 에서
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition(); // 뷰홀더에 표시할 아이템이 어댑터에서 몇 번째 인지 정보 = 아이템 인덱스 정보 를 반환
                    if(listener != null){
                        listener.onItemClick(ArticleAdapter.ViewHolder.this,view,position);
                    }
                }
            });
            article_title = itemView.findViewById(R.id.article_title);
            article_writer = itemView.findViewById(R.id.article_writer);
            article_time = itemView.findViewById(R.id.article_time);
        }

        public void setItem(ArticleForm item){
            article_title.setText(item.getArticle_title());
            article_writer.setText(item.getArticle_writer());
            article_time.setText(item.getArticle_time());
        }
    }
}
