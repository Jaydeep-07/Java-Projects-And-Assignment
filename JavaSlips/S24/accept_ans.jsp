<%@page import="java.sql.*,java.util.*"%>
<%
	Class.forName("org.postgresql.Driver");

	Connection con = DriverManager.getConnection(
	"jdbc:postgresql:ty1","postgres","");

	Vector answers = (Vector)session.getAttribute("answers");

	if(answers==null)
		answers = new Vector();

	int qno = Integer.parseInt(request.getParameter("qno"));
	int ans = Integer.parseInt(request.getParameter("op"));
	int i = Integer.parseInt(request.getParameter("qid"));

	answers.add(qno+" "+ans);

	session.setAttribute("answers",answers);

	String ok = request.getParameter("ok");

	if(ok.equals("Submit") || i==5){
		response.sendRedirect("result.jsp");
		return;
	}	
	
	PreparedStatement ps = con.prepareStatement("select * from questions where qid=?");
%>
<form method='post' action='accept_ans.jsp'>
<table width='70%' align='center'>
<%
	Vector v = (Vector)session.getAttribute("qids");

	int qid = Integer.parseInt(v.get(i).toString());
	ps.setInt(1,qid);
	ResultSet rs = ps.executeQuery();
	rs.next();
%>
<tr>
<td><b>Question:<%=i+1%></b></td>
</tr>
<tr>
<td><pre><b><%=rs.getString(2)%></pre></b></td>
</tr>
<tr>
<td>
<b>
<input type='radio' name='op' value=1><%=rs.getString(3)%><br>
<input type='radio' name='op' value=2><%=rs.getString(4)%><br>
<input type='radio' name='op' value=3><%=rs.getString(5)%><br>
<input type='radio' name='op' value=4><%=rs.getString(6)%><br><br>
</b>
</td>
</tr>
<tr>
	<td align='center'>
	<input type='submit' value='Next' name='ok'>
	<input type='submit' value='Submit' name='ok'>
	</td>
</tr>
</table>
<input type='hidden' name='qno' value=<%=qid%>>
<input type='hidden' name='qid' value=<%=i+1%>>
</form>
</body>
