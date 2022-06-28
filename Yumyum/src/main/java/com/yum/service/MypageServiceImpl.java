package com.yum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yum.domain.CouponDTO;
import com.yum.domain.OrderDTO;
import com.yum.mapper.MypageMapper;

@Service
public class MypageServiceImpl implements MypageService {
	@Autowired
	private MypageMapper mypageMapper;
	
	@Override
	public CouponDTO getCouponDetail(int num) {
		return mypageMapper.selectCouponDetail(num);
	}
	@Override
	public OrderDTO getOrderDetail(int num, int date) {
		return mypageMapper.selectOrderDetail(num, date) ;
	}

	
}
