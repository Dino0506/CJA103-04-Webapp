package com.model; // 根據結構圖修改封裝路徑

import java.util.List;

public class MemberService {

	private MemberDAO_interface dao;

	public MemberService() {
		// 指向實作類別 MemberJDBCDAO
		dao = new MemberJDBCDAO();
	}

	// 新增技師會員
	public MemberVO addMember(String account, String name, String address, 
			String phone, Integer status) {

		MemberVO memberVO = new MemberVO();

		memberVO.setAccount(account);
		memberVO.setName(name);
		memberVO.setAddress(address);
		memberVO.setPhone(phone);
		memberVO.setStatus(status);
		dao.insert(memberVO);

		return memberVO;
	}

	// 修改技師會員
	public MemberVO updateMember(Integer techId, String account, String name, 
			String address, String phone, Integer status) {

		MemberVO memberVO = new MemberVO();

		memberVO.setTechId(techId);
		memberVO.setAccount(account);
		memberVO.setName(name);
		memberVO.setAddress(address);
		memberVO.setPhone(phone);
		memberVO.setStatus(status);
		dao.update(memberVO);

		return dao.findByPrimaryKey(techId);
	}

	// 刪除技師會員
	public void deleteMember(Integer techId) {
		dao.delete(techId);
	}

	// 查詢單一技師會員
	public MemberVO getOneMember(Integer techId) {
		return dao.findByPrimaryKey(techId);
	}

	// 查詢所有技師會員
	public List<MemberVO> getAll() {
		return dao.getAll();
	}
}