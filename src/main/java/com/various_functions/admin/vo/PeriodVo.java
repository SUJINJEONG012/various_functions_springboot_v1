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
	private String peseason;
	private LocalDateTime pestart;
	private long aid;
}
