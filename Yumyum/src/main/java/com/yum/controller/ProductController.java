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

import com.yum.constant.Method;
import com.yum.domain.BranchProductDTO;
import com.yum.domain.ImgDTO;
import com.yum.domain.ProductDTO;
import com.yum.service.ProductService;
import com.yum.util.UiUtils;

@Controller
public class ProductController extends UiUtils{
	
	@Autowired
	private ProductService productService;
	
	@GetMapping(value="/product/write")
	public String openProductWrite(@RequestParam(value="productNum", required=false) Long productNum, Model model ) {
		
		if(productNum == null) {
			//productNum이 null일 경우 비어있는 객체를 전달(ProductDTO 객체를 product라는 이름으로)
			model.addAttribute("product",new ProductDTO());
		}else {
			//productNum이 전송된 경우 정보가 포함하고 있는 객체 전달
			ProductDTO product = productService.getProductDetail(productNum);
			if(product == null ) {
				return "redirect:/product/list";
			}
			model.addAttribute("product",product);
			
			List<ImgDTO> fileList =productService.getAttachFileList(productNum);
			model.addAttribute("fileList", fileList);
		}
		return "product/productWrite";
	}
	
	
	@PostMapping(value = "/product/register")
	public String registerProduct(final ProductDTO params, final MultipartFile[] files, Model model)  throws Exception {
		//Map<String, Object> pagingParmas = getPagingParams(params);
		try {
			boolean isRegistered = productService.registerProduct(params,files);
			if (isRegistered == false) {
				return showMessageWithRedirect("게시글 등록에 실패하였습니다.", "/product/list", Method.GET, null, model);
			}
		} catch (DataAccessException e) {
			return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/product/list", Method.GET, null, model);
		} catch (Exception e) {
			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/product/list", Method.GET, null, model);
		}
		return showMessageWithRedirect("게시글 등록이 완료되었습니다.", "/product/list", Method.GET, null, model);
	}
	
	@GetMapping(value = "/product/list")
	public String openProductList(@ModelAttribute("params") ProductDTO params, Model model) {
		List<ProductDTO> productList = productService.getProductList(params);
		model.addAttribute("productList", productList);

		return "product/productList";
	}
		
	@GetMapping(value = "/product/view")
	public String openProductDetail(@RequestParam(value = "productNum", required = false) Long productNum, Model model) {
		if (productNum == null) {
			// TODO => 올바르지 않은 접근이라는 메시지를 전달하고, 게시글 리스트로 리다이렉트
			return showMessageWithRedirect("올바르지 않은 접근입니다.", "/product/list", Method.GET, null, model);
			//return "redirect:/product/list";
		}

		ProductDTO product = productService.getProductDetail(productNum);
		if (product == null) {
			// TODO => 없는 게시글이거나, 이미 삭제된 게시글이라는 메시지를 전달하고, 게시글 리스트로 리다이렉트
			return showMessageWithRedirect("없는 게시글이거나, 이미 삭제된 게시글입니다.", "/product/list", Method.GET, null, model);
			//return "redirect:/product/list";
		}
		model.addAttribute("product", product);
		List<ImgDTO> fileList =productService.getAttachFileList(productNum);
		model.addAttribute("fileList", fileList);
		 

		return "product/view";
	}
	
	//제품 삭제하기
	@PostMapping(value = "/product/delete")
	public String deleteImg(@RequestParam(value = "productNum", required = false) Long productNum, Model model) {
		if (productNum == null) {
			return showMessageWithRedirect("올바르지 않은 접근입니다.", "/product/list", Method.GET, null, model);
		}
		productService.deleteProduct(productNum);
		/*
		try {
			boolean isDeleted = productService.deleteImg(productNum);
			if (isDeleted == false) { // TODO => 게시글 삭제에 실패하였다는 메시지를 전달
				System.out.println("게시글 삭제에 실패"); 
				}
			 
		} catch (DataAccessException e) {
			// TODO => 데이터베이스 처리 과정에 문제가 발생하였다는 메시지를 전달
			System.out.println("데이터베이스 처리 과정에 문제");

		} catch (Exception e) {
			// TODO => 시스템에 문제가 발생하였다는 메시지를 전달
			System.out.println("시스템에 문제 발생");
		}
		*/
		return showMessageWithRedirect("게시글 삭제가 완료되었습니다.", "/product/list", Method.GET, null, model);
	}
	
	//지점장페이지 
	@GetMapping(value = "/product/list2")
	public String openProductList2(@ModelAttribute("params") BranchProductDTO params, Model model) {
		System.out.println("==========controller 시작: "+params.getBranchNum()+"==========");
		List<BranchProductDTO> BranchProductList = productService.getBranchProductList(params);
		model.addAttribute("BranchProductList", BranchProductList);

		return "product/productList2";
	}
	
	/*
	@GetMapping(value = "/product/list2")
	public String openProductList2(@RequestParam(value = "branchNum", required = false) Long branchNum, Model model) {
		System.out.println("==========controller 시작: "+branchNum+"==========");
		BranchProductDTO BranchProductList = productService.getBranchProductList(branchNum);
		model.addAttribute("BranchProductList",BranchProductList);

		return "product/productList2";
	}
	*/
	@PostMapping(value = "/product/list2")
	public String updateBProduct(BranchProductDTO params)  throws Exception {
		productService.updateBProduct(params);
		return "redirect:/product/list2";
	}
	

	//test
		@GetMapping(value = "/product/test")
		public String test() {
			
			return "product/test";
		}
		
		
}
