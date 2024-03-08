package com.various_functions.admin.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.various_functions.admin.dto.NoticeFileDto;
import com.various_functions.admin.mapper.NoticeFileMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeFileService {	

	private final NoticeFileMapper noticeFileMapper;
	
	@Transactional
	public void saveFile(final Long noticeId, final List<NoticeFileDto> files) {
		if (CollectionUtils.isEmpty(files)) {
            return;
        }
        for (NoticeFileDto file : files) {
            file.setNoticeId(noticeId);
        }
        noticeFileMapper.noticeFilesaveAll(files);
    }
  
	
}
