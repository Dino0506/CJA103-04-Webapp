<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.model.*"%>

<%-- 此頁練習採用 EL 的寫法取值 --%>
<%
    MemberService memberSvc = new MemberService();
    List<MemberVO> list = memberSvc.getAll();
    pageContext.setAttribute("list", list);
%>

<html>
<head>
<title>所有技師資料 - listAllMember.jsp</title>

<style>
  table#table-1 {
    background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
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
    width: 950px;
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

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有技師資料 - listAllMember.jsp</h3>
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
		<th>創建時間</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
    
	<%-- 引入分頁控制邏輯 --%>
	<%@ include file="page1.file" %> 
    
	<c:forEach var="memberVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr>
			<td>${memberVO.techId}</td>
			<td>${memberVO.account}</td>
			<td>${memberVO.name}</td>
			<td>${memberVO.address}</td>
			<td>${memberVO.phone}</td>
			<td>${memberVO.status == 0 ? "正常" : "停權"}</td>
			<td>${memberVO.createdAt}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="techId"  value="${memberVO.techId}">
			     <input type="hidden" name="action"	value="getOne_For_Update">
			  </FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="techId"  value="${memberVO.techId}">
			     <input type="hidden" name="action" value="delete">
			  </FORM>
			</td>
		</tr>
	</c:forEach>
</table>

<%-- 引入分頁按鈕控制 --%>
<%@ include file="page2.file" %>

</body>
</html>