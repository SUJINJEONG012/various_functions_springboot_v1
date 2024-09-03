package com.various_functions.admin.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDto {
	
	private Long rid; //예약아이디
	private Long rordernum; // 객실예약번호
	private int ramount;//인원
	private int rroomnum; //방개수
	private Date rcheckin; // 예약시작일
	private Date rcheckout; //예약종료일
	private String rresname;//예약자이름
	private String rresphone;//예약자 전화번호
	private String rresemail;//예약자 이메일
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy. M. d.", timezone="UTC")
	private Date checkInDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy. M. d.", timezone="UTC")
    private Date checkOutDate;
	
	private Long memberId;//회원아이디
	private Long roomId;//객실아이디zz
}
