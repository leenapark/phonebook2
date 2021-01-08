<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.javaex.dao.PhoneDao" %>
<%@ page import="com.javaex.vo.PersonVo" %>

<%

	int personId = Integer.parseInt(request.getParameter("id"));
	System.out.println(personId);	

	PhoneDao phoneDao = new PhoneDao();
	PersonVo personVo = phoneDao.getPerson(personId);	

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/phonebook2/pbc" method="get">
		이름(name):<input type="text" name="name" value="<%=personVo.getName()%>"><br>
		핸드폰(hp):<input type="text" name="hp" value="<%=personVo.getHp()%>"><br>
		회사(company):<input type="text" name="company" value="<%=personVo.getCompany()%>"><br>
		
		<!-- 아래 두 영역은 히든 처리로 노출하지 않는 부분 test 때만 text로 두고 확인한다 -->
		id: <input type="text" name="id" value="<%=personVo.getPersonId() %>">		
		action: <input type="text" name="action" value="update"><br>
		<button type="submit">등록</button>
	</form>
	<br>
	
</body>
</html>