package com.yum.service;

import java.util.List;

import com.yum.domain.MemberDTO;

public interface MemberService {
	
	public boolean registerMember(MemberDTO params);
	
	public MemberDTO getMemberDetail(Long user_num);
	
	public boolean deleteMember(Long user_num);
	
	public List<MemberDTO> getMemberList();
	
	public int idOverlapCheck(String id);
}