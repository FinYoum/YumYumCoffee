package com.yum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yum.domain.ProductDTO;
import com.yum.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping(value="/product/write.do")
	public String openProductWrite(@RequestParam(value="productNum", required=false) Long productNum, Model model ) {
		if(productNum == null) {
			model.addAttribute("product",new ProductDTO());
		}else {
			ProductDTO product = productService.getProductDetail(productNum);
			if(product == null) {
				return "redirect:/product/list.do";
			}
			model.addAttribute("product", product);
		}
		
		return "product/productWrite";
	}
	
	
	@PostMapping(value = "/product/register.do")
	public String registerBoard(final ProductDTO params) throws Exception {
		try {
			boolean isRegistered = productService.registerProduct(params);
			if (isRegistered == false) {
				// TODO => 게시글 등록에 실패하였다는 메시지를 전달
			}
		} catch (DataAccessException e) {
			// TODO => 데이터베이스 처리 과정에 문제가 발생하였다는 메시지를 전달

		} catch (Exception e) {
			// TODO => 시스템에 문제가 발생하였다는 메시지를 전달
		}

		return "redirect:/product/list.do";
	}
	
	@GetMapping(value = "/product/list.do")
	public String openBoardList(Model model) {
		List<ProductDTO> productList = productService.getProductList();
		model.addAttribute("productList", productList);
		return "product/productList";
	}


}
