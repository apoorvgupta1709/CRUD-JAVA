package code;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Delete() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	
	{
		String a=request.getParameter("name");
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();

		Connection con =null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/apo?verifyServerCertificate=false&useSSL=true","root","1709");
			PreparedStatement ps=con.prepareStatement("delete from dataStore where email=?");
			ps.setString(1, request.getParameter("email"));
			int i=ps.executeUpdate();
			
			if(i>0)
			{
				response.setContentType("text/html");
				pw.print("Details Deleted Successful");
				request.getRequestDispatcher("Delete.html").include(request,response);
			}
			else
			{
				pw.print("Try Again! Details not exist in Table");
				request.getRequestDispatcher("Delete.html").include(request, response);
			}
			
		}
		catch(Exception e)
		{
			
		}

	
	
	}


}
