package com.model;
import java.util.List;

public interface MemberDAO_interface {
    public void insert(MemberVO memberVO);
    public void update(MemberVO memberVO);
    public void delete(Integer techNo);
    public MemberVO findByPrimaryKey(Integer techNo);
    public List<MemberVO> getAll();
}