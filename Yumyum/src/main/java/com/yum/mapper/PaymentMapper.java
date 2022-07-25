package com.yum.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yum.domain.OrderDTO;
import com.yum.domain.PaymentDTO;

@Mapper
public interface PaymentMapper {
	public List<PaymentDTO> selectPaymentList(PaymentDTO params);
	public int selectTotalPrice(int orderNum);
	public int countPaymentList(PaymentDTO params);
	public String selectBranchName(int orderNum);
	public List<OrderDTO> selectOrder(int orderNum);
}
