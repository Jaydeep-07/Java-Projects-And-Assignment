import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class BillServlet extends HttpServlet{
	private Connection con;
  	private PreparedStatement ps;
  	private ResultSet rs;

	public void init() 
  	throws ServletException{
    		try{
      			Class.forName("org.postgresql.Driver");
      			con = DriverManager.getConnection("jdbc:postgresql:bill",
							"postgres","");
    		}
    		catch(Exception e){}
  	}

  	public void doPost(HttpServletRequest request, HttpServletResponse response)
  	throws IOException,ServletException{
    		response.setContentType("text/html");

    		PrintWriter out = response.getWriter();

    		int bno = Integer.parseInt(request.getParameter("bno"));
	
		try{
			ps = con.prepareStatement("select * from BillMaster where billno=?");
			ps.setInt(1,bno);
			
			rs = ps.executeQuery();

			rs.next();

			out.print("<table border=1>"+
			"<tr><td><b>Bill No:</b></td><td>"+rs.getInt(1)+"</td>"+	
			"<td><b>Bill Date:</b></td><td>"+rs.getString(3)+"</td></tr>"+
			"<tr><td><b>Customer Name:</b></td><td>"+rs.getString(2)+"</td></tr>");
			out.print("<tr><th>Sr. No.</th><th>Item Name</th><th>Qty</th><th>Rate</th><th>Total</th></tr>");

			ps = con.prepareStatement("select * from BillDetails where billno=?");
			ps.setInt(1,bno);

			rs = ps.executeQuery();

			int i=1;
			float tot=0;

			while(rs.next()){
				float amt = rs.getInt(2)*rs.getFloat(3);

				out.print("<tr><td>"+i+"</td><td>"+
					rs.getString(1)+"</td><td>"+
					rs.getInt(2)+"</td><td>"+
					rs.getFloat(3)+"</td><td>"+
					amt+"</td></tr>");

				tot+=amt;
				i++;
			}
			out.print("<tr><td><b>Net Bill:</b></td><td>"+tot+"</td></tr>");
			out.print("</table>");		
		}catch(Exception e){}
	}
}
