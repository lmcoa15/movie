<%-- <%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"
 import="java.util.*,movie.admin.*"%>

<jsp:useBean id="datas" scope="request" class="java.util.ArrayList" />
<jsp:setProperty name="datas" property="*" />

<link rel="stylesheet" href="../css/movie_list_css.css" type="text/css" media="screen" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<%
	System.out.println("reservation.jsp 입장");		
	String user_id=request.getParameter("user_id"); //유저의 이름
	String films_id=request.getParameter("films_id"); //선택한 영화의 id
	String action=request.getParameter("action");
	int films_id2=Integer.parseInt(films_id); //선택한 영화의 id
%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>영화 예매 화면</title>
</head>
<body>
<h2>영화 예매 화면</h2>
<hr>
<form name=form1 method=post  action=film_reservation_control.jsp>

<table border="1">
	<tr><th>좌석 번호</th><th>좌석 현황</th>
	<th>예매</th>
	</tr>

	<%	
		//String id
		int count=1;
		for(seat_no ab:(ArrayList<seat_no>) datas){
	%>
	<tr>
	<td><%=count++%></td>
	<td><%=ab.get%></td>
	<td><%=ab.getSeat_num() %></td>
	<td><%=ab.getWatch_date() %></td>

	<input type=hidden name="action" value="delete">
 	<td><input type=Submit name="id_film" value="<%=ab.getId_film() %>"></td>

	</tr>
	<%
		}
	%>
</table>
</form>
</body>
</html> --%>