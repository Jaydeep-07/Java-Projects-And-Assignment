import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class BillServlet extends HttpServlet{
  public void doPost(HttpServletRequest req, 
			HttpServletResponse res)
  throws ServletException,IOException{
    res.setContentType("text/html");
    PrintWriter out = res.getWriter();
    HttpSession session = req.getSession(true);

    Vector v = (Vector)session.getAttribute("list");
    int tot1 = Integer.parseInt(
		session.getAttribute("tot").toString());
    
    out.print("<h3>Page 1 products</h3>"+
 	"<ul>");
    for(int i=0;i<v.size();i++)
      out.print("<li>"+v.get(i)+"</li>");

    out.print("</ul><br>"+
    "Page 1 total = Rs."+tot1+"/-<br>");

    String vals[]=req.getParameterValues("prod");

    int tot2 = 0;

    out.print("<h3>Page 2 products</h3><ul>");

    for(int i=0;i<vals.length;i++){
      int j = vals[i].indexOf(" ");
      out.print("<li>"+vals[i].substring(0,j)+"</li>");
      tot2+=Integer.parseInt(vals[i].substring(j+1));
    }

    out.print("</ul><br>"+
    "Page 2 total = Rs."+tot2+"/-<br>"+
    "Total = Rs."+(tot1+tot2)+"/-");
  }
}






   










