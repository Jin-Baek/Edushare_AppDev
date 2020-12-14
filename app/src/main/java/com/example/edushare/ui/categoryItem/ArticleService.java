package com.example.edushare.ui.categoryItem;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ArticleService {

    @GET("/AndroidDB/Acategory_R.jsp")
    Call<List<ArticleForm>> getAArticleData();

    @GET("/AndroidDB/Bcategory_R.jsp")
    Call<List<ArticleForm>> getBArticleData();


    @GET("/AndroidDB/AllArticle_R.jsp")
    Call<List<ArticleForm>> getAllArticleData();

    @GET("/AndroidDB/getLastNumber.jsp")
    Call<String> getLastArticleNum();

    @FormUrlEncoded
    @POST("/AndroidDB/category_W.jsp")
    Call<String> putArticleData(@FieldMap HashMap<String,String> map);

}
