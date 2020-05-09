import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ViewHobbyServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException,IOException{
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		Cookie []c = request.getCookies();
		
		out.println("<table border=1><tr><th>Name</th><th>Value</th></tr>");
	
		for(int i=0;i<c.length;i++){
			out.println("<tr>"+
			"<td>"+c[i].getName()+"</td>"+
			"<td>"+c[i].getValue()+"</td>"+
			"</tr>");
		}

		out.println("</table>");
	}
}
