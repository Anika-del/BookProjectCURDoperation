package com.ani;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")

public class RegisterServlet extends HttpServlet {

	private static final String query = "INSERT INTO BOOKDATA(BOOKNAME,BOOKEDITION,BOOKPRICE) VALUES(?,?,?)";
	
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		doPost(req, res);
		//get print writer
		PrintWriter pw = res.getWriter();
		//set content type
		res.setContentType("text/html"); //like we click on register then add data into database
		
		//GET the BOOK info
		String bookName = req.getParameter("bookName");
		String bookEdition = req.getParameter("bookEdition");
		float bookPrice = Float.parseFloat(req.getParameter("bookPrice"));
		
		//LOAD JDBC Driver
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		}catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		//generate the connection
		try(Connection con = DriverManager.getConnection("jdbc:mysql:///book", "root", "mca6"); 
				PreparedStatement ps = con.prepareStatement(query);) {
			ps.setString(1, bookName);
			ps.setString(2, bookEdition);
			ps.setFloat(3, bookPrice);
			int count = ps.executeUpdate();
			if(count == 1) {
				pw.println("<h2>Record Register Sucessfully</h2>");
			}else {
				pw.println("<h2>Record not register</h2>");
			}
			
			
		}catch(SQLException se) {
			se.printStackTrace();
			pw.println("<h2>"+se.getMessage()+"</h2>");
		}catch(Exception e) {
			e.printStackTrace();
			pw.println("<h2>"+e.getMessage()+"</h2>");
		}
		
//		BACK TO HOME
		pw.println("<a href='index.html'>Back-To-Home</a>");
        pw.println("<br>");
        pw.println("<a href='bookList'>Book List</a>");
	}
	
	
	
	
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
}
	
}