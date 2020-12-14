package com.example.edushare.ui.categoryItem;

import java.io.Serializable;

public class ArticleForm implements Serializable {
    String article_title;
    String article_content;
    String article_writer;
    int article_num;
    String article_time;
    String category_name;

    public ArticleForm(String article_title, String article_content, String article_writer, int article_num, String article_time, String category_name) {
        this.article_title = article_title;
        this.article_content = article_content;
        this.article_writer = article_writer;
        this.article_num = article_num;
        this.article_time = article_time;
        this.category_name = category_name;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getArticle_content() {
        return article_content;
    }

    public void setArticle_content(String article_content) {
        this.article_content = article_content;
    }

    public String getArticle_title() {
        return article_title;
    }

    public void setArticle_title(String article_title) {
        this.article_title = article_title;
    }

    public String getArticle_writer() {
        return article_writer;
    }

    public void setArticle_writer(String article_writer) {
        this.article_writer = article_writer;
    }

    public int getArticle_num() {
        return article_num;
    }

    public void setArticle_num(int article_num) {
        this.article_num = article_num;
    }

    public String getArticle_time() {
        return article_time;
    }

    public void setArticle_time(String article_time) {
        this.article_time = article_time;
    }
}
