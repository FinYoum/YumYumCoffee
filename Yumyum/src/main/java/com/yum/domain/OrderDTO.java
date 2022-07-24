package com.yum.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDTO {

	//주문 번호
	private int orderNum;
	//물품 번호
	private int productNum;
	//제품명
	private String name;
	//가격
	private int price;
	//갯수
	private int ea;
	//메뉴 커스텀
	private String shotCustom;
	//이미지 경로
	private String imgPath;
//	//픽업여부
//	private String pickupYn;
	
}
