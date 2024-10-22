package com.various_functions.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationVo {

	private Long rid; // 예약아이디
	private Long rordernum; // 객실예약번호
	private int ramount;// 인원
	private int rroomnum; // 방개수

	private String rresname;// 예약자이름
	private String rresphone;// 예약자 전화번호
	private String rresemail;// 예약자 이메일
	private Long memberId;// 회원아이디
	private Long roomId;// 객실아이디zz
	private Date payTime;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy. M. d.", timezone = "UTC")
	private Date checkInDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy. M. d.", timezone = "UTC")
	private Date checkOutDate;

	private String resState; // 예약 상태 추가
	private String paymentId; // 결제 ID 추가
	private Long accommodationId;

	private String accommodationName;

}
