package com.yum.service;

import java.util.Collections;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yum.domain.ProductDTO;
import com.yum.mapper.ProductMapper;
@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductMapper productMapper;

	@Override
	public boolean registerProduct(ProductDTO params) {
		int queryResult = 0;

		if (params.getProductNum() == null) { 
			queryResult =productMapper.insertProduct(params);
			System.out.println(params.getProductNum());
			System.out.println(params.getName());
			System.out.println(params.getPrice());
			System.out.println(params.getInfo());
			System.out.println(params.getImg());
		} else { 
			queryResult =productMapper.updateProduct(params); 
		}
		 
		return (queryResult == 1) ? true : false;
		
	}

	@Override
	public ProductDTO getProductDetail(Long productNum) {
		return productMapper.selectProductDetail(productNum);
	}

	@Override
	public boolean deleteProduct(Long productNum) {
		return false;
	}

	@Override
	public List<ProductDTO> getProductList() {
		List<ProductDTO> productList =Collections.emptyList();
		
		int productTotalCount =productMapper.selectProductTotalCount();
		
		if(productTotalCount >0) {
			productList = productMapper.selectProductList();
		}
		return productList;
	}
	
	
}
