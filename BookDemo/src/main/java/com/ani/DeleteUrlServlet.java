package com.ani;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleteurl")
public class DeleteUrlServlet extends HttpServlet {

private static final String query = "Delete from BOOKDATA where id=?";
	
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		//get print writer
		PrintWriter pw = res.getWriter();
		//set content type
		res.setContentType("text/html"); 
		//get the id of record 
		int id = Integer.parseInt(req.getParameter("id"));
		
		//LOAD JDBC Driver
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		}catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		//generate the connection
		try(Connection con = DriverManager.getConnection("jdbc:mysql:///book", "root", "mca6"); 
				PreparedStatement ps = con.prepareStatement(query);) {
			ps.setInt(1, id);
			int count = ps.executeUpdate();
			if(count==1) {
				pw.println("<h2>Record is Deleted Secussfully</h2>");
			}else {
				pw.println("<h2>Record Not Deleted</h2>");
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


