<%@page import="java.sql.*,java.util.*,java.text.*"%>
<%
	Class.forName("org.postgresql.Driver");

	Connection con = DriverManager.getConnection(
	"jdbc:postgresql:ty1","postgres","");

	Vector v = (Vector)session.getAttribute("answers");
	if(v==null){
%>
<h1>No questions answered</h1>
<%
		return;
	}

	PreparedStatement ps = con.prepareStatement("select ans from questions where qid=?");

	int tot=0;

	for(int i=0;i<v.size();i++){
		String str = v.get(i).toString();
		int j = str.indexOf(' ');
		int qno = Integer.parseInt(str.substring(0,j));
		int gans = Integer.parseInt(str.substring(j+1));

		ps.setInt(1,qno);
		
		ResultSet rs = ps.executeQuery();
		rs.next();

		int cans = rs.getInt(1);

		if(gans==cans) tot++;
	}

	session.removeAttribute("qids");
	session.removeAttribute("answers");
	session.removeAttribute("qid");
%>	
<h3>Score:<%=tot%></h1>
<center><a href='exam.jsp'>Restart</a></center>
</body>
