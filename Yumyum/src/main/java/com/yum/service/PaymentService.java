package com.yum.service;

import java.util.List;

import com.yum.domain.PaymentDTO;

public interface PaymentService {

	// 결제할 내역 보기
	// totalPrice로 결제 진행
	
	public List<PaymentDTO> getPaymentList(PaymentDTO params);
	public int getTotalPrice(int orderNum);
	public int countPaymentList(PaymentDTO params);
}
