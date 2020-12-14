package com.db;

import java.sql.Connection;

import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class Article_DBwrite {
	private static Article_DBwrite dbwrite = new Article_DBwrite();

	public static Article_DBwrite getWrite() {
		return dbwrite;
	}
	
	String driver ="oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@112.220.19.35:1521:ORCL";
	String userId ="training";
	String userPw="training";

	private String returns = "";
	private Connection conn = null;
	
	private PreparedStatement pstmt = null;
	//private ResultSet rs = null;
	
	private String sql="";

	public String write(String article_title,String article_content,String article_writer,String article_number,String category_name) {
		// article_number는 num의 형변환 전 임시 변수
	    try {
	    	Class.forName(driver);
			conn = DriverManager.getConnection(url,userId,userPw);
			
			System.out.println(article_title);
			System.out.println(article_content);
			System.out.println(article_writer);
			int article_num = Integer.parseInt(article_number);
			System.out.println(article_num);
	
			sql ="INSERT INTO Category_article VALUES(?,?,?,?,sysdate,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,article_title);
			pstmt.setString(2,article_content);
			pstmt.setString(3,article_writer);
			pstmt.setInt(4,article_num);
			pstmt.setString(5, category_name);
			pstmt.executeUpdate();
			returns = "success";
			
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
	    if (pstmt != null) try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}
	    if (conn != null) try {conn.close();} catch (SQLException e) {e.printStackTrace();}
	}   
	return returns;
       }
}
