package com.yum.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yum.domain.CartDTO;
import com.yum.mapper.CartMapper;

@Service
public class CartServiceImpl implements CartService{

	@Autowired
	private CartMapper cartMapper;
	
	
	@Override		
	public boolean insertCart(CartDTO params) {
//				
		int queryResult = 0;
// 		장바구니에 데이터가 존재하는지 확인
		CartDTO checkCart = cartMapper.countCartQty(params); 
		
// 		장바구니에 데이터가 없다면, insert
		if(checkCart == null) {
			queryResult = cartMapper.insertCart(params);
		} else{
// 		장바구니에 데이터가 있다면, update 	
			params.setQty(checkCart.getQty()+Long.valueOf(1));
			queryResult = cartMapper.updateCartQty(params);
		}
		return (queryResult == 1) ? true : false;
	}

	// 장바구니 목록 
	@Override	
	public List<CartDTO> getCartList(Long userNum, Long branchNum) {
		
		List<CartDTO> cartList = Collections.emptyList();
		int countTotalCart= cartMapper.countTotalCart(userNum, branchNum);
		if (countTotalCart != 0 ) {
			cartList = cartMapper.selectCartList(userNum, branchNum);
		}
		
		return cartList;
	}
	
	// 제품 수량 업데이트
	@Override
	public boolean updateCartQty(CartDTO params) {
		int queryResult = 0;
		
		if (params!= null) {
			queryResult = cartMapper.updateCartQty(params);
		} 
		return (queryResult == 1) ? true : false;
	}

	// 삭제 기능
	@Override
	public boolean deleteCart(CartDTO params) {
		int queryResult = 0;
		
		if (params!= null) {
			queryResult = cartMapper.deleteCart(params);
		} 
		return (queryResult == 1) ? true : false;
	}
	
	
}
