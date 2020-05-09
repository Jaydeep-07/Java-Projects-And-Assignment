<%@page import="java.sql.*,java.util.*"%>
<%
	Class.forName("org.postgresql.Driver");

	Connection con = DriverManager.getConnection(
	"jdbc:postgresql:ty1","postgres","");

	Set s = new TreeSet();

	while(true){
		int n = (int)(Math.random()*11+1);

		s.add(n);

		if(s.size()==5) break;
	}		

	PreparedStatement ps = con.prepareStatement("select * from questions where qid=?");
%>
<form method='post' action='accept_ans.jsp'>
<table width='70%' align='center'>
<%
	int i=0;
	Vector v = new Vector(s);
	session.setAttribute("qids",v);

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
