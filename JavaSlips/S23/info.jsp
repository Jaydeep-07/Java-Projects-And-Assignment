<%@page import="java.util.*"%>
Today is <% out.print(new java.util.Date());%><br>
<table border=1>
<tr>
	<th>Name</th><th>Value</th>
</tr>
<%
	Enumeration e = request.getHeaderNames();
	while(e.hasMoreElements()){
		String name = (String)e.nextElement();
		String value = request.getHeader(name);
%>
<tr>
	<td><%=name%></td>
	<td><%=value%></td>
</tr>
<%
	}
%>
</table>
<%
	Cookie c = new Cookie("test","Hello");
	response.addCookie(c);
%>
Cookie added successfully.<br>
Context Param:<%=application.getInitParameter("myparam")%><br>
Init Param:<%=confit.getInitParameter("myparam")%><br>


