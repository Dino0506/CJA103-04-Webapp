package com.model; // 根據結構圖，package 路徑設為 com.model

import java.util.*;

/**
 * MemberDAO_interface - 技師會員資料存取介面
 * 定義對 MEMBER table 的增刪改查規格
 */
public interface MemberDAO_interface {
          /**
           * 新增技師會員
           * @param memberVO 包含帳號、姓名、地址、電話、狀態的物件
           */
          public void insert(MemberVO memberVO);

          /**
           * 修改技師會員資料
           * @param memberVO 必須包含 techId (PK) 以及要修改的欄位
           */
          public void update(MemberVO memberVO);

          /**
           * 刪除技師會員
           * @param techId 技師編號
           */
          public void delete(Integer techId);

          /**
           * 根據技師編號查詢單一資料
           * @param techId 技師編號
           * @return MemberVO 技師資料物件
           */
          public MemberVO findByPrimaryKey(Integer techId);

          /**
           * 查詢所有技師會員資料
           * @return List<MemberVO> 技師清單
           */
          public List<MemberVO> getAll();
          
          // 萬用複合查詢 (保留範本中的擴充性)
//        public List<MemberVO> getAll(Map<String, String[]> map); 
}