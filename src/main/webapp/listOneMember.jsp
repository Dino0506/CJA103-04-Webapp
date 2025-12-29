<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.model.*"%>
<% MemberVO memberVO = (MemberVO) request.getAttribute("memberVO"); %>
<html>
<head>
<title>技師名片 - Carshop</title>
<style>
    body { font-family: "Microsoft JhengHei", sans-serif; background-color: #f4f7f6; display: flex; justify-content: center; padding-top: 50px; }
    .profile-card { background: white; width: 400px; border-radius: 20px; overflow: hidden; box-shadow: 0 10px 25px rgba(0,0,0,0.1); }
    .banner { height: 100px; background: linear-gradient(135deg, #2c3e50, #f39c12); }
    .content { padding: 20px; text-align: center; }
    .avatar { width: 80px; height: 80px; background: #eee; border-radius: 50%; margin: -50px auto 10px; border: 5px solid white; display: flex; align-items: center; justify-content: center; font-size: 30px; }
    .info-row { display: flex; justify-content: space-between; padding: 10px 0; border-bottom: 1px solid #f9f9f9; text-align: left; }
    .label { color: #7f8c8d; font-size: 14px; }
    .back-btn { display: inline-block; margin-top: 20px; color: #3498db; text-decoration: none; }
</style>
</head>
<body>
<div class="profile-card">
    <div class="banner"></div>
    <div class="content">
        <div class="avatar">👨‍🔧</div>
        <h2 style="margin:5px 0;">${memberVO.realName}</h2>
        <p style="color:#7f8c8d; font-size:12px;">技術人員 ID: ${memberVO.techNo}</p>
        
        <div class="info-row"><span class="label">連絡電話</span><span>${memberVO.phone}</span></div>
        <div class="info-row"><span class="label">電子郵件</span><span>${memberVO.email}</span></div>
        <div class="info-row"><span class="label">服務區域</span><span>${memberVO.serviceArea}</span></div>
        <div class="info-row"><span class="label">目前狀態</span><span>${memberVO.isActive == 1 ? "✅ 啟用" : "❌ 停用"}</span></div>
        
        <a href="select_page.jsp" class="back-btn">← 返回後台首頁</a>
    </div>
</div>
</body>
</html>