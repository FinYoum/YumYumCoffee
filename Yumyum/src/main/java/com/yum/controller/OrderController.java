package com.yum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.yum.domain.ProductDTO;
import com.yum.service.ProductService;

@Controller
public class OrderController {
	
	@Autowired
	private ProductService productService;

	@GetMapping(value = "/order")
	public String openOrderList(@ModelAttribute("params") ProductDTO params, Model model) {
		List<ProductDTO> orderList = productService.getProductList(params);
		model.addAttribute("orderList", orderList);

		return "order/orderPage2";
	}
	
}
