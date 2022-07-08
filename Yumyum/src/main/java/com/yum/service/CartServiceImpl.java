package com.yum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yum.domain.CartDTO;
import com.yum.mapper.CartMapper;

@Service
public class CartServiceImpl implements CartService{

	@Autowired
	private CartMapper cartMapper;
	
	@Override
	public int insertCart(CartDTO cartdto) {
		
		
		// 장바구니 데이터 체크
		CartDTO checkCart = cartMapper.checkCart(cartdto);
		
		if(checkCart != null) {
			return 2;
		}
		
		// 장바구니 등록 & 에러 시 0반환
		try {
			return cartMapper.insertCart(cartdto);
		} catch (Exception e) {
			return 0;
		}
	};
	
	
}
