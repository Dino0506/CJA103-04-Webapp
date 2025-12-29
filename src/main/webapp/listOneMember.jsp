<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.model.*"%>
<%-- 此頁練習採用 Script 的寫法取值 --%>

<%
  // 接收來自 MemberServlet.java 存入 req 的 memberVO 物件 [cite: 21]
  MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
%>

<html>
<head>
<title>技師資料 - listOneMember.jsp</title>

<style>
  table#table-1 {
    background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
    width: 600px;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
  table {
    width: 800px;
    background-color: white;
    margin-top: 5px;
    margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>技師資料 - listOneMember.jsp</h3>
		 <h4><a href="<%=request.getContextPath() %>/select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>技師編號</th>
		<th>帳號</th>
		<th>姓名</th>
		<th>服務地址</th>
		<th>電話</th>
		<th>狀態</th>
		<th>帳戶創建時間</th>
	</tr>
	<tr>
		<td><%=memberVO.getTechId()%></td>
		<td><%=memberVO.getAccount()%></td>
		<td><%=memberVO.getName()%></td>
		<td><%=memberVO.getAddress()%></td>
		<td><%=memberVO.getPhone()%></td>
		<td><%=(memberVO.getStatus() == 0) ? "正常" : "停權"%></td>
		<td><%=memberVO.getCreatedAt()%></td>
	</tr>
</table>

</body>
</html>