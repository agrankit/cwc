<%@ page import="com.ank.cwc.app.model.Match" %>
<%@ page import="com.google.appengine.api.datastore.KeyFactory" %>
<html>
<body>
	<h1>Update Match</h1>
 
	<%
		Match match = new Match();
 
		if(request.getAttribute("match")!=null){
 
			match = (Match)request.getAttribute("match");
 
		}
 
	%>
 
	<form method="post" action="../update" >
		<input type="hidden" name="id" id="id" 
			value="<%= KeyFactory.keyToString(match.getId()) %>"/> 
 
		<table>
			<tr>
				<td>
					Team1 :
				</td>
				<td>
					<input type="text" style="width: 185px;" 
                                             maxlength="30" name="team1" id="team1" 
						value="<%=match.getTeam1() %>" />
				</td>
			</tr>
			<tr>
				<td>
					Team2 :
				</td>
				<td>
					<input type="text" style="width: 185px;" 
                                             maxlength="30" name="team2" id="team2" 
						value="<%=match.getTeam2() %>" />
				</td>
			</tr>
			<tr>
				<td>
					When :
				</td>
				<td>
					<input type="text" style="width: 185px;" 
                                             maxlength="30" name="when" id="when" 
						value="<%=match.getWhen() %>" />
				</td>
			</tr>
			 <tr>
				<td>
					Where :
				</td>
				<td>
					<input type="text" style="width: 185px;" 
                                             maxlength="30" name="where" id="where" 
						value="<%=match.getWhere() %>" />
				</td>
			</tr>
			<tr>
				<td>
					Result :
				</td>
				<td>
					<input type="text" style="width: 185px;" 
                                             maxlength="30" name="result" id="result" 
						value="<%=match.getResult() %>" />
				</td>
			</tr>
		</table>
		<input type="submit" class="update" title="Update" value="Update" />
	</form>
 
</body>
</html>