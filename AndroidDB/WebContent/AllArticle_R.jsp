<%@page import="java.util.ArrayList"%>
<%@page import="com.db.*"%>
<%@page import="org.json.simple.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	 // A 카테고리 정보 가져오기
	 ArrayList<ArticleVO> All_arrayList = new ArrayList<ArticleVO>();
	 JSONArray All_articleList = new JSONArray();
	      
	 System.out.println("모든 게시판을 리턴합니다.");
	     
	 All_Article_DBread allArticle = All_Article_DBread.getBoard();
	 All_arrayList = allArticle.selectAllArticle();
	      
	 for(int i=0;i<allArticle.selectAllArticle().size();i++){
		 ArticleVO articleVO = All_arrayList.get(i);
		 JSONObject sendObject = new JSONObject();
		    	 
		 sendObject.put("article_title",articleVO.getArticle_title());
		 sendObject.put("article_content",articleVO.getArticle_content());
		 sendObject.put("article_writer",articleVO.getArticle_writer());
		 sendObject.put("article_num",articleVO.getArticle_num());
		 sendObject.put("article_time",articleVO.getArticle_time());
		 sendObject.put("category_name",articleVO.getCategory_name());
		 
		 All_articleList.add(sendObject);
	 }
	     
	 System.out.println(All_articleList);
	 out.println(All_articleList);
%>