package com.model; // 根據結構圖修改封裝路徑

import java.util.*;
import java.sql.*;

public class MemberJDBCDAO implements MemberDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/db01?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "dinohung88"; // 請替換成您的資料庫密碼

	// SQL 指令完全對應 MEMBER 資料表結構
	private static final String INSERT_STMT = 
		"INSERT INTO MEMBER (ACCOUNT, NAME, ADDRESS, PHONE, STATUS) VALUES (?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT TECH_ID, ACCOUNT, NAME, ADDRESS, PHONE, STATUS, CREATED_AT FROM MEMBER order by TECH_ID";
	private static final String GET_ONE_STMT = 
		"SELECT TECH_ID, ACCOUNT, NAME, ADDRESS, PHONE, STATUS, CREATED_AT FROM MEMBER where TECH_ID = ?";
	private static final String DELETE = 
		"DELETE FROM MEMBER where TECH_ID = ?";
	private static final String UPDATE = 
		"UPDATE MEMBER set ACCOUNT=?, NAME=?, ADDRESS=?, PHONE=?, STATUS=? where TECH_ID = ?";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		}
	}

	@Override
	public void insert(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, memberVO.getAccount());
			pstmt.setString(2, memberVO.getName());
			pstmt.setString(3, memberVO.getAddress());
			pstmt.setString(4, memberVO.getPhone());
			pstmt.setInt(5, memberVO.getStatus());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try { pstmt.close(); } catch (SQLException se) { se.printStackTrace(System.err); }
			}
			if (con != null) {
				try { con.close(); } catch (Exception e) { e.printStackTrace(System.err); }
			}
		}
	}

	@Override
	public void update(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, memberVO.getAccount());
			pstmt.setString(2, memberVO.getName());
			pstmt.setString(3, memberVO.getAddress());
			pstmt.setString(4, memberVO.getPhone());
			pstmt.setInt(5, memberVO.getStatus());
			pstmt.setInt(6, memberVO.getTechId());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try { pstmt.close(); } catch (SQLException se) { se.printStackTrace(System.err); }
			}
			if (con != null) {
				try { con.close(); } catch (Exception e) { e.printStackTrace(System.err); }
			}
		}
	}

	@Override
	public void delete(Integer techId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, techId);

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try { pstmt.close(); } catch (SQLException se) { se.printStackTrace(System.err); }
			}
			if (con != null) {
				try { con.close(); } catch (Exception e) { e.printStackTrace(System.err); }
			}
		}
	}

	@Override
	public MemberVO findByPrimaryKey(Integer techId) {
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, techId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setTechId(rs.getInt("TECH_ID"));
				memberVO.setAccount(rs.getString("ACCOUNT"));
				memberVO.setName(rs.getString("NAME"));
				memberVO.setAddress(rs.getString("ADDRESS"));
				memberVO.setPhone(rs.getString("PHONE"));
				memberVO.setStatus(rs.getInt("STATUS"));
				memberVO.setCreatedAt(rs.getTimestamp("CREATED_AT"));
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try { rs.close(); } catch (SQLException se) { se.printStackTrace(System.err); }
			}
			if (pstmt != null) {
				try { pstmt.close(); } catch (SQLException se) { se.printStackTrace(System.err); }
			}
			if (con != null) {
				try { con.close(); } catch (Exception e) { e.printStackTrace(System.err); }
			}
		}
		return memberVO;
	}

	@Override
	public List<MemberVO> getAll() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setTechId(rs.getInt("TECH_ID"));
				memberVO.setAccount(rs.getString("ACCOUNT"));
				memberVO.setName(rs.getString("NAME"));
				memberVO.setAddress(rs.getString("ADDRESS"));
				memberVO.setPhone(rs.getString("PHONE"));
				memberVO.setStatus(rs.getInt("STATUS"));
				memberVO.setCreatedAt(rs.getTimestamp("CREATED_AT"));
				list.add(memberVO);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try { rs.close(); } catch (SQLException se) { se.printStackTrace(System.err); }
			}
			if (pstmt != null) {
				try { pstmt.close(); } catch (SQLException se) { se.printStackTrace(System.err); }
			}
			if (con != null) {
				try { con.close(); } catch (Exception e) { e.printStackTrace(System.err); }
			}
		}
		return list;
	}
}