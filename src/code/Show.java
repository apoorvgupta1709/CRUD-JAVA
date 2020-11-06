package code;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Show")
public class Show extends HttpServlet {
    public Show() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		Connection con =null;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/apo?verifyServerCertificate=false&useSSL=true","root","1709");
			Statement ps=con.createStatement();
			String Querry="Select * from dataStore";
			ResultSet i=ps.executeQuery(Querry);
			pw.print("<h2>Record updated successful</h2> </br></br>");
			pw.print("<meta http-equiv=\"refresh\" content=\"3;url=Update.html”/>");
			while(i.next())
			{
				pw.print(i.getString(1)+ "\t" +i.getString(2)+ "\t"+i.getString(3)+ "\t"+i.getString(4)+ "</br>");
			}
		}
		catch(Exception e)
		{
			
		}
	}

}
