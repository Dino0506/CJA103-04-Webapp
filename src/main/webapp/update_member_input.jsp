<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.model.*"%>

<%
    // 取得由 MemberServlet.java 透過 getOne_For_Update 轉交過來的 memberVO
    MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>技師資料修改 - update_member_input.jsp</title>

<style>
  table#table-1 {
    background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
    width: 500px;
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
    width: 500px;
    background-color: white;
    margin-top: 5px;
    margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
  }
</style>
</head>
<body bgcolor='white'>

<table id="table-1">
    <tr><td>
         <h3>技師資料修改 - update_member_input.jsp</h3>
         <h4><a href="select_page.jsp">回首頁</a></h4>
    </td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
    <font style="color:red">請修正以下錯誤:</font>
    <ul>
        <c:forEach var="message" items="${errorMsgs}">
            <li style="color:red">${message}</li>
        </c:forEach>
    </ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member.do" name="form1">
<table>
    <tr>
        <td>技師編號:<font color=red><b>*</b></font></td>
        <td><%=memberVO.getTechId()%></td>
    </tr>
    <tr>
        <td>帳號:</td>
        <td><input type="TEXT" name="account" size="45" value="<%=memberVO.getAccount()%>" /></td>
    </tr>
    <tr>
        <td>姓名:</td>
        <td><input type="TEXT" name="name" size="45" value="<%=memberVO.getName()%>" /></td>
    </tr>
    <tr>
        <td>服務地址:</td>
        <td><input type="TEXT" name="address" size="45" value="<%=memberVO.getAddress()%>" /></td>
    </tr>
    <tr>
        <td>電話:</td>
        <td><input type="TEXT" name="phone" size="45" value="<%=memberVO.getPhone()%>" /></td>
    </tr>
    <tr>
        <td>狀態:</td>
        <td>
            <select size="1" name="status">
                <option value="0" <%=(memberVO.getStatus() == 0) ? "selected" : ""%>>正常</option>
                <option value="1" <%=(memberVO.getStatus() == 1) ? "selected" : ""%>>停權</option>
            </select>
        </td>
    </tr>
</table>

<br>
<%-- 送出修改所需的隱藏欄位 --%>
<input type="hidden" name="action" value="update">
<input type="hidden" name="techId" value="<%=memberVO.getTechId()%>">
<input type="submit" value="送出修改">
</FORM>

</body>
</html>