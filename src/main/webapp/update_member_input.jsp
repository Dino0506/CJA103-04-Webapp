<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.model.*"%>

<%
    MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
%>

<html>
<head>
<title>資料維護 - Carshop</title>
<style>
    body { font-family: "Microsoft JhengHei", sans-serif; background-color: #f4f7f6; padding: 40px; }
    .form-container { 
        max-width: 550px; margin: auto; background: white; padding: 35px; 
        border-radius: 12px; border-top: 8px solid #3498db; /* 藍色頂條 */
        box-shadow: 0 10px 25px rgba(0,0,0,0.05);
    }
    .header-box { text-align: center; margin-bottom: 30px; }
    .header-box h2 { color: #2c3e50; margin-bottom: 5px; }
    .static-info { background: #f8f9fa; padding: 15px; border-radius: 6px; margin-bottom: 25px; border-left: 4px solid #bdc3c7; }
    .form-group { margin-bottom: 20px; }
    label { display: block; margin-bottom: 8px; color: #34495e; font-weight: bold; }
    input[type="text"], select { 
        width: 100%; padding: 12px; border: 1px solid #ddd; 
        border-radius: 6px; box-sizing: border-box; transition: 0.3s;
    }
    input[type="text"]:focus { border-color: #3498db; outline: none; box-shadow: 0 0 5px rgba(52,152,219,0.2); }
    .submit-btn { 
        width: 100%; padding: 15px; background: #3498db; color: white; 
        border: none; border-radius: 6px; cursor: pointer; font-size: 16px; font-weight: bold;
    }
    .submit-btn:hover { background: #2980b9; }
    .back-link { display: block; text-align: center; margin-top: 20px; color: #7f8c8d; text-decoration: none; }
</style>
</head>
<body>

<div class="form-container">
    <div class="header-box">
        <h2>⚙️ 技師資料維護</h2>
        <p>更新技師的服務資訊與帳號狀態</p>
    </div>

    <div class="static-info">
        <div style="font-size: 14px; color: #7f8c8d;">技師流水號：<b style="color:#2c3e50;"><%=memberVO.getTechNo()%></b></div>
        <div style="font-size: 14px; color: #7f8c8d;">關聯會員號：<b style="color:#2c3e50;"><%=memberVO.getMemberNo()%></b></div>
    </div>

    <form method="post" action="<%=request.getContextPath()%>/member.do">
        <div class="form-group">
            <label>技師姓名</label>
            <input type="text" name="realName" value="<%=memberVO.getRealName()%>">
        </div>

        <div class="form-group">
            <label>連絡電話</label>
            <input type="text" name="phone" value="<%=memberVO.getPhone()%>">
        </div>

        <div class="form-group">
            <label>電子郵件</label>
            <input type="text" name="email" value="<%=memberVO.getEmail()%>">
        </div>

        <div class="form-group">
            <label>服務地區</label>
            <input type="text" name="serviceArea" value="<%=memberVO.getServiceArea()%>">
        </div>

        <div class="form-group">
            <label>帳號狀態</label>
            <select name="isActive">
                <option value="1" <%=(memberVO.getIsActive() == 1) ? "selected" : ""%>>啟用中 (Active)</option>
                <option value="0" <%=(memberVO.getIsActive() == 0) ? "selected" : ""%>>已停權 (Inactive)</option>
            </select>
        </div>

        <input type="hidden" name="techNo" value="<%=memberVO.getTechNo()%>">
        <input type="hidden" name="action" value="update">
        <button type="submit" class="submit-btn">儲存變更內容</button>
    </form>
    
    <a href="listAllMember.jsp" class="back-link">← 放棄修改並返回列表</a>
</div>

</body>
</html>