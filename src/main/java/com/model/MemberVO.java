package com.model; // 根據結構圖修改封裝路徑 [cite: 20, 21]

import java.sql.Timestamp;

/**
 * MemberVO - 技師會員資料載體物件
 * 對應資料庫表: MEMBER [cite: 20]
 */
public class MemberVO implements java.io.Serializable {
    
    // 必須加上版本序號，確保序列化相容性 [cite: 1]
    private static final long serialVersionUID = 1L;

    private Integer techId;      // 技師編號 (PK, AUTO_INCREMENT)
    private String account;      // 技師帳號 (UNIQUE)
    private String name;         // 技師名稱
    private String address;      // 服務地址
    private String phone;        // 技師電話
    private Integer status;      // 技師狀態 (0:正常, 1:停權)
    private Timestamp createdAt; // 帳戶創建時間

    // 無參數建構子 (POJO 規範，許多框架如 Hibernate/Struts 需要它)
    public MemberVO() {
    }

    // Getter & Setter [cite: 21]
    
    public Integer getTechId() {
        return techId;
    }

    public void setTechId(Integer techId) {
        this.techId = techId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}