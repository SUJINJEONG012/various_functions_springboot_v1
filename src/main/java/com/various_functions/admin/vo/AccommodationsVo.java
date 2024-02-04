package com.various_functions.admin.vo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 숙소
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccommodationsVo {
	
	private long aid;
	private String acate;
	private String aname;
	private String aadress;
	private String aphone;
	private String axcoordi;
	private int atotalroom;
	private String agrade;
	private String adetail;
	private String amainimg;
	private LocalDateTime aregdate;
}
