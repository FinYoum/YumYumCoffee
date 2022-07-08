package com.yum.mapper;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yum.domain.MemberDTO;

@Mapper
public interface MemberMapper {
	
	public int idOverlapCheck(String id);
	
	public MemberDTO login(@Param("id") String id, @Param("pw") String pw);
	//public MemberDTO login(MemberDTO params, HttpSession session);
	//public MemberDTO login(String id);
	
	public int insertMember(MemberDTO params);
	
	public MemberDTO selectMemberDetail(Long userNum);
	
	public int updateMember(MemberDTO params);
	
	public int deleteMember(Long userNum);
	
	public List<MemberDTO> selectMemberList();

	public int selectMemberTotalCount();
}
