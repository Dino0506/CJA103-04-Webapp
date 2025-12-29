<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.model.*"%>

<%
  MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
%>

<html>
<head>
<title>技師入駐 -Carshop</title>
<style>
    body { font-family: "Microsoft JhengHei", sans-serif; background-color: #f4f7f6; padding: 40px; }
    .form-container { 
        max-width: 550px; margin: auto; background: white; padding: 35px; 
        border-radius: 12px; border-top: 8px solid #27ae60; /* 綠色頂條 */
        box-shadow: 0 10px 25px rgba(0,0,0,0.05);
    }
    .header-box { text-align: center; margin-bottom: 30px; }
    .header-box h2 { color: #2c3e50; margin-bottom: 5px; }
    .header-box p { color: #7f8c8d; font-size: 14px; }
    .form-group { margin-bottom: 20px; }
    label { display: block; margin-bottom: 8px; color: #34495e; font-weight: bold; }
    input[type="text"], select { 
        width: 100%; padding: 12px; border: 1px solid #ddd; 
        border-radius: 6px; box-sizing: border-box; transition: 0.3s;
    }
    input[type="text"]:focus { border-color: #27ae60; outline: none; box-shadow: 0 0 5px rgba(39,174,96,0.2); }
    .submit-btn { 
        width: 100%; padding: 15px; background: #27ae60; color: white; 
        border: none; border-radius: 6px; cursor: pointer; font-size: 16px; font-weight: bold;
    }
    .submit-btn:hover { background: #219150; }
    .back-link { display: block; text-align: center; margin-top: 20px; color: #3498db; text-decoration: none; }
    .error-msg { color: #e74c3c; background: #fdf2f2; padding: 10px; border-radius: 5px; margin-bottom: 20px; font-size: 14px; }
</style>
</head>
<body>

<div class="form-container">
    <div class="header-box">
        <h2>🚚 技師入駐申請</h2>
        <p>請填寫基本資訊以建立新的技師檔案</p>
    </div>

    <c:if test="${not empty errorMsgs}">
        <div class="error-msg">
            <c:forEach var="message" items="${errorMsgs}"><div>• ${message}</div></c:forEach>
        </div>
    </c:if>

    <form method="post" action="<%=request.getContextPath()%>/member.do">
        <input type="hidden" name="memberNo" value="1">

        <div class="form-group">
            <label>技師真實姓名</label>
            <input type="text" name="realName" placeholder="例如：王小明" value="<%= (memberVO==null)? "" : memberVO.getRealName()%>">
        </div>

        <div class="form-group">
            <label>連絡電話</label>
            <input type="text" name="phone" placeholder="09xxxxxxxx" value="<%= (memberVO==null)? "" : memberVO.getPhone()%>">
        </div>

        <div class="form-group">
            <label>電子郵件 (Email)</label>
            <input type="text" name="email" placeholder="example@mail.com" value="<%= (memberVO==null)? "" : memberVO.getEmail()%>">
        </div>

        <div class="form-group">
            <label>服務地區</label>
            <input type="text" name="serviceArea" placeholder="例如：台北市、新北市" value="<%= (memberVO==null)? "" : memberVO.getServiceArea()%>">
        </div>

        <div class="form-group">
            <label>帳號啟動狀態</label>
            <select name="isActive">
                <option value="1">立即啟用</option>
                <option value="0">暫時停用</option>
            </select>
        </div>

        <input type="hidden" name="action" value="insert">
        <button type="submit" class="submit-btn">確認新增技師</button>
    </form>
    
    <a href="select_page.jsp" class="back-link">← 取消並返回查詢頁</a>
</div>

</body>
</html>