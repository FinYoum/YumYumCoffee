package com.yum.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentDTO {

	// 주문 번호
	private Long orderNum;
	// 고객 번호
	private Long userNum;
	// 지접 번호
	private Long branchNum;
	// 픽업 여부
	private String pickupYn;
	// 픽업 시간
	private String pickupTime;
	// 주문 시간
	private String orderTime;
	// 결제 금액(총 금액)
	private Long totalPrice;
	
	// 고유 번호
	
	// 주문 고유 번호
	
	// 결제 방법
	
	// 결제 될 금액

	// 결제 시간
	
}
