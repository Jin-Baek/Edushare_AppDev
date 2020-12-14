
<%@page import="java.util.ArrayList"%>
<%@page import="com.db.*"%>
<%@page import="org.json.simple.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	// B 카테고리 정보 가져오기 
	ArrayList<ArticleVO> B_arrayList = new ArrayList<ArticleVO>();
	JSONArray B_articleList = new JSONArray();
		      
	System.out.println("B값을 리턴합니다.");
		     
	B_Article_DBread b_article_dbread = B_Article_DBread.getBoard();
	B_arrayList = b_article_dbread.select();
		      
	for(int i=0;i<b_article_dbread.select().size();i++){
		ArticleVO articleVO = B_arrayList.get(i);
		JSONObject sendObject = new JSONObject();
			    	 
		sendObject.put("article_title",articleVO.getArticle_title());
		sendObject.put("article_content",articleVO.getArticle_content());
		sendObject.put("article_writer",articleVO.getArticle_writer());
		sendObject.put("article_num",articleVO.getArticle_num());
		sendObject.put("article_time",articleVO.getArticle_time());
		sendObject.put("category_name",articleVO.getCategory_name());
			    	  
		B_articleList.add(sendObject);
	}
		    	  
	System.out.println(B_articleList);
	out.println(B_articleList);	    	  
%>		    	  