package com.yum.service;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yum.domain.MemberDTO;
import com.yum.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;
	@Autowired
    private PasswordEncoder passwordEncoder;

	@Override
	public MemberDTO login(String id, String pw) { 
		
		return memberMapper.login(id, pw);
	}
	
	@Override
	public boolean registerMember(MemberDTO params) {
		int queryResult = 0;
		
		if (params.getUserNum() == 0) {
	    	params.setPw(passwordEncoder.encode(params.getPw()));
			queryResult = memberMapper.insertMember(params);
		} else {
			queryResult = memberMapper.updateMember(params);
		}
		        
		return (queryResult == 1) ? true : false;
	}
	
	@Override
	public MemberDTO getMemberDetail(Long userNum) {
		return memberMapper.selectMemberDetail(userNum);
	}
	
	@Override
	public boolean deleteMember(Long userNum) {
		int queryResult = 0;
		
		MemberDTO member = memberMapper.selectMemberDetail(userNum);
		
		if (member !=null && "N".equals(member.getDeleteYn())) {
			queryResult = memberMapper.deleteMember(userNum);
		}
		
		return (queryResult == 1) ? true : false;
	}
	@Override
	public List<MemberDTO> getMemberList() {
		List<MemberDTO> memberList = Collections.emptyList();
		
		int memberTotalCount = memberMapper.selectMemberTotalCount();
		
		if (memberTotalCount > 0) {
			memberList = memberMapper.selectMemberList();
		}
		
		return memberList;
	}
	@Override
	public int idOverlapCheck(String id) {
		int result = memberMapper.idOverlapCheck(id);
		return result;
	}
	
	
	
}
