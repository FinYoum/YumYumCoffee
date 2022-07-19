package com.yum.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yum.domain.BranchProductDTO;

@Mapper
public interface BranchProductMapper {
	
	public List<BranchProductDTO> selectBranchProductList(Long productNum);
	
	public BranchProductDTO selectBranchProductDetail();
	
	//select 
	public List<BranchProductDTO> selectBPList(BranchProductDTO params);
	
	//public BranchProductDTO selectBProductDetail(long branchNum);
	
	public int selectBProductTotalCount(BranchProductDTO params);
	
	//update(hidenYn)
	public void updateBProductState(BranchProductDTO params);
	

}
