package com.yum.service;

import java.util.List;

import com.yum.domain.ProductDTO;


public interface ProductService {
	
	public boolean registerProduct(ProductDTO params);
	
	public ProductDTO getProductDetail(Long productNum);

	public boolean deleteProduct(Long productNum);

	public List<ProductDTO> getProductList();
}
