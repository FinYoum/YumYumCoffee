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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yum.constant.Method;
import com.yum.constant.SessionConstants;
import com.yum.domain.BranchDTO;
import com.yum.domain.ImgDTO;
import com.yum.domain.MemberDTO;
import com.yum.domain.ProductDTO;
import com.yum.service.BranchService;
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
	public String openproductList( 
			@RequestParam(value = "branchNum", required = false) Long branchNum, Model model ) {
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
		return "branch/product";
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
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("없는 게시글이거나, 이미 삭제된 게시글입니다");
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
