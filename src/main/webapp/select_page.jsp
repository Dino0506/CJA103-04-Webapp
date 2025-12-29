<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Carshop汽車技師後台管理</title>
<style>
    :root {
        --primary-dark: #2c3e50;
        --accent-orange: #f39c12;
        --bg-gray: #f8f9fa;
        --text-main: #34495e;
    }
    body { font-family: "Microsoft JhengHei", sans-serif; background-color: var(--bg-gray); margin: 0; display: flex; justify-content: center; align-items: center; min-height: 100vh; }
    .dashboard { width: 900px; background: white; border-radius: 15px; overflow: hidden; box-shadow: 0 15px 35px rgba(0,0,0,0.1); display: flex; }
    
    /* 左側側邊欄風格 */
    .sidebar { width: 300px; background: var(--primary-dark); color: white; padding: 40px 30px; }
    .sidebar h2 { color: var(--accent-orange); font-size: 24px; margin-bottom: 10px; }
    .sidebar p { font-size: 14px; opacity: 0.8; line-height: 1.6; }
    .brand-logo { font-size: 40px; margin-bottom: 20px; }

    /* 右側主內容區 */
    .main-content { flex: 1; padding: 40px; }
    .error-alert { background: #fee; color: #e74c3c; padding: 15px; border-radius: 8px; margin-bottom: 20px; font-size: 14px; border-left: 5px solid #e74c3c; }
    
    .card-grid { display: grid; grid-template-columns: 1fr 1fr; gap: 20px; margin-top: 20px; }
    .card { background: #fff; border: 1px solid #eee; padding: 20px; border-radius: 10px; transition: 0.3s; }
    .card:hover { transform: translateY(-5px); box-shadow: 0 5px 15px rgba(0,0,0,0.05); border-color: var(--accent-orange); }
    .card h3 { margin-top: 0; font-size: 18px; color: var(--primary-dark); display: flex; align-items: center; gap: 10px; }
    
    .btn-main { background: var(--primary-dark); color: white; padding: 10px 20px; border: none; border-radius: 5px; cursor: pointer; text-decoration: none; display: inline-block; transition: 0.3s; width: 100%; text-align: center; box-sizing: border-box; }
    .btn-main:hover { background: var(--accent-orange); }
    
    .query-form { margin-top: 15px; }
    .query-form select, .query-form input { width: 100%; padding: 10px; margin-bottom: 10px; border: 1px solid #ddd; border-radius: 5px; box-sizing: border-box; }
</style>
</head>
<body>

<div class="dashboard">
    <div class="sidebar">
        <div class="brand-logo">🏎️</div>
        <h2>CarShop</h2>
        <p>專業技師管理系統<br>CarShop Technician Hub</p>
        <hr style="border: 0; border-top: 1px solid rgba(255,255,255,0.1); margin: 30px 0;">
        <p style="font-size: 12px;">當前資料庫：cja103g1<br></p>
    </div>

    <div class="main-content">
        <c:if test="${not empty errorMsgs}">
            <div class="error-alert">
                <c:forEach var="message" items="${errorMsgs}"><div>⚠️ ${message}</div></c:forEach>
            </div>
        </c:if>

        <div class="card-grid">
            <div class="card" style="grid-column: span 2;">
                <h3>📊 數據總覽</h3>
                <p style="color: #7f8c8d; font-size: 14px;">快速查看所有合作技師的服務狀況與評價。</p>
                <a href="listAllMember.jsp" class="btn-main">進入技師列表清單</a>
            </div>

            <jsp:useBean id="memberSvc" scope="page" class="com.model.MemberService" />

            <div class="card">
                <h3>🔍 編號查詢</h3>
                <form method="post" action="member.do" class="query-form">
                    <input type="text" name="techNo" placeholder="輸入技師 ID">
                    <input type="hidden" name="action" value="getOne_For_Display">
                    <button type="submit" class="btn-main">執行查詢</button>
                </form>
            </div>

            <div class="card">
                <h3>👥 姓名選擇</h3>
                <form method="post" action="member.do" class="query-form">
                    <select name="techNo">
                        <c:forEach var="memberVO" items="${memberSvc.all}">
                            <option value="${memberVO.techNo}">${memberVO.realName}</option>
                        </c:forEach>
                    </select>
                    <input type="hidden" name="action" value="getOne_For_Display">
                    <button type="submit" class="btn-main">查看名片</button>
                </form>
            </div>

            <div class="card" style="grid-column: span 2; border-style: dashed; background: #fffcf5;">
                <h3>🛠️ 合作夥伴加盟</h3>
                <p style="color: #7f8c8d; font-size: 14px;">新的技師入駐請點擊下方按鈕進行資料建檔。</p>
                <a href="addMember.jsp" class="btn-main" style="background: #27ae60;">＋ 新增技師帳號</a>
            </div>
        </div>
    </div>
</div>

</body>
</html>