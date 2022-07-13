package com.yum.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.yum.domain.ImgDTO;
import com.yum.domain.ProductDTO;
import com.yum.mapper.ImgMapper;
import com.yum.mapper.ProductMapper;
<<<<<<< HEAD
=======
import com.yum.paging.Criteria;
import com.yum.paging.PaginationInfo;
>>>>>>> refs/remotes/origin/main
import com.yum.util.FileUtils;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private ImgMapper imgMapper;
	
	@Autowired
	private FileUtils fileUtils;

	@Override
	public boolean registerProduct(ProductDTO params) {
		
		int queryResult = 0;

		if (params.getProductNum() == null) {
			int ProductNumMax = productMapper.selectProductMax(params);
			params.setProductNum((long)ProductNumMax+1);
			queryResult = productMapper.insertProduct(params);
		} else {
			queryResult = productMapper.updateProduct(params);

			// 파일이 추가, 삭제, 변경된 경우
			if ("Y".equals(params.getChangeYn())) {
				imgMapper.deleteAttach(params.getProductNum());
				// fileIdxs에 포함된 idx를 가지는 파일의 삭제여부를 'N'으로 업데이트
				if (CollectionUtils.isEmpty(params.getFileIdxs()) == false) {
					System.out.println("---------------update문 실행 전----------------------------------");
					System.out.println("params.getFileIdxs()"+params.getFileIdxs());
					imgMapper.updateAttach(params.getFileIdxs());
					System.out.println("----------------update 완료------------------------------------------");
				}

			}
		}

		return (queryResult > 0);
		
	}
	
	@Override
	public boolean registerProduct(ProductDTO params, MultipartFile[] files) {
		int queryResult =1;
		
		if(registerProduct(params) == false) {
			return false;
		}
		
		List<ImgDTO> fileList = fileUtils.uploadFiles(files, params.getProductNum());
		
		if (CollectionUtils.isEmpty(fileList) == false) {
<<<<<<< HEAD
=======
			System.out.println("fileList : "+fileList);
>>>>>>> refs/remotes/origin/main
			queryResult = imgMapper.insertAttach(fileList);
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
	public void deleteProduct(Long productNum) {
		productMapper.deleteProduct(productNum);
	}

	@Override
	public List<ProductDTO> getProductList(ProductDTO params) {
		List<ProductDTO> productList =Collections.emptyList();

		int productTotalCount =productMapper.selectProductTotalCount(params);
		
		PaginationInfo paginationInfo = new PaginationInfo(params);
		paginationInfo.setTotalRecordCount(productTotalCount);

		params.setPaginationInfo(paginationInfo);
		
		if(productTotalCount >0) {
			productList = productMapper.selectProductList(params);
		}
		return productList;
	}
	
	@Override
	public List<ImgDTO> getAttachFileList(Long productNum) {

		int fileTotalCount = imgMapper.selectAttachTotalCount(productNum);
		if (fileTotalCount < 1) {
			return Collections.emptyList();
		}
		return imgMapper.selectAttachList(productNum);
	}
	

	//@Override
	//public ProductDTO getListDetail(String codeId) {
	//	return productMapper.selectListDetail(codeId);
	//}


}
