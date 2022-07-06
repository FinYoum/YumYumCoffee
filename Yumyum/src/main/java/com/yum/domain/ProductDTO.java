package com.yum.domain;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
	
	//상품 번호	
	private Long productNum;
	
	//상품코드
	private String codeId;
	
	//상품이름
	private String name;
	
	//상품 가격
	private int price;
	
	//상품정보
	private String info;
	
	//상품이미지
	private String img;
	

}
