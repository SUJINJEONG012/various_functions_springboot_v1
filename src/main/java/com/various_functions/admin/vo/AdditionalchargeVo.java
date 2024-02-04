package com.various_functions.admin.vo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 추가요금
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AdditionalchargeVo {

	private int acid;
	private int acpersonnel;
	private int acbreakfast;
	private int acbed;
	private long riid;
}
