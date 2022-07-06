package com.yum.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.yum.mapper.AttachMapper;
import com.yum.util.FileUtils;
import com.yum.domain.ImgDTO;
import com.yum.domain.ProductDTO;
import com.yum.mapper.ProductMapper;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private AttachMapper attachMapper;
	
	@Autowired
	private FileUtils fileUtils;

	@Override
	public boolean registerProduct(ProductDTO params) {
		int queryResult = 0;

		if (params.getProductNum() == null) { 
			int ProductNumMax = productMapper.selectProductMax(params);
			params.setProductNum((long)ProductNumMax+1);
			
			System.out.println("-----------------");
			System.out.println(params.getProductNum());
			System.out.println("codeId : "+params.getCodeId());
			System.out.println(params.getName());
			System.out.println(params.getPrice());
			System.out.println(params.getInfo());
			System.out.println("-----------------");
			queryResult =productMapper.insertProduct(params);
			System.out.println("queryResult : " + queryResult);
		} else { 
			queryResult =productMapper.updateProduct(params); 
		}
		 
		return (queryResult == 1) ? true : false; 
		
	}
	
	@Override
	public boolean registerProduct(ProductDTO params, MultipartFile[] files) {
		int queryResult =1;
		
		if(registerProduct(params) == false) {
			return false;
		}
		
		List<ImgDTO> fileList = fileUtils.uploadFiles(files, params.getProductNum());
		if (CollectionUtils.isEmpty(fileList) == false) {
			queryResult = attachMapper.insertAttach(fileList);
			if (queryResult < 1) {
				queryResult = 0;
			}
		}
		return (queryResult > 0);
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
	public List<ProductDTO> getProductList(ProductDTO params) {
		List<ProductDTO> productList =Collections.emptyList();

		int productTotalCount =productMapper.selectProductTotalCount(params);
		
		if(productTotalCount >0) {
			productList = productMapper.selectProductList(params);
		}
		return productList;
	}

	
	
	
}
