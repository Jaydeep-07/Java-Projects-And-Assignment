import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HitCountServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res)
	throws ServletException,IOException{
		res.setContentType("text/html");

		PrintWriter out = res.getWriter();

		int count=1;

		Cookie c[] = req.getCookies();

		if(c==null)
		{
			Cookie newCookie = new Cookie("count","1");
			res.addCookie(newCookie);
			
			out.print("<h1>Welcome Servlet</h1>");
		}
		else
		{
			count = Integer.parseInt(c[0].getValue())+1;
			c[0].setValue(Integer.toString(count));
			res.addCookie(c[0]);
		}

		out.print("Hit Count:"+count);
	}
}
