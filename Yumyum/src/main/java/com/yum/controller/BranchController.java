package com.yum.controller;

import java.io.File;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yum.constant.Method;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.yum.constant.SessionConstants;
import com.yum.domain.BranchDTO;
import com.yum.domain.CartDTO;
import com.yum.domain.ImgDTO;
import com.yum.domain.MemberDTO;
import com.yum.domain.ProductDTO;
import com.yum.service.BranchService;
import com.yum.service.CartService;
import com.yum.service.ProductService;
import com.yum.util.DownloadView;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BranchController {
	
	@Autowired
	private BranchService branchService;
	
	@Autowired
	private ProductService productService;
	
	
	@Autowired
	private CartService cartService;
	
	
	
	/** 업로드 */
	private final String uploadPath = Paths.get("C:", "Users", System.getProperty("user.name"),"Pictures","yumyum").toString();

	
	/**
	 * 파일 다운로드
	 * @param imgNum
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/fileDownload/{imgNum}")
	public DownloadView fileDownload(@PathVariable Long imgNum , Model model) throws Exception {			
			ImgDTO imgDTO=branchService.getImg(imgNum);	
			if(imgDTO==null) {
				throw new RuntimeException("해당 파일 존재 하지 않습니다.");
			}			
			log.info(" uploadPath+imgDTO.getImgPath()  =>  {}", uploadPath+File.separator+imgDTO.getSaveName());
			File file = new File(uploadPath+File.separator+imgDTO.getSaveName());
			model.addAttribute("downloadFile", file);
			model.addAttribute("orignalName", imgDTO.getOriginalName());
			DownloadView downloadView=new DownloadView();
			return downloadView;			
	}
	
	
	@GetMapping(value = "/yumyum/branch")
	public String openBranchList(Model model) {
		List<BranchDTO> branchList = branchService.getBranchList();
		model.addAttribute("branchList", branchList);
		return "branch/branch";
	}
	
	@GetMapping(value = "/map")
	public String viewMap(@RequestParam(value = "branchNum", required = false) Long branchNum, Model model, HttpSession session) {
		
		MemberDTO member = (MemberDTO)session.getAttribute(SessionConstants.loginMember);
		model.addAttribute("member", member);	
		model.addAttribute("branchNum", branchNum);
		if (branchNum == null) {
			// TODO => 올바르지 않은 접근이라는 메시지를 전달하고, 지점 리스트로 리다이렉트
			return "redirect:/yumyum/branch";
		}
		
		return "branch/map";
	}

	@GetMapping(value = "/product")
	public String openproductList( @RequestParam(value = "branchNum", required = false) Long branchNum, Model model ) {
//		List<BranchProductDTO> branchProductList = BranchProductService.getBranchProductList();
//		model.addAttribute("branchProductList", branchProductList);
		
		if (branchNum == null) {
			// TODO => 올바르지 않은 접근이라는 메시지를 전달하고, 지점 리스트로 리다이렉트
			return "redirect:/yumyum/branch";
		}
		
//		BranchDTO branch = BranchProductService.getBoardDetail(idx);
//		if (branch == null ) {
//			// TODO => 없는 지점이거나, 이미 삭제된 지점이라는 메시지를 전달하고, 지점 리스트로 리다이렉트
//			return "redirect:/yumyum/branch";
//		}
//		model.addAttribute("branch", branch);
//		
		List<BranchDTO> branchList = branchService.getBranchList();
		model.addAttribute("branchList", branchList);
		return "branch/product";
	}
	
	@PostMapping(value = "/product/addCart")
	@ResponseBody
	public ResponseEntity<?> addCart(@RequestBody CartDTO cartDTO,  HttpSession session, Model model) {		
		try {
			MemberDTO  member=(MemberDTO)session.getAttribute(SessionConstants.loginMember);			
			if(member==null) {			
				return ResponseEntity.status(HttpStatus.OK).body(new ObjectMapper().writeValueAsString("login error"));	
			}
			
			ProductDTO product = productService.getProductDetail(cartDTO.getProductNum());
			cartDTO.setPrice(product.getPrice());
			cartDTO.setUserNum(Long.valueOf(member.getUserNum()));

			CartDTO confirm=cartService.confirmCart(cartDTO);
			if(confirm==null) {
				int totalPrice=(int) (cartDTO.getPrice()*cartDTO.getQty());
				
				if(cartDTO.getOption3().equals("true")) {
					totalPrice =totalPrice+500;
				}
				
				if(cartDTO.getOption4().equals("true")) {
					totalPrice =totalPrice+1000;
				}
				cartDTO.setTotalPrice(Long.valueOf( totalPrice));
				cartService.addCart(cartDTO);	
			}else {		
				int totalPrice=(int) (cartDTO.getPrice()*cartDTO.getQty());
				if(cartDTO.getOption3().equals("true")) {
					totalPrice =totalPrice+500;
				}
				
				if(cartDTO.getOption4().equals("true")) {
					totalPrice =totalPrice+1000;
				}
				
				cartDTO.setTotalPrice(totalPrice  +  confirm.getTotalPrice());
				cartDTO.setQty(cartDTO.getQty()+confirm.getQty());
				cartService.updateCart2(cartDTO);
			}
						
			return ResponseEntity.status(HttpStatus.OK).body(new ObjectMapper().writeValueAsString("success"));			
		} catch (Exception e) {			
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}		
	}

	
	/**
	 * 장바구니 목록 불러오기
	 * @param session
	 * @param model
	 * @return
	 */
	@GetMapping(value="/product/cartList")
	@ResponseBody
	public ResponseEntity<?> cartList(HttpSession session, Model model) {		
		try {
			MemberDTO  member=(MemberDTO)session.getAttribute(SessionConstants.loginMember);			
			if(member==null) {			
				return ResponseEntity.status(HttpStatus.OK).body(new ObjectMapper().writeValueAsString("login error"));	
			}

			List<CartDTO> cartList = cartService.getCartList(member.getUserNum());
						
			return ResponseEntity.status(HttpStatus.OK).body(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(cartList));			
		} catch (Exception e) {			
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}		
	}

	
	@PutMapping(value="/product/updateCart2")
	@ResponseBody
	public ResponseEntity<?> updateCart2(@RequestBody CartDTO cartDTO , HttpSession session, Model model) {		
		try {
			MemberDTO  member=(MemberDTO)session.getAttribute(SessionConstants.loginMember);			
			if(member==null) {			
				return ResponseEntity.status(HttpStatus.OK).body(new ObjectMapper().writeValueAsString("login error"));	
			}

			cartDTO.setUserNum(Long.valueOf(member.getUserNum()));
			int result= cartService.updatePriceCart2(cartDTO);
						
			return ResponseEntity.status(HttpStatus.OK).body(new ObjectMapper().writeValueAsString("success"));			
		} catch (Exception e) {			
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}		
	}
	
	
	
	@DeleteMapping(value="/product/deleteCart2")
	@ResponseBody
	public ResponseEntity<?> deleteCart(@RequestBody CartDTO cartDTO, HttpSession session, Model model) {		
		try {
			MemberDTO  member=(MemberDTO)session.getAttribute(SessionConstants.loginMember);			
			if(member==null) {			
				return ResponseEntity.status(HttpStatus.OK).body(new ObjectMapper().writeValueAsString("login error"));	
			}

			cartService.deleteCart2(cartDTO);
						
			return ResponseEntity.status(HttpStatus.OK).body(new ObjectMapper().writeValueAsString("success"));			
		} catch (Exception e) {			
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}		
	}
	
	
	@GetMapping(value = "/product/catgoryProductList/{codeId}")
	@ResponseBody
	public ResponseEntity<?> catgoryProductList(@PathVariable("codeId") String codeId,
			ProductDTO  productDTO, Model model) {		
		try {
			productDTO.setCodeId(codeId);	
			productDTO.setRecordsPerPage(12);
			List<ProductDTO> pList = productService.getProductList(productDTO);			
			return ResponseEntity.status(HttpStatus.OK).body(pList);			
		} catch (Exception e) {			
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}		
	}

	
	
	@GetMapping(value = "/product/detail/{productNum}")
	@ResponseBody
	public ResponseEntity<?> productDetail(@PathVariable Long productNum, Model model) throws JsonProcessingException {

		ProductDTO product = productService.getProductDetail(productNum);
		if (product == null) {			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("없는 제품이거나, 이미 삭제된 제품입니다");
		}
		
		Map<String,Object> map=new LinkedHashMap<>();
		List<ImgDTO> fileList =productService.getAttachFileList(productNum);
		map.put("product", product);
		map.put("fileList", fileList);
		String json=new ObjectMapper().writeValueAsString(map);

		return ResponseEntity.status(HttpStatus.OK).body(json);
	}
	
	
	
		
	
	
	
	/*
	 * @GetMapping(value = "/drink") public String openDrinkList(Model model) { //
	 * List<BranchDTO> branchList = branchService.getBranchList(); //
	 * model.addAttribute("branchList", branchList);
	 * 
	 * return "yumyum/drink"; }
	 * 
	 * @GetMapping(value = "/dessert") public String openDessertList(Model model) {
	 * // List<BranchDTO> branchList = branchService.getBranchList(); //
	 * model.addAttribute("branchList", branchList);
	 * 
	 * return "yumyum/dessert"; }
	 */
	
}
