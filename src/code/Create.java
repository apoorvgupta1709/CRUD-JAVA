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
@WebServlet("/Create")
public class Create extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public Create() {
		super();
		// TODO Auto-generated constructor stub
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
		try {
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
				pw.print("<h3><b>   Data Store Successful   </b></h3>");
				pw.print("<meta http-equiv=\"refresh\" content=\"3;url=FullForm.html”/>");
				request.getRequestDispatcher("Show.html").include(request, response);
				con.close();
			}else
			{
				pw.print("sorry unable to save data");
				con.close();
			}
		} catch (Exception e) {

			e.printStackTrace();
		}


	}



}
