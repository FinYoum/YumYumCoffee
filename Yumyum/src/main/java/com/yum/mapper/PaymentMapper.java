package com.yum.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yum.domain.CartDTO;
import com.yum.domain.OrderDTO;
import com.yum.domain.PaymentDTO;

@Mapper
public interface PaymentMapper {
	// 주문 내역 추가
	public int insertOrder(PaymentDTO params);
	
	// 주문 세부 내역 추가
	public int insertOrderDetail(CartDTO params);
	
	// 주문한 수량만큼 스탬프 추가
	public int insertCoupon(@Param("userNum") Long userNum, @Param("totalQty") Long totalQty);
	
	
	public List<PaymentDTO> selectPaymentList(PaymentDTO params);
	public int selectTotalPrice(int orderNum);
	public int countPaymentList(PaymentDTO params);
	public String selectBranchName(int orderNum);
	public List<OrderDTO> selectOrder(int orderNum);
}
