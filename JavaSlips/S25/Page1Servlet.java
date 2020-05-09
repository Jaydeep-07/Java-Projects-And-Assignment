import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class Page1Servlet extends HttpServlet{
  public void doPost(HttpServletRequest req, 
			HttpServletResponse res)
  throws ServletException,IOException{
    res.setContentType("text/html");
    PrintWriter out = res.getWriter();
    HttpSession session = req.getSession(true);

    String vals[] = req.getParameterValues("prod");

    Vector v = new Vector(); 

    int tot = 0;

    for(int i=0;i<vals.length;i++){
      int j = vals[i].lastIndexOf(" ");
      v.add(vals[i].substring(0,j));
      tot += Integer.parseInt(vals[i].substring(j+1));
    }

    session.setAttribute("list",v);
    session.setAttribute("tot",new Integer(tot));

    res.sendRedirect(
		"http://localhost:8080/ty1/page2.html");
  }
}    






   










