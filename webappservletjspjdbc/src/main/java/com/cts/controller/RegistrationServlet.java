package com.cts.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/regserv")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     
    public RegistrationServlet() {
        super();
    }*/

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	    //get the parameter from jsp or html page
		String uname=request.getParameter("username");
	    String pwd=request.getParameter("password");
	    String emailid=request.getParameter("email");
	    
	    //jdbc code
	    String db_username="root";
	    String db_password="root";
	    String db_drivername="com.mysql.jdbc.Driver";
	    String db_url="jdbc:mysql://localhost:3306/adm017";
	    
	    	Connection connection=null;
	    	PreparedStatement pStatement=null;
	    	try {
	    		Class.forName(db_drivername);
	    		connection=DriverManager.getConnection(db_url,db_username,db_password);
	    		String insertQuery="insert into reg_table values(?,?,?)";
	    		pStatement=connection.prepareStatement(insertQuery);
	    		
	    		pStatement.setString(1, uname);
	    		pStatement.setString(2, pwd);
	    		pStatement.setString(3, emailid);
	    		
	    		pStatement.executeUpdate();
	    		System.out.println("Registration successfully completed.");
	    		response.getWriter().println("Registration successfully completed");
	    		
	    		
	    	}catch(Exception exception) {
	    		exception.printStackTrace();
	    		
	    	}finally {
	    		if(pStatement!=null) {
	    			try {
	    				pStatement.close();
	    			}catch(SQLException e) {
	    				e.printStackTrace();
	    			}
	    		}
	    		
	    	}
	    	
	    	response.getWriter().println("<br>");

	    //display the result on server using response object
	    response.getWriter().println("User Name: "+uname);
	    response.getWriter().println("Password: "+pwd);
	    response.getWriter().println("Email Id: "+emailid);
	}

}