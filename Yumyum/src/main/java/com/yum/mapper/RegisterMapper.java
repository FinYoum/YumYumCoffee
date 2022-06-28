package com.yum.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.yum.domain.MemberDTO;

@Mapper
public interface RegisterMapper {
	void signCheck(MemberDTO params);

}
