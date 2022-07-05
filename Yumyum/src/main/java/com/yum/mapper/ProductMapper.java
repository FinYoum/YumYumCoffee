package com.yum.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yum.domain.ProductDTO;

@Mapper
public interface ProductMapper {
	
	public int insertProduct(ProductDTO params);
	
	public ProductDTO selectProductDetail(Long productNum);
	
	public int updateProduct(ProductDTO params);
	
	public int deleteProduct(Long productNum);
	
	public List<ProductDTO> selectProductList();
	
	public int selectProductTotalCount();

	
}
