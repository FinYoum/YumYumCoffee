package com.yum.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentDTO {

	// 주문 번호
	private int orderNum;
	// 고객 번호
	private int userNum;
	// 지접 번호
	private int branchNum;
	// 픽업 여부
	private String pickupYn;
	// 주문 시간
	private String orderTime;
	// 결제 금액(총 금액)
	private int totalPrice;
}
