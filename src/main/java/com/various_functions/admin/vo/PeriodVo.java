package com.various_functions.admin.vo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 기간
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PeriodVo {
	private long peid;
	private String peseason; //시즌 : 비수기/성수기/준성수기
	private LocalDateTime pestart; // 시작날짜
	private LocalDateTime peend; // 끝날짜
	private long aid;
}
