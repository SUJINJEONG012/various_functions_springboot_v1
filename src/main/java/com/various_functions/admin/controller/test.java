package com.various_functions.admin.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.various_functions.admin.dto.NoticeDto;
import com.various_functions.admin.dto.NoticeFileDto;
import com.various_functions.vo.MemberVo;

public class test {
	
	// 공지사항작성
		@PostMapping("/admin/notice/save")
		public String saveNotice(final NoticeDto noticeDto, Model model, HttpSession session) {
		
			MemberVo member = (MemberVo) session.getAttribute("loginMember");
			if(member == null) {
				return "redirect:/member/login";
			}
		
			// 게시글insert
			Long noticeId = noticeService.noticeSave(noticeDto);

			// 단일 파일 업로드시에도 파일을 리스트에 담아서 전달
			List<MultipartFile> noticeFiles = noticeDto.getFiles();
			
			if(noticeFiles!= null && !noticeFiles.isEmpty()) {
			List<NoticeFileDto> fileList = fileUtils.uploadFiles(noticeFiles);
			noticeFileService.saveFiles(noticeId, fileList); // saveFile 메서드를 사용하여 단일 파일을 저장
			}
			
			return "redirect:/admin/notice/list";
		}v

}
