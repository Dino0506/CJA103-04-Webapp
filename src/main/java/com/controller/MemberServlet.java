package com.controller; // 依結構圖修改 

import java.io.*;
import java.util.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import com.model.*; // 依結構圖修改，引用 com.model 下的 VO 與 Service 

@WebServlet("/member.do") // 映射路徑為 /member.do [cite: 31, 32]
public class MemberServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		// =========================================================
		// 1. 來自 select_page.jsp 的單一查詢請求
		// =========================================================
		if ("getOne_For_Display".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			String str = req.getParameter("techId");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入技師編號");
			}
			
			if (!errorMsgs.isEmpty()) {
				// 根據結構圖，select_page.jsp 在根目錄 
				RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
				failureView.forward(req, res);
				return;
			}
			
			Integer techId = null;
			try {
				techId = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("技師編號格式不正確");
			}
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
				failureView.forward(req, res);
				return;
			}
			
			/***************************2.開始查詢資料*****************************************/
			MemberService memberSvc = new MemberService();
			MemberVO memberVO = memberSvc.getOneMember(techId);
			if (memberVO == null) {
				errorMsgs.add("查無資料");
			}
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
				failureView.forward(req, res);
				return;
			}
			
			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
			req.setAttribute("memberVO", memberVO); 
			String url = "/listOneMember.jsp"; // 轉交至根目錄下的 listOneMember.jsp [cite: 20]
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		}
		
		// =========================================================
		// 2. 來自 listAllMember.jsp 的修改請求 (抓取單筆資料供修改)
		// =========================================================
		if ("getOne_For_Update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/***************************1.接收請求參數****************************************/
			Integer techId = Integer.valueOf(req.getParameter("techId"));
			
			/***************************2.開始查詢資料****************************************/
			MemberService memberSvc = new MemberService();
			MemberVO memberVO = memberSvc.getOneMember(techId);
							
			/***************************3.查詢完成,準備轉交(Send the Success view)************/
			req.setAttribute("memberVO", memberVO);         
			String url = "/update_member_input.jsp"; // 指向根目錄 
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		// =========================================================
		// 3. 來自 update_member_input.jsp 的送出修改
		// =========================================================
		if ("update".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			Integer techId = Integer.valueOf(req.getParameter("techId").trim());
			
			String account = req.getParameter("account").trim();
			if (account == null || account.length() == 0) {
				errorMsgs.add("帳號請勿空白");
			}

			String name = req.getParameter("name").trim();
			if (name == null || name.length() == 0) {
				errorMsgs.add("姓名請勿空白");
			}
				
			String address = req.getParameter("address").trim();
			String phone = req.getParameter("phone").trim();
			Integer status = Integer.valueOf(req.getParameter("status").trim());

			MemberVO memberVO = new MemberVO();
			memberVO.setTechId(techId);
			memberVO.setAccount(account);
			memberVO.setName(name);
			memberVO.setAddress(address);
			memberVO.setPhone(phone);
			memberVO.setStatus(status);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("memberVO", memberVO); 
				RequestDispatcher failureView = req.getRequestDispatcher("/update_member_input.jsp");
				failureView.forward(req, res);
				return; 
			}
			
			/***************************2.開始修改資料*****************************************/
			MemberService memberSvc = new MemberService();
			memberVO = memberSvc.updateMember(techId, account, name, address, phone, status);
			
			/***************************3.修改完成,準備轉交(Send the Success view)*************/
			req.setAttribute("memberVO", memberVO); 
			String url = "/listOneMember.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		}

		// =========================================================
		// 4. 來自 addMember.jsp 的新增請求
		// =========================================================
        if ("insert".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			String account = req.getParameter("account");
			String name = req.getParameter("name");
			String address = req.getParameter("address");
			String phone = req.getParameter("phone");
			Integer status = 0;
			try {
				status = Integer.valueOf(req.getParameter("status"));
			} catch (Exception e) {
				status = 0;
			}

			MemberVO memberVO = new MemberVO();
			memberVO.setAccount(account);
			memberVO.setName(name);
			memberVO.setAddress(address);
			memberVO.setPhone(phone);
			memberVO.setStatus(status);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("memberVO", memberVO); 
				RequestDispatcher failureView = req.getRequestDispatcher("/addMember.jsp");
				failureView.forward(req, res);
				return;
			}
			
			/***************************2.開始新增資料***************************************/
			MemberService memberSvc = new MemberService();
			memberVO = memberSvc.addMember(account, name, address, phone, status);
			
			/***************************3.新增完成,準備轉交(Send the Success view)***********/
			String url = "/listAllMember.jsp"; // 轉交回所有技師列表 [cite: 4]
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);				
		}
		
		// =========================================================
		// 5. 來自 listAllMember.jsp 的刪除請求
		// =========================================================
		if ("delete".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
			/***************************1.接收請求參數***************************************/
			Integer techId = Integer.valueOf(req.getParameter("techId"));
			
			/***************************2.開始刪除資料***************************************/
			MemberService memberSvc = new MemberService();
			memberSvc.deleteMember(techId);
			
			/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
			String url = "/listAllMember.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
	}
}