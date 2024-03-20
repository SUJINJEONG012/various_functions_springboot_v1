package com.various_functions.admin.vo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 결제
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentVo {

	private Long pid;
	private String pmethod;
	private LocalDateTime pdate;
	private Long ptotal;
	private String prefund; //한불시 : y
	private String ptoken;
	private Long riid;
	private Long aid;
	
}
