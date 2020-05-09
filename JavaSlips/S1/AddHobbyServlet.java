import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class AddHobbyServlet extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException,IOException{
		response.setContentType("text/html");
		PrintWriter out = res.getWriter();
		Cookie []c = request.getCookies();
		int id=1;
		if(c!=null) id = c.length+1;
		String value = request.getParameter("hobby");
		Cookie newCookie = new Cookie("Hobby:"+id,value);
		response.addCookie(newCookie);
		out.println("<h4>Cookie added with value "+value+"</h4>");
	}
}

