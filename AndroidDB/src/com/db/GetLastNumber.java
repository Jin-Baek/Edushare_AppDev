package com.db;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GetLastNumber {
	 private static GetLastNumber num = new GetLastNumber();

	   public static GetLastNumber getNum() {
	      return num;
	   }
	   
		String driver ="oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@000.000.00.00:0000:ORCL";
		String userId ="xxxxx";
		String userPw="xxxxx";

	   private String returns="";
	   private Connection conn = null;
	   private PreparedStatement pstmt = null;
	   private ResultSet rs = null;
	   private String sql;

	   public int returnNum() {
		   
		   int article_num = 0;
		   try {
	    	Class.forName(driver);
			conn = DriverManager.getConnection(url,userId,userPw);
			
			 sql = "SELECT ARTICLE_NUM FROM CATEGORY_ARTICLE WHERE ARTICLE_NUM =(SELECT MAX(ARTICLE_NUM) FROM CATEGORY_ARTICLE)";
	         pstmt = conn.prepareStatement(sql);
	         rs = pstmt.executeQuery();
	         while(rs.next()) {
		         article_num = rs.getInt("article_num");    
	         }
	      } catch (Exception e) {
	         e.printStackTrace();
	      } 

	      finally {
	    	  	if (pstmt != null) try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}
	  	    	if (conn != null) try {conn.close();} catch (SQLException e) {e.printStackTrace();}
	  	    	if (rs != null)try {rs.close();} catch (SQLException e) {e.printStackTrace();}
	      }
	      return article_num;
	   }
}
