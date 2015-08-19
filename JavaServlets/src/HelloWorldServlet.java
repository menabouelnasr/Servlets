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
 * Servlet implementation class HelloWorldServlet
 */
@WebServlet("/HelloWorldServlet")
public class HelloWorldServlet extends HttpServlet {
	static Connection conn;
	static String First_Name, Last_Name, id, output="";
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloWorldServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 /*PrintWriter out = response.getWriter();
	     out.println("Hello World");
	     request.setAttribute("message", "Hello World");
	     getServletContext().getRequestDispatcher("/Output.jsp").forward(request,response);*/
		
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
           
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("select * from Demo_Customers");
//             while (rs.next()) {
//                 name = rs.getString("CUST_FIRST_NAME"); 
//                 Lname= rs.getString("CUST_LAST_NAME");
//                 out.println(name); 
//                 out.println(Lname);
//                 Names= out.println("<tr><td>" + name + "</td><td>" + Lname + "</td><td>");
//                 //names.add(name);
//             }
//             conn.close();
//            }
//             catch (Exception e) {
//             out.println("error");
//             e.printStackTrace();
//         }
//         
//         
           
         output+="<table border=2 color=white bgcolor=black>";
         output+="<tr><th>Customer ID </th><th>First Name</th><th>Last Name</th></tr>";
         while (rs.next()) {
        	 id= rs.getString("CUSTOMER_ID");
        	 First_Name = rs.getString("CUST_FIRST_NAME"); 
             Last_Name= rs.getString("CUST_LAST_NAME");

             output+= "<tr><td>"+id+"</td><td><a href= Details?customerID= "+ id + ">"+ First_Name + "</a></td><td>" + Last_Name + "</td></tr>"; 
         }
         
         
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
