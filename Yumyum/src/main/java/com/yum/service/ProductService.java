package com.yum.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.yum.domain.ProductDTO;


public interface ProductService {
	
	public boolean registerProduct(ProductDTO params);
	
	public boolean registerProduct(ProductDTO params, MultipartFile[] files);
	
	public ProductDTO getProductDetail(Long productNum);

	public boolean deleteProduct(Long productNum);

	public List<ProductDTO> getProductList(ProductDTO params);
}
