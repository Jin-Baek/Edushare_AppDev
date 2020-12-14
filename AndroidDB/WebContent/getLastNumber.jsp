<%@page import="java.util.ArrayList"%>
<%@page import="com.db.*"%>
<%@page import="org.json.simple.*"%>
<%@page import="org.json.simple.parser.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	      
	 System.out.println("마지막 게시글 번호를 리턴합니다.");
	     
	 GetLastNumber num = GetLastNumber.getNum();
	 int number = num.returnNum();
	 
	 JSONParser jsonparser = new JSONParser();
	 
	 System.out.println(jsonparser.parse(number+""));
	 out.println(jsonparser.parse(number+""));
%>