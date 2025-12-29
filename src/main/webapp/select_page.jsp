<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Momento 汽車專題: 技師管理首頁</title>

<style>
  table#table-1 {
    width: 450px;
    background-color: #CCCCFF;
    margin-top: 5px;
    margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>Momento 汽車專題</h3><h4>( 技師管理首頁 )</h4></td></tr>
</table>

<p>這是在 webapp 根目錄下的技師管理首頁</p>

<h3>資料查詢:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <%-- 列出所有技師 --%>
  <li><a href='listAllMember.jsp'>List</a> all Members. <br><br></li>
  
  <li>
    <%-- 透過輸入編號查詢 --%>
    <FORM METHOD="post" ACTION="<%=request.getContextPath() %>/member.do" >
        <b>輸入技師編號 (如 1):</b>
        <input type="text" name="techId" >
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <%-- 根據結構圖，MemberService 位於 com.model 套件下 --%>
  <jsp:useBean id="memberSvc" scope="page" class="com.model.MemberService" />
   
  <li>
     <%-- 透過下拉選單選擇編號查詢 --%>
     <FORM METHOD="post" ACTION="<%=request.getContextPath() %>/member.do" >
       <b>選擇技師編號:</b>
       <select size="1" name="techId">
         <c:forEach var="memberVO" items="${memberSvc.all}" > 
          <option value="${memberVO.techId}">${memberVO.techId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <%-- 透過下拉選單選擇姓名查詢 --%>
     <FORM METHOD="post" ACTION="<%=request.getContextPath() %>/member.do" >
       <b>選擇技師姓名:</b>
       <select size="1" name="techId">
         <c:forEach var="memberVO" items="${memberSvc.all}" > 
          <option value="${memberVO.techId}">${memberVO.name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>

<h3>技師管理</h3>

<ul>
  <li><a href='addMember.jsp'>Add</a> a new Member.</li>
</ul>

</body>
</html>