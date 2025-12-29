package com.model;

import java.util.List;

public class MemberService {

	private MemberDAO_interface dao;

	public MemberService() {
		// 指向實作類別 MemberJDBCDAO，處理 technician 資料表
		dao = new MemberJDBCDAO();
	}

	// 新增技師資料
	public MemberVO addMember(Integer memberNo, String realName, String phone, 
			String email, String serviceArea, Integer isActive) {

		MemberVO memberVO = new MemberVO();

		memberVO.setMemberNo(memberNo);
		memberVO.setRealName(realName);
		memberVO.setPhone(phone);
		memberVO.setEmail(email);
		memberVO.setServiceArea(serviceArea);
		memberVO.setIsActive(isActive);
		dao.insert(memberVO);

		return memberVO;
	}

	// 修改技師資料
	public MemberVO updateMember(Integer techNo, String realName, String phone, 
			String email, String serviceArea, Integer isActive) {

		MemberVO memberVO = new MemberVO();

		memberVO.setTechNo(techNo);
		memberVO.setRealName(realName);
		memberVO.setPhone(phone);
		memberVO.setEmail(email);
		memberVO.setServiceArea(serviceArea);
		memberVO.setIsActive(isActive);
		dao.update(memberVO);

		return dao.findByPrimaryKey(techNo);
	}

	// 刪除技師資料
	public void deleteMember(Integer techNo) {
		dao.delete(techNo);
	}

	// 查詢單一技師資料
	public MemberVO getOneMember(Integer techNo) {
		return dao.findByPrimaryKey(techNo);
	}

	// 查詢所有技師資料
	public List<MemberVO> getAll() {
		return dao.getAll();
	}
}