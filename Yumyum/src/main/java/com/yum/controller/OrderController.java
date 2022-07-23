package com.yum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.yum.domain.OrderDTO;
import com.yum.domain.ProductDTO;
import com.yum.service.PaymentService;
import com.yum.service.ProductService;

@Controller
public class OrderController {
	
	@Autowired
	private ProductService productService;
	@Autowired
	private PaymentService paymentService;

	@GetMapping(value = "/order/{orderNum}")
	public String openOrderList(@ModelAttribute("params") ProductDTO params, @PathVariable("orderNum") int orderNum, Model model) {
		List<OrderDTO> orderList = paymentService.selectOrder(orderNum);
		String branchName= paymentService.getBranchName(orderNum);
		model.addAttribute("orderList", orderList);
		model.addAttribute("branchName", branchName);

		return "order/orderPage2";
	}
	
}
