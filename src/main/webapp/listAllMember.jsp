<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*, com.model.*"%>
<%
    MemberService memberSvc = new MemberService();
    List<MemberVO> list = memberSvc.getAll();
    pageContext.setAttribute("list", list);
%>
<html>
<head>
<title>技師清單 - Carshop</title>
<style>
    body { font-family: "Microsoft JhengHei", sans-serif; background-color: #f4f7f6; padding: 20px; }
    .main-card { background: white; border-radius: 12px; box-shadow: 0 4px 10px rgba(0,0,0,0.1); padding: 20px; }
    .title-bar { display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px; }
    table { width: 100%; border-collapse: collapse; margin-top: 10px; }
    th { background-color: #2c3e50; color: white; padding: 12px; }
    td { padding: 10px; border-bottom: 1px solid #eee; text-align: center; }
    tr:hover { background-color: #f9f9f9; }
    .status-active { color: #27ae60; font-weight: bold; }
    .status-off { color: #e74c3c; font-weight: bold; }
    .btn-sm { padding: 5px 10px; border-radius: 4px; border: none; cursor: pointer; color: white; font-size: 12px; }
    .btn-edit { background-color: #3498db; }
    .btn-del { background-color: #e74c3c; }
</style>
</head>
<body>
<div class="main-card">
    <div class="title-bar">
        <h2>🛠️ 服務技師總覽</h2>
        <a href="select_page.jsp" style="text-decoration:none; color:#3498db;">回查詢頁</a>
    </div>

    <%@ include file="page1.file" %> 
    <table>
        <tr>
            <th>ID</th><th>姓名</th><th>電話</th><th>服務地區</th><th>評分</th><th>狀態</th><th>編輯</th><th>移除</th>
        </tr>
        <c:forEach var="memberVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
            <tr>
                <td>${memberVO.techNo}</td>
                <td><b>${memberVO.realName}</b></td>
                <td>${memberVO.phone}</td>
                <td>${memberVO.serviceArea}</td>
                <td><span style="color:#f1c40f;">★</span> ${memberVO.ratingAmount > 0 ? memberVO.ratingStar / memberVO.ratingAmount : '0'}</td>
                <td>
                    <c:choose>
                        <c:when test="${memberVO.isActive == 1}"><span class="status-active">● 啟用</span></c:when>
                        <c:otherwise><span class="status-off">● 停用</span></c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <form method="post" action="member.do" style="margin:0;">
                        <input type="hidden" name="techNo" value="${memberVO.techNo}">
                        <input type="hidden" name="action" value="getOne_For_Update">
                        <button type="submit" class="btn-sm btn-edit">修改</button>
                    </form>
                </td>
                <td>
                    <form method="post" action="member.do" style="margin:0;">
                        <input type="hidden" name="techNo" value="${memberVO.techNo}">
                        <input type="hidden" name="action" value="delete">
                        <button type="submit" class="btn-sm btn-del" onclick="return confirm('確定移除？')">刪除</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <%@ include file="page2.file" %>
</div>
</body>
</html>