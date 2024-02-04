package com.various_functions.admin.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 예약

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationVo {

	private long rid;
	private long rordernum;
	private int ramount;
	private int rroomnum;
	private String rcheckin;
	private String rcheckout;
	private String rresname;
	private String rresphone;
	private String rresemail;
	private long member_id;
	private long riid;
	
}
