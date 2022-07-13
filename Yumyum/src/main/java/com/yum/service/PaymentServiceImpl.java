package com.yum.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yum.domain.PaymentDTO;
import com.yum.mapper.PaymentMapper;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentMapper paymentMapper;
	
	@Override
	public int countPaymentList(PaymentDTO params) {
		int countPaymentList = paymentMapper.countPaymentList(params);
		return countPaymentList;
	}

	@Override
	public List<PaymentDTO> getPaymentList(PaymentDTO params) {
		List<PaymentDTO> paymentList = Collections.emptyList();
		int countPaymentList = paymentMapper.countPaymentList(params);
		if (countPaymentList > 0) {
			try {
				paymentList = paymentMapper.selectPaymentList(params);
			} catch(RuntimeException e) {
				System.out.println(e);
			} catch (Exception e){
				System.out.println(e);
			}
		}
		return paymentList;
	}

	@Override
	public int getTotalPrice(int orderNum) {
		int totalPrice = paymentMapper.selectTotalPrice(orderNum);
		return totalPrice;
	}
}
