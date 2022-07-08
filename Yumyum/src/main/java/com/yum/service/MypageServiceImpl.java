package com.yum.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yum.domain.CouponDTO;
import com.yum.domain.OrderHistoryDTO;
import com.yum.domain.UserDTO;
import com.yum.mapper.MypageMapper;

@Service
public class MypageServiceImpl implements MypageService {
	@Autowired
	private MypageMapper mypageMapper;
		
	
	@Override
	public UserDTO getUserDetail(int userNum) {
		return mypageMapper.selectUserDetail(userNum);
	}
	
	
	@Override
	public int countCoupon(CouponDTO params) {
		int couponTotalCount = mypageMapper.countCoupon(params);
		return couponTotalCount;
	}

	
	@Override
	public List<CouponDTO> getCouponList(CouponDTO params){
		List<CouponDTO> couponList = Collections.emptyList();
		int couponTotalCount = mypageMapper.countCoupon(params);
		if (couponTotalCount > 0) {
			try {
			couponList = mypageMapper.selectCouponList(params);
			} catch(RuntimeException e) {
				System.out.println(e);
			} catch (Exception e){
				System.out.println(e);
			}
		}
		return couponList;
	}
	
	
	@Override
	public List<OrderHistoryDTO> getOrderHistory(int userNum, int period, int firstIndex){
//		public List<OrderHistoryDTO> getOrderHistory(OrderHistoryDTO params){
		List<OrderHistoryDTO> orderHistoryList = Collections.emptyList();
		int orderTotalCount = mypageMapper.countOrder(userNum, period);
		if (orderTotalCount > 0) {
			try {
				orderHistoryList = mypageMapper.selectOrderHistory(userNum, period, firstIndex);
			} catch(RuntimeException e) {
				System.out.println(e);
			} catch (Exception e){
				System.out.println(e);
			}
			
		}
		return orderHistoryList;
	}


	@Override
	public int countOrder(int userNum, int period) {
		int orderTotalCount = mypageMapper.countOrder(userNum, period);
		return orderTotalCount;
	}
	
}
