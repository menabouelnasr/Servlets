import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Servlet implementation class Details	
 */
@WebServlet("/Details")
public class Details extends HttpServlet {
	static Connection conn;
	static String address, postalCode,num, credit, city, state, output="", custID;
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Details() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
         response.setContentType("text/html");
         out.println("<html><body>");
         try {
        	//URL of Oracle database server
        	 
             String url = "jdbc:oracle:thin:testuser/password@localhost"; 
             Class.forName("oracle.jdbc.driver.OracleDriver");
             
             //properties for creating connection to Oracle database
             Properties props = new Properties();
             props.setProperty("user", "testdb");
             props.setProperty("password", "password");
           
             //creating connection to Oracle database using JDBC
             try {
     			conn = DriverManager.getConnection(url,props);
     		} catch (SQLException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		}
             custID= request.getParameter("customerID");
             System.out.println(custID);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("select * from Demo_Customers where customer_ID=" + custID);     
             System.out.println(rs);
           
         output+="<table border=2 color=white bgcolor=lightgray>";
         output+="<tr><th>Address </th><th>City</th><th>State</th><th>Postal Code</th><th> Phone Number </th><th> Credit Limit</th></tr> ";
         
         boolean a=rs.next();
        	 System.out.println(a);
         	address= rs.getString("CUST_Street_Address1");
        	 city= rs.getString("Cust_City");
        	 state= rs.getString("CUST_STATE");
        	 postalCode = rs.getString("CUST_Postal_Code"); 
        	 num=rs.getString("PHONE_NUMBER1");
        	 credit=rs.getString("Credit_Limit");
        	
             output+= "<tr><td>" + address +"</td><td>"+ city + "</td><td>" + state + "</td><td>" + postalCode +"</td><td>" + num +"</td><td>" + credit +"</td></tr>"; 
     
             System.out.println("hgk"+output);
         
         
         conn.close();
        }
         catch (Exception e) {
         out.println("error");
     }
         
         request.setAttribute("message", output);
	     getServletContext().getRequestDispatcher("/Output.jsp").forward(request,response);
	     output="";
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
