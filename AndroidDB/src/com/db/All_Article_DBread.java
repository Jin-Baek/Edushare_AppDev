package com.db;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class All_Article_DBread {
	 private static All_Article_DBread article_dbread = new All_Article_DBread();

	   public static All_Article_DBread getBoard() {
	      return article_dbread;
	   }
	   
		String driver ="oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@112.220.19.35:1521:ORCL";
		String userId ="training";
		String userPw="training";

	   private String returns="";
	   private Connection conn = null;
	   private PreparedStatement pstmt = null;
	   private ResultSet rs = null;
	   private String sql;

	   public ArrayList<ArticleVO> selectAllArticle() {
		   ArrayList<ArticleVO> list = new ArrayList<ArticleVO>();
		   
		   try {
	    	Class.forName(driver);
			conn = DriverManager.getConnection(url,userId,userPw);
			
	         sql = "SELECT * FROM (SELECT * FROM CATEGORY_ARTICLE ORDER BY ARTICLE_NUM DESC) WHERE rownum<=5";
	         pstmt = conn.prepareStatement(sql);
	         rs = pstmt.executeQuery();

        	 SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
	         
	         
        	 while(rs.next()) {
        		 String article_title = rs.getString("article_title");
        		 String article_content = rs.getString("article_content");
        		 String article_writer = rs.getString("article_writer");
	        	 int article_num = rs.getInt("article_num");
	        	 Date date = rs.getDate("article_time"); 
	        	 String article_time = sdf.format(date);	 
	        	 String category_name = rs.getString("category_name");
	        	 
	        	 ArticleVO articleVO = new ArticleVO(article_title,article_content,article_writer,article_num,article_time,category_name);
	        	 list.add(articleVO);
	        	   	 
//returns +=rs.getString("article_title")+"\t"+rs.getString("article_content")+"\t"+rs.getString("article_writer")+"\t"+rs.getInt("article_num")+"\t"+article_time+"\t";
	         } 
	      } catch (Exception e) {
	         e.printStackTrace();
	      } 

	      finally {
	    	  	if (pstmt != null) try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}
	  	    	if (conn != null) try {conn.close();} catch (SQLException e) {e.printStackTrace();}
	  	    	if (rs != null)try {rs.close();} catch (SQLException e) {e.printStackTrace();}
	      }
	      return list;
	   }
}
