package com.model;

import java.util.*;
import java.sql.*;

public class MemberJDBCDAO implements MemberDAO_interface {
    // 1. 資料庫連線設定
    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/cja103g1?serverTimezone=Asia/Taipei";
    String userid = "root";
    String passwd = "dinohung88"; 

    // 2. SQL 指令定義
    private static final String INSERT_STMT = 
        "INSERT INTO technician (member_no, real_name, phone, email, service_area, is_active) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String GET_ALL_STMT = 
        "SELECT * FROM technician ORDER BY tech_no";
    private static final String GET_ONE_STMT = 
        "SELECT * FROM technician WHERE tech_no = ?";
    private static final String DELETE = 
        "DELETE FROM technician WHERE tech_no = ?";
    private static final String UPDATE = 
        "UPDATE technician SET real_name=?, phone=?, email=?, service_area=?, is_active=? WHERE tech_no = ?";

    // 3. 載入資料庫驅動程式
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("無法載入資料庫驅動程式: " + e.getMessage());
        }
    }

    // 新增技師資料
    @Override
    public void insert(MemberVO memberVO) {
        // 使用 try-with-resources 自動關閉連線
        try (Connection con = DriverManager.getConnection(url, userid, passwd);
             PreparedStatement pstmt = con.prepareStatement(INSERT_STMT)) {

            pstmt.setInt(1, memberVO.getMemberNo());
            pstmt.setString(2, memberVO.getRealName());
            pstmt.setString(3, memberVO.getPhone());
            pstmt.setString(4, memberVO.getEmail());
            pstmt.setString(5, memberVO.getServiceArea());
            pstmt.setInt(6, memberVO.getIsActive());

            pstmt.executeUpdate();

        } catch (SQLException se) {
            throw new RuntimeException("資料庫新增報錯: " + se.getMessage());
        }
    }

    // 修改技師資料
    @Override
    public void update(MemberVO memberVO) {
        try (Connection con = DriverManager.getConnection(url, userid, passwd);
             PreparedStatement pstmt = con.prepareStatement(UPDATE)) {

            pstmt.setString(1, memberVO.getRealName());
            pstmt.setString(2, memberVO.getPhone());
            pstmt.setString(3, memberVO.getEmail());
            pstmt.setString(4, memberVO.getServiceArea());
            pstmt.setInt(5, memberVO.getIsActive());
            pstmt.setInt(6, memberVO.getTechNo()); // 指定要修改哪一位技師

            pstmt.executeUpdate();

        } catch (SQLException se) {
            throw new RuntimeException("資料庫修改報錯: " + se.getMessage());
        }
    }

    // 刪除技師資料
    @Override
    public void delete(Integer techNo) {
        try (Connection con = DriverManager.getConnection(url, userid, passwd);
             PreparedStatement pstmt = con.prepareStatement(DELETE)) {

            pstmt.setInt(1, techNo);
            pstmt.executeUpdate();

        } catch (SQLException se) {
            throw new RuntimeException("資料庫刪除報錯: " + se.getMessage());
        }
    }

    // 根據編號查詢一位技師
    @Override
    public MemberVO findByPrimaryKey(Integer techNo) {
        MemberVO memberVO = null;
        try (Connection con = DriverManager.getConnection(url, userid, passwd);
             PreparedStatement pstmt = con.prepareStatement(GET_ONE_STMT)) {

            pstmt.setInt(1, techNo);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // 將查詢結果封裝進 VO 物件
                    memberVO = new MemberVO();
                    memberVO.setTechNo(rs.getInt("tech_no"));
                    memberVO.setMemberNo(rs.getInt("member_no"));
                    memberVO.setRealName(rs.getString("real_name"));
                    memberVO.setPhone(rs.getString("phone"));
                    memberVO.setEmail(rs.getString("email"));
                    memberVO.setServiceArea(rs.getString("service_area"));
                    memberVO.setIsActive(rs.getInt("is_active"));
                    memberVO.setCreateAt(rs.getTimestamp("create_at"));
                    memberVO.setRatingAmount(rs.getInt("rating_amount"));
                    memberVO.setRatingStar(rs.getInt("rating_star"));
                }
            }
        } catch (SQLException se) {
            throw new RuntimeException("資料庫查詢單筆報錯: " + se.getMessage());
        }
        return memberVO;
    }

    // 查詢所有技師列表
    @Override
    public List<MemberVO> getAll() {
        List<MemberVO> list = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(url, userid, passwd);
             PreparedStatement pstmt = con.prepareStatement(GET_ALL_STMT);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                // 每跑一圈代表一筆資料，存入 List
                MemberVO memberVO = new MemberVO();
                memberVO.setTechNo(rs.getInt("tech_no"));
                memberVO.setMemberNo(rs.getInt("member_no"));
                memberVO.setRealName(rs.getString("real_name"));
                memberVO.setPhone(rs.getString("phone"));
                memberVO.setEmail(rs.getString("email"));
                memberVO.setServiceArea(rs.getString("service_area"));
                memberVO.setIsActive(rs.getInt("is_active"));
                memberVO.setCreateAt(rs.getTimestamp("create_at"));
                memberVO.setRatingAmount(rs.getInt("rating_amount"));
                memberVO.setRatingStar(rs.getInt("rating_star"));
                list.add(memberVO);
            }
        } catch (SQLException se) {
            throw new RuntimeException("資料庫查詢全體報錯: " + se.getMessage());
        }
        return list;
    }
}