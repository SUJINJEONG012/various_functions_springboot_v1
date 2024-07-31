package com.various_functions.admin.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResVo {

	private Long rid; //예약아이디
	private Long rordernum; // 객실예약번호
	private int ramount;//인원
	private int rroomnum; //방개수
	private Date recheckin; // 예약시작일
	private Date recheckout; //예약종료일
	private String rresname;//예약자이름
	private String rresphone;//예약자 전화번호
	private String rresemail;//예약자 이메일
	private Long member_id;//회원아이디
	private Long riid;//객실아이디 
	
}
