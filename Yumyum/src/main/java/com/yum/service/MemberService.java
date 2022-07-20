package com.yum.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.yum.domain.MemberDTO;

public interface MemberService {
	
	public boolean registerMember(MemberDTO params);
	
	public MemberDTO getMemberDetail(Long user_num);
	
	public boolean deleteMember(Long user_num);
	
	public List<MemberDTO> getMemberList();
	
	public int idOverlapCheck(String id);

	public String findId(String name, String email);
	
	public int findPw(String id, String email);
	
	public MemberDTO login(String id, String pw);
	
	//public MemberDTO login(MemberDTO params, HttpSession session);
}