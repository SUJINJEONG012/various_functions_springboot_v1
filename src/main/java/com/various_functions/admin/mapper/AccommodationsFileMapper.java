package com.various_functions.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.various_functions.admin.dto.AccommodationsFileDto;
import com.various_functions.vo.FileEntity;

@Mapper
public interface AccommodationsFileMapper {

	// 파일 등록 메서드
	void insertAccommodationsFile(AccommodationsFileDto accommodationsFileDto);
	
	//List<FileEntity> findAllByPostId(Long aid);
	
	// 파일 리스트 조회
	List<FileEntity> findAllByIds(List<Long> ids);
	
	// 파일 상세정보 조회(DB에서 조회핧수 있도록)
	FileEntity findById(Long id);
	
	// 파일 삭제
	void deleteAllByIds(List<Long> ids);
	
}
