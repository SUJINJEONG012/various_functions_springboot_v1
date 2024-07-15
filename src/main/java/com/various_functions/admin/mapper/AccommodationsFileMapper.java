package com.various_functions.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.various_functions.admin.dto.AccommodationsFileDto;
import com.various_functions.admin.vo.AccommodationsFileVo;
import com.various_functions.admin.vo.AccommodationsVo;

@Mapper
public interface AccommodationsFileMapper {
	
	// 파일저장
	void accommodationFileSaveAll(List<AccommodationsFileDto> accommodationsFiles);
	// 단일파일저장
	void accommodationSave(AccommodationsFileDto accommodationsFileDto);
	
	// 이미지 데이터 변환
	List<AccommodationsFileVo> findFileByAccommodationId(Long AccommodationId);

	/*
	 * 파일리스트 조회
	 * @Param - accommodationId - 게시글 조회
	 * return 파일리스트
	 * */
	List<AccommodationsFileVo> findAllByIds(List<Long> ids);
	
	/* 파일 삭제 */
	void deleteById(Long afId);
	
	/* 파일 상세 조회 */
	AccommodationsFileVo findById(Long afId);
	
	
}

