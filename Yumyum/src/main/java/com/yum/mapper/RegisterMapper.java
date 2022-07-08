package com.yum.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yum.domain.MemberDTO;

@Mapper
public interface RegisterMapper {
	
	public int idOverlapCheck(String id);
	
	public int insertMember(MemberDTO params);
	
	public MemberDTO selectMemberDetail(Long userNum);
	
	public int updateMember(MemberDTO params);
	
	public int deleteMember(Long userNum);
	
	public List<MemberDTO> selectMemberList();

	public int selectMemberTotalCount();
}
