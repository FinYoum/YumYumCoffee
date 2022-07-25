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
	
	// 추가 기능
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
	}

	// 장바구니 목록 
	@Override	
	public List<CartDTO> getCartList(Long userNum, Long branchNum) {
		
		List<CartDTO> cartList = Collections.emptyList();
		Long countTotalCart= cartMapper.countTotalCart(userNum, branchNum);
		if (countTotalCart != null ) {
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
	
	
	@Override
	public int addCart(CartDTO cartDTO) {
		return cartMapper.addCart(cartDTO);
	}

	
	@Override
	public CartDTO confirmCart(CartDTO cartDTO) {
		return cartMapper.confirmCart(cartDTO);
	}

	@Override
	public void updateCart(CartDTO cartDTO) {
		 cartMapper.updateCart(cartDTO);		
	}

	
	@Override
	public int updateCart2(CartDTO cartDTO) {
		return  cartMapper.updateCart2(cartDTO);				
	}

	
	@Override
	public List<CartDTO> getCartList(int userNum) {
		return  cartMapper.getCartList(userNum);	
	}

	
	
	@Override
	public int deleteCart2(CartDTO cartDTO) {
		return  cartMapper.deleteCart2(cartDTO);		
	}

	@Override
	public int updatePriceCart2(CartDTO cartDTO) {
		return  cartMapper.updatePriceCart2(cartDTO);		
	};
	
	
}