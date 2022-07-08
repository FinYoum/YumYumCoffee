package com.yum;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yum.domain.CartDTO;
import com.yum.mapper.CartMapper;

public class CartMapperTests {
	@Autowired
	private CartMapper cartMapper;

//	@Test // insert
//	public void addCartTest() {
//		// given
//		int cartNum = 4;
//		int userNum = 0;
//		int productNum = 1;
//		int qty = 22;
//
//		CartDTO dto = new CartDTO();
//		dto.setCartNum(cartNum);
//		dto.setUserNum(userNum);
//		dto.setProductNum(productNum);
//		dto.setQty(qty);
//
//		// when
//		int result = cartMapper.insertCart(dto);
//		System.out.println(result);
//
//	}

	@Test // update qty
	public void modifyCartTest() {
		int cartNum = 4;
		int count = 7;

		CartDTO cart = new CartDTO();
		cart.setCartNum(cartNum);
		cart.setQty(count);

		cartMapper.updateCart(cart);

	}

	@Test // delete
	public void deleteCartTest() {
		int cartNum = 4;

		cartMapper.deleteCart(cartNum);
	}
	
	
//	@Test // cart list
//	public void getCartTest() {
//		int userNum = 33;
//		
//		
//		List<CartDTO> list = cartMapper.getCart(userNum);
//		for(CartDTO cart : list) {	
//			System.out.println(cart);
//			cart
//			System.out.println("init cart : " + cart);
//		}
		
		@Test
		public void checkCartTest() {
			
			int userNum = 33;
			int productNum = 71;
			
			CartDTO cart = new CartDTO();
			cart.setUserNum(userNum);
			cart.setProductNum(productNum);
			
			CartDTO resutlCart = cartMapper.checkCart(cart);
			System.out.println("결과 : " + resutlCart);
			
		}

}
