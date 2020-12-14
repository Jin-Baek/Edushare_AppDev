<%@page import="java.util.ArrayList"%>
<%@page import="com.db.*"%>
<%@page import="org.json.simple.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
   request.setCharacterEncoding("UTF-8");
   
   String returns = "";
   String article_title = request.getParameter("article_title");
   String article_content = request.getParameter("article_content");
   String article_writer = request.getParameter("article_writer");
   String article_number = request.getParameter("article_num");
   String category_name = request.getParameter("category_name");
   
%>
<%
	if(article_title != null){
		if(category_name.equals("A")){
			// 각 카테고리 정보 넣어주기
			System.out.println("값을 성공적으로 전달했습니다."); 
			Article_DBwrite article_dbwrite = Article_DBwrite.getWrite();
   			returns = article_dbwrite.write(article_title,article_content,article_writer,article_number,category_name);
   			out.println(returns);
		}
	}
	System.out.println("서버 시작");
%>