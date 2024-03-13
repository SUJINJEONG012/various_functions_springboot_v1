package com.various_functions.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.various_functions.admin.dto.AccommodationsFileDto;
import com.various_functions.admin.mapper.AccommodationsFileMapper;

@Service
public class AccommodationsFileService {

	@Autowired
	private AccommodationsFileMapper accommodationsFileMapper;
	
	public void UploadFile(AccommodationsFileDto accommodationsFileDto) {
		// 파일 업로드 로직
		accommodationsFileMapper.insertAccommodationsFile(accommodationsFileDto);
	}

}
