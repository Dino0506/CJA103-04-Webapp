package com.controller;

import java.io.*;
import java.util.*;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import com.model.*;

@WebServlet("/member.do")
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
        // 1. 查詢單一技師資料 (來自 select_page.jsp)
        // =========================================================
        if ("getOne_For_Display".equals(action)) { 

            List<String> errorMsgs = new LinkedList<String>();
            req.setAttribute("errorMsgs", errorMsgs);

            /*************************** 1.接收請求參數 **************************************/
            String str = req.getParameter("techNo");
            if (str == null || (str.trim()).length() == 0) {
                errorMsgs.add("請輸入技師編號");
            }
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
                failureView.forward(req, res);
                return;
            }
            
            Integer techNo = null;
            try {
                techNo = Integer.valueOf(str);
            } catch (Exception e) {
                errorMsgs.add("技師編號格式不正確");
            }
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
                failureView.forward(req, res);
                return;
            }
            
            /*************************** 2.開始查詢資料 *****************************************/
            MemberService memberSvc = new MemberService();
            MemberVO memberVO = memberSvc.getOneMember(techNo);
            if (memberVO == null) {
                errorMsgs.add("查無此技師資料");
            }
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
                failureView.forward(req, res);
                return;
            }
            
            /*************************** 3.查詢完成,準備轉交 *************************************/
            req.setAttribute("memberVO", memberVO); 
            String url = "/listOneMember.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); 
            successView.forward(req, res);
        }

        // =========================================================
        // 2. 準備修改資料 (來自 listAllMember.jsp)
        // =========================================================
        if ("getOne_For_Update".equals(action)) { 

            Integer techNo = Integer.valueOf(req.getParameter("techNo"));
            
            MemberService memberSvc = new MemberService();
            MemberVO memberVO = memberSvc.getOneMember(techNo);
                            
            req.setAttribute("memberVO", memberVO);         
            String url = "/update_member_input.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }

        // =========================================================
        // 3. 執行修改資料 (來自 update_member_input.jsp)
        // =========================================================
        if ("update".equals(action)) { 
            
            List<String> errorMsgs = new LinkedList<String>();
            req.setAttribute("errorMsgs", errorMsgs);
        
            /*************************** 1.接收請求參數 **************************************/
            Integer techNo = Integer.valueOf(req.getParameter("techNo").trim());
            String realName = req.getParameter("realName").trim();
            if (realName == null || realName.length() == 0) {
                errorMsgs.add("技師姓名請勿空白");
            }
            String phone = req.getParameter("phone").trim();
            String email = req.getParameter("email").trim();
            String serviceArea = req.getParameter("serviceArea").trim();
            Integer isActive = Integer.valueOf(req.getParameter("isActive").trim());

            MemberVO memberVO = new MemberVO();
            memberVO.setTechNo(techNo);
            memberVO.setRealName(realName);
            memberVO.setPhone(phone);
            memberVO.setEmail(email);
            memberVO.setServiceArea(serviceArea);
            memberVO.setIsActive(isActive);

            if (!errorMsgs.isEmpty()) {
                req.setAttribute("memberVO", memberVO); 
                RequestDispatcher failureView = req.getRequestDispatcher("/update_member_input.jsp");
                failureView.forward(req, res);
                return; 
            }
            
            /*************************** 2.開始修改資料 *****************************************/
            MemberService memberSvc = new MemberService();
            memberVO = memberSvc.updateMember(techNo, realName, phone, email, serviceArea, isActive);
            
            /*************************** 3.修改完成,準備轉交 *************************************/
            req.setAttribute("memberVO", memberVO); 
            String url = "/listOneMember.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); 
            successView.forward(req, res);
        }

        // =========================================================
        // 4. 新增技師資料 (來自 addMember.jsp)
        // =========================================================
        if ("insert".equals(action)) { 
            
            List<String> errorMsgs = new LinkedList<String>();
            req.setAttribute("errorMsgs", errorMsgs);

            /*********************** 1.接收請求參數 *************************/
            // memberNo 在 JSP 中被設為隱藏欄位，預設為 1
            Integer memberNo = Integer.valueOf(req.getParameter("memberNo")); 
            String realName = req.getParameter("realName");
            if (realName == null || realName.trim().length() == 0) {
                errorMsgs.add("姓名請勿空白");
            }
            String phone = req.getParameter("phone");
            String email = req.getParameter("email");
            String serviceArea = req.getParameter("serviceArea");
            Integer isActive = Integer.valueOf(req.getParameter("isActive"));

            MemberVO memberVO = new MemberVO();
            memberVO.setMemberNo(memberNo);
            memberVO.setRealName(realName);
            memberVO.setPhone(phone);
            memberVO.setEmail(email);
            memberVO.setServiceArea(serviceArea);
            memberVO.setIsActive(isActive);

            if (!errorMsgs.isEmpty()) {
                req.setAttribute("memberVO", memberVO); 
                RequestDispatcher failureView = req.getRequestDispatcher("/addMember.jsp");
                failureView.forward(req, res);
                return;
            }
            
            /*************************** 2.開始新增資料 ***************************************/
            MemberService memberSvc = new MemberService();
            memberSvc.addMember(memberNo, realName, phone, email, serviceArea, isActive);
            
            /*************************** 3.新增完成,準備轉交 ***********************************/
            String url = "/listAllMember.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); 
            successView.forward(req, res);                
        }

        // =========================================================
        // 5. 刪除技師資料 (來自 listAllMember.jsp)
        // =========================================================
        if ("delete".equals(action)) { 
            Integer techNo = Integer.valueOf(req.getParameter("techNo"));
            MemberService memberSvc = new MemberService();
            memberSvc.deleteMember(techNo);
            String url = "/listAllMember.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }
    }
}