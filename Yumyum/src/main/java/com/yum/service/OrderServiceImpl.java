package com.yum.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yum.domain.CartDTO;
import com.yum.domain.OrderDTO;
import com.yum.domain.PaymentDTO;
import com.yum.mapper.OrderMapper;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderMapper paymentMapper;
	
	// 주문 내역 추가
	@Override		
	public boolean insertOrder(OrderDTO params) {
		int queryResult = 0;
			queryResult = paymentMapper.insertOrder(params);
		return (queryResult == 1) ? true : false;
	}
	
	// 주문 세부 내역 추가
	@Override		
	public boolean insertOrderDetail(CartDTO params) {
		int queryResult = 0;
			queryResult = paymentMapper.insertOrderDetail(params);
		return (queryResult == 1) ? true : false;
	}
	
	// 쿠폰 추가
	public boolean insertCoupon(Long userNum, Long totalQty) {
		int queryResult = 0;
		queryResult = paymentMapper.insertCoupon(userNum, totalQty);
		return (queryResult == 1) ? true : false;
	}
	
	// 결제 정보 추가
		public boolean inserstPayInfo(PaymentDTO params) {
		int queryResult = 0;
		queryResult = paymentMapper.insertPayInfo(params);
		return (queryResult == 1) ? true : false;
	}
	
	
		
		
		
		
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

	@Override
	public String getBranchName(int orderNum) {
		// TODO 주문 시 선택한 지점의 번호를 받아 지점명을 넘겨줌
		String branchName = paymentMapper.selectBranchName(orderNum);
		return branchName;
	}

	@Override
	public List<OrderDTO> selectOrder(int orderNum) {
		// TODO 주문 내역
		List<OrderDTO> selectOrder = paymentMapper.selectOrder(orderNum);
		return selectOrder;
	}

//	@Override
//	public String getProductName(int orderNum) {
//		// TODO 주문 번호에 해당하는 주문의 제품명 받아오기
//		String productName = paymentMapper.getProductName(orderNum);
//		return null;
//	}
}
