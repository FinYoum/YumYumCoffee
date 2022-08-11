package com.yum.service;

import java.util.List;

import com.yum.domain.CartDTO;
import com.yum.domain.OrderDTO;
import com.yum.domain.PaymentDTO;

public interface OrderService {
	
	// 주문 내역 추가
	public boolean insertOrder(OrderDTO params);
	
	// 주문 세부 내역 추가
	public boolean insertOrderDetail(CartDTO params);
	
	// 쿠폰 추가
	public boolean insertCoupon(Long userNum, Long totalQty);
	
	// 결제 정보 추가
	public boolean inserstPayInfo(PaymentDTO params);
	
	// 결제할 내역 보기
	// totalPrice로 결제 진행
	
	public List<PaymentDTO> getPaymentList(PaymentDTO params);
	public int getTotalPrice(int orderNum);
	public int countPaymentList(PaymentDTO params);
	
	//지점 정보
	public String getBranchName(int orderNum);
	
	//주문내역
	public List<OrderDTO> selectOrder(int orderNum);
	
//	//제품번호 얻어오기
//	public String getProductName(int orderNum);
}
