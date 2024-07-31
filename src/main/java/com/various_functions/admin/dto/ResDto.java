package com.various_functions.admin.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ResDto {
	
	private Long rid; // 예약아이디
    private Long rordernum; // 객실예약번호
    private int ramount; // 인원
    private int rroomnum; // 방 개수
    private Date checkin; // 예약시작일
    private Date checkout; // 예약종료일
    private String resName; // 예약자 이름
    private String resPhone; // 예약자 전화번호
    private String resEmail; // 예약자 이메일
    private Long memberId; // 회원아이디
    private Long roomId; // 객실아이디
    private int totalAmount; // 총 금액 (비즈니스 로직에서 계산)
}
