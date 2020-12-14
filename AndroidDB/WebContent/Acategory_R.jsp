<%@page import="java.util.ArrayList"%>
<%@page import="com.db.*"%>
<%@page import="org.json.simple.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	 // A 카테고리 정보 가져오기
	 ArrayList<ArticleVO> A_arrayList = new ArrayList<ArticleVO>();
	 JSONArray A_articleList = new JSONArray();
	      
	 System.out.println("A값을 리턴합니다.");
	     
	 A_Article_DBread a_article_dbread = A_Article_DBread.getBoard();
	 A_arrayList = a_article_dbread.select();
	      
	 for(int i=0;i<a_article_dbread.select().size();i++){
		 ArticleVO articleVO = A_arrayList.get(i);
		 JSONObject sendObject = new JSONObject();
		    	 
		 sendObject.put("article_title",articleVO.getArticle_title());
		 sendObject.put("article_content",articleVO.getArticle_content());
		 sendObject.put("article_writer",articleVO.getArticle_writer());
		 sendObject.put("article_num",articleVO.getArticle_num());
		 sendObject.put("article_time",articleVO.getArticle_time());
		 sendObject.put("category_name",articleVO.getCategory_name());
		 
		 A_articleList.add(sendObject);
	 }
	     
	 System.out.println(A_articleList);
	 out.println(A_articleList);
%>