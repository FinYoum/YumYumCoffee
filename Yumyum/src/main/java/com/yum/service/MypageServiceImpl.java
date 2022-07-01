package com.yum.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yum.domain.CouponDTO;
import com.yum.domain.OrderDTO;
import com.yum.domain.UserDTO;
import com.yum.mapper.MypageMapper;

@Service
public class MypageServiceImpl implements MypageService {
	@Autowired
	private MypageMapper mypageMapper;
	
	
	@Override
	public OrderDTO getOrderDetail(int num, int date) {
		return mypageMapper.selectOrderDetail(num, date) ;
	}
	
	
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
	
}
