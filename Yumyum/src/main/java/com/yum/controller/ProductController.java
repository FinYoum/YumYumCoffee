package com.yum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.yum.domain.ProductDTO;
import com.yum.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping(value="/product/write.do")
	public String openProductWrite(@RequestParam(value="productNum", required=false) Long productNum, Model model ) {
		if(productNum == null) {
			//productNum이 null일 경우 비어있는 객체를 전달(ProductDTO 객체를 product라는 이름으로)
			model.addAttribute("product",new ProductDTO());
		}else {
			//productNum이 전송된 경우 정보가 포함하고 있는 객체 전달
			ProductDTO product = productService.getProductDetail(productNum);
			if(product == null) {
				return "redirect:/product/list.do";
			}
			model.addAttribute("product", product);
		}
		
		return "product/productWrite";
	}
	
	
	@PostMapping(value = "/product/register.do")
	public String registerBoard(final ProductDTO params, final MultipartFile[] files, Model model)  throws Exception {
//		Map<String, Object> pagingParmas = getPagingParams(params);
		try {
			boolean isRegistered = productService.registerProduct(params , files);
			if (isRegistered == false) {
				// TODO => 게시글 등록에 실패하였다는 메시지를 전달
				System.out.println("게시글 등록에 실패");
			}
		} catch (DataAccessException e) {
			// TODO => 데이터베이스 처리 과정에 문제가 발생하였다는 메시지를 전달
			System.out.println("데이터베이스 처리 과정에 문제");
		} catch (Exception e) {
			// TODO => 시스템에 문제가 발생하였다는 메시지를 전달
			System.out.println("시스템에 문제가 발생");
		}

		return "redirect:/product/list.do";
	}
	
	@GetMapping(value = "/product/list.do")
	public String openProductList(@ModelAttribute("params") ProductDTO params, Model model) {
		List<ProductDTO> productList = productService.getProductList(params);
		model.addAttribute("productList", productList);

		return "product/productList";
	}
	
	@GetMapping(value = "/product/list2")
	public String openProductList2(@ModelAttribute("params") ProductDTO params, Model model) {
		List<ProductDTO> productList = productService.getProductList(params);
		model.addAttribute("productList", productList);

		return "product/productList2";
	}
}
