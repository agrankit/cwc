<%@ page import="java.util.List" %>
<%@ page import="com.ank.cwc.app.model.Match" %>
<%@ page import="com.google.appengine.api.datastore.KeyFactory" %>
<html>
<body>
	<h1>List if matches Cricket World Cup 2015</h1>
 
	Function : <a href="add">Add Match</a>
	<hr />
 
	<h2>All Customers</h2>
	<table border="1">
		<thead>
			<tr>
				<td>Team 1</td>
				<td>Team 2</td>
				<td>When</td>
				<td>Where</td>
				<td>Result</td>
			</tr>
		</thead>
 
		<%
 
		if(request.getAttribute("matchList")!=null){
 
			List<Match> matches = 
                           (List<Match>)request.getAttribute("matchList");
 
			if(!matches.isEmpty()){
				 for(Match c : matches){
 
		%>
				<tr>
				  <td><%=c.getTeam1() %></td>
				  <td><%=c.getTeam2() %></td>
				  <td><%=c.getWhen() %></td>
				  <td><%=c.getWhere() %></td>
				  <td><%=c.getResult() %></td>
				  <td><a href="update/<%=KeyFactory.keyToString(c.getId())%>">Update</a> | 
                                      <a href="delete/<%=KeyFactory.keyToString(c.getId()) %>">
                                       Delete</a>
                                  </td>
				</tr>
		<%	
 
				}
 
			}
 
		   }
		%>
 
        </tr>
 
	</table>
 
</body>
</html>