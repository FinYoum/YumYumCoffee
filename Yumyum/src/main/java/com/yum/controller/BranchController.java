package com.yum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yum.domain.BranchDTO;
import com.yum.domain.BranchProductDTO;
import com.yum.service.BranchProductService;
import com.yum.service.BranchService;

@Controller
public class BranchController {
	
	@Autowired
	private BranchService branchService;
	
	@GetMapping(value = "/yumyum/branch")
	public String openBranchList(Model model) {
		List<BranchDTO> branchList = branchService.getBranchList();
		model.addAttribute("branchList", branchList);

		return "yumyum/branch";
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
		return "yumyum/product";
	}
	
	@GetMapping(value = "/drink")
	public String openDrinkList(Model model) {
//		List<BranchDTO> branchList = branchService.getBranchList();
//		model.addAttribute("branchList", branchList);

		return "yumyum/drink";
	}
	
	@GetMapping(value = "/dessert")
	public String openDessertList(Model model) {
//		List<BranchDTO> branchList = branchService.getBranchList();
//		model.addAttribute("branchList", branchList);

		return "yumyum/dessert";
	}
	
}
