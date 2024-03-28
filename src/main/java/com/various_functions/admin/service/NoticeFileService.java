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
	
	// 다중파일 업로드
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

	// 파일저장
	@Transactional
	public void saveFile(final Long noticeId, final NoticeFileDto file) {
		if(file == null) {
			return;
		}
		file.setNoticeId(noticeId);
		noticeFileMapper.noticeFileSave(file);
	}
	

	
	
}
