package com.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class MemberVO implements Serializable {
    private static final long serialVersionUID = 1L;

    // 對應 technician table 欄位
    private Integer techNo;       // 技師編號 (PK)
    private Integer memberNo;     // 會員編號 (FK)
    private String realName;      // 技師姓名 
    private String phone;
    private String email;
    private String serviceArea;   // 可服務地區
    private Integer isActive;     // 是否啟用 (0:未啟用 1:啟用)
    private Timestamp createAt;
    private Integer ratingAmount; // 評價人數
    private Integer ratingStar;   // 總星數

    public MemberVO() {}

    // Getters and Setters 
    public Integer getTechNo() { return techNo; }
    public void setTechNo(Integer techNo) { this.techNo = techNo; }
    public Integer getMemberNo() { return memberNo; }
    public void setMemberNo(Integer memberNo) { this.memberNo = memberNo; }
    public String getRealName() { return realName; }
    public void setRealName(String realName) { this.realName = realName; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getServiceArea() { return serviceArea; }
    public void setServiceArea(String serviceArea) { this.serviceArea = serviceArea; }
    public Integer getIsActive() { return isActive; }
    public void setIsActive(Integer isActive) { this.isActive = isActive; }
    public Timestamp getCreateAt() { return createAt; }
    public void setCreateAt(Timestamp createAt) { this.createAt = createAt; }
    public Integer getRatingAmount() { return ratingAmount; }
    public void setRatingAmount(Integer ratingAmount) { this.ratingAmount = ratingAmount; }
    public Integer getRatingStar() { return ratingStar; }
    public void setRatingStar(Integer ratingStar) { this.ratingStar = ratingStar; }
}