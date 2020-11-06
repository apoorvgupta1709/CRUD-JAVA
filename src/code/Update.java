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

@WebServlet("/Update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Update() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		String a=request.getParameter("name");
		String b=request.getParameter("age");
		String c=request.getParameter("email");
		String d=request.getParameter("password");

		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		Connection con =null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/apo?verifyServerCertificate=false&useSSL=true","root","1709");
			PreparedStatement ps=con.prepareStatement("insert into dataStore values(?,?,?,?)");
			ps.setString(1, a);
			ps.setString(2, b);
			ps.setString(3, c);
			ps.setString(4, d);

			int i=ps.executeUpdate();
			if(i>0)
			{
				response.setContentType("text/html");
				pw.print("<h3><b>   Record update Successful   </b></h3>");
				
				pw.print("<meta http-equiv=\"refresh\" content=\"3;url=Update.html”/>");
				request.getRequestDispatcher("Update.html").include(request, response);
				con.close();
			}
			else
			{
				response.setContentType("text/html");
				pw.print("<h3><b>   Update failed   </b></h3>");
				
				pw.print("<meta http-equiv=\"refresh\" content=\"3;url=Update.html”/>");
				request.getRequestDispatcher("Update.html").include(request, response);
				con.close();
			}
				
			
		}
		catch(Exception e)
		{
			
		}
		
		
		
	}

}
