package com.yum.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yum.domain.BranchProductDTO;
import com.yum.domain.ProductDTO;

@Mapper
public interface BranchProductMapper {
	
	public List<BranchProductDTO> selectBranchProductList(Long productNum);
	public List<ProductDTO> selectBranchDrinkList(Long branchNum);
	public List<ProductDTO> selectBranchDessertList(Long branchNum);
	
	public BranchProductDTO selectBranchProductDetail();
	
	//select 
	public List<BranchProductDTO> selectBPList(BranchProductDTO params);
	
	//public BranchProductDTO selectBProductDetail(long branchNum);
	
	public int selectBProductTotalCount(BranchProductDTO params);
	
	//update(hidenYn)
	public void updateBProductState(BranchProductDTO params);
	

}
