package com.yum.domain;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDTO {
//	주문일자
	LocalDateTime orderDate;
//	주문내역
	String orderDetail;
//	결제금액
	int totalPrice;
//	주문지점
	String branch;
		
}
