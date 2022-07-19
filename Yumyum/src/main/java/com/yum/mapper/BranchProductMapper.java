package com.yum.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yum.domain.BranchProductDTO;

@Mapper
public interface BranchProductMapper {
	
	public List<BranchProductDTO> selectBranchProductList();
	
	public BranchProductDTO selectBranchProductDetail();

}
