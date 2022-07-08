package com.yum.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yum.domain.MemberDTO;
import com.yum.mapper.RegisterMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private RegisterMapper registerMapper;
	
	@Override
	public boolean registerMember(MemberDTO params) {
		int queryResult = 0;
		
		if (params.getUserNum() == 0) {
			queryResult = registerMapper.insertMember(params);
		} else {
			queryResult = registerMapper.updateMember(params);
		}
		
		return (queryResult == 1) ? true : false;
	}
	
	@Override
	public MemberDTO getMemberDetail(Long userNum) {
		return registerMapper.selectMemberDetail(userNum);
	}
	
	@Override
	public boolean deleteMember(Long userNum) {
		int queryResult = 0;
		
		MemberDTO member = registerMapper.selectMemberDetail(userNum);
		
		if (member !=null && "N".equals(member.getDeleteYn())) {
			queryResult = registerMapper.deleteMember(userNum);
		}
		
		return (queryResult == 1) ? true : false;
	}
	@Override
	public List<MemberDTO> getMemberList() {
		List<MemberDTO> memberList = Collections.emptyList();
		
		int memberTotalCount = registerMapper.selectMemberTotalCount();
		
		if (memberTotalCount > 0) {
			memberList = registerMapper.selectMemberList();
		}
		
		return memberList;
	}
	@Override
	public int idOverlapCheck(String id) {
		int result = registerMapper.idOverlapCheck(id);
		return result;
	}
}
