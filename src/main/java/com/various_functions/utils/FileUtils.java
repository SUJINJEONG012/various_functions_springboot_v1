package com.various_functions.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.various_functions.admin.dto.AccommodationsFileDto;
import com.various_functions.admin.dto.NoticeFileDto;
import com.various_functions.admin.vo.AccommodationsFileVo;
import com.various_functions.admin.vo.NoticeFileVo;

/*
 * 
 * 파일업로드/다운로드 사용하기 위함
 * @Component - 개발자가 직접 정의한 클래스를 빈으로 등록할 때 
 * */
@Component
public class FileUtils {

	// 단일파일 업로드
	//private final String singuploadPath = Paths.get("C:", "Users", "NCIN","single-upload-files").toString();
	// 다중파일 업로드
	// private final String multuploadPath = Paths.get("C:", "Users", "NCIN","mult-upload-files").toString();

	/*
	 * uploadPath 물리적으로 파일을 저장할 위치 보통 OS별 디렉터리 경로를 구분할 때 File.separator를 이용하고는 하는데요.
	 * Paths.get( )을 이용하면 OS에 상관없이 디렉터리 경로를 구분할 수 있다.
	 */
	private final String singuploadPath = Paths.get("/Users", "jeongsujin", "upload").toString();

	/**
	 * 다중 파일 업로드
	 * 
	 * @param multipartFiles - 파일 객체 List
	 * @return DB에 저장할 파일 정보 List
	 */
	public List<NoticeFileDto> uploadFiles(final List<MultipartFile> multipartFiles) {
		// 여러 이미지를 담는 객체
		List<NoticeFileDto> filesff = new ArrayList<>();

		for (MultipartFile multipartFile : multipartFiles) {
			if (multipartFile.isEmpty()) {
				continue;
			}

			filesff.add(uploadFile(multipartFile));
		}
		return filesff;
	}

	public List<AccommodationsFileDto> uploadFileAccommodations(final List<MultipartFile> multipartFiles) {

		// 여러 이미지를 담는 객체
		List<AccommodationsFileDto> filesAccommodation = new ArrayList<>();

		for (MultipartFile multipartFile : multipartFiles) {
			if (multipartFile.isEmpty()) {
				continue;
			}

			filesAccommodation.add(uploadFileAccommodation(multipartFile));
		}
		return filesAccommodation;
	}

	/**
	 * 단일 파일 업로드
	 * 
	 * @param multipartFile - 파일 객체
	 * @return DB에 저장할 파일 정보
	 */
	public NoticeFileDto uploadFile(final MultipartFile multipartFile) {
		if (multipartFile.isEmpty()) {
			return null;
		}
		// 파일이름
		String saveName = generateSaveFilename(multipartFile.getOriginalFilename());
		String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd")).toString();
		String uploadPath = getSingUploadPath(today) + File.separator + saveName; // 변경된 부분

		// 파일위치
		File uploadFile = new File(uploadPath);

		/*
		 * transferTo() 경우 IOException와 IllegalStateException을 일으킬 가능성이 있기 때문에 컴파일러에서
		 * try catch문을 사용하라는 경구 문이 뜹니다. 따라서 파일을 저장하는 코드를 try catch문
		 */
		try {

			multipartFile.transferTo(uploadFile);

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return NoticeFileDto.builder().originalName(multipartFile.getOriginalFilename()).saveName(saveName)
				.size((int) multipartFile.getSize()).build();
	}

	/**
	 * 단일 파일 업로드
	 * 
	 * @param multipartFile - 파일 객체
	 * @return DB에 저장할 파일 정보
	 */
	public AccommodationsFileDto uploadFileAccommodation(final MultipartFile multipartFile) {
		if (multipartFile.isEmpty()) {
			return null;
		}
		// 파일이름
		String saveName = generateSaveFilename(multipartFile.getOriginalFilename());
		String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd")).toString();
		String uploadPath = getSingUploadPath(today) + File.separator + saveName; // 변경된 부분

		// 파일위치
		File uploadFile = new File(uploadPath);

		/*
		 * transferTo() 경우 IOException와 IllegalStateException을 일으킬 가능성이 있기 때문에 컴파일러에서
		 * try catch문을 사용하라는 경구 문이 뜹니다. 따라서 파일을 저장하는 코드를 try catch문
		 */
		try {

			multipartFile.transferTo(uploadFile);

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return AccommodationsFileDto.builder().originalName(multipartFile.getOriginalFilename()).saveName(saveName)
				.size((int) multipartFile.getSize()).build();
	}

	/**
	 * 저장 파일명 생성
	 * 
	 * @param filename 원본 파일명
	 * @return 디스크에 저장할 파일명
	 */
	public String generateSaveFilename(final String filename) {
		// uuid 적용 파일 이름
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		String extension = StringUtils.getFilenameExtension(filename);
		return uuid + "." + extension;
	}

	/**
	 * 업로드 경로 반환
	 * 
	 * @return 업로드 경로
	 */
	public String getSingUploadPath() {
		return makeDirectories(singuploadPath);
	}

	/**
	 * 업로드 경로 반환
	 * 
	 * @param addPath - 추가 경로
	 * @return 업로드 경로
	 */
	public String getSingUploadPath(final String addPath) {
		return makeDirectories(singuploadPath + File.separator + addPath);
	}

	/**
	 * 업로드 폴더(디렉터리) 생성
	 * 
	 * @param path - 업로드 경로
	 * @return 업로드 경로 디렉토리가 존재하는지 유무를반환하는 exists()메서드를 활용하여 if문 작성
	 */
	private String makeDirectories(final String path) {
		File dir = new File(path);
		if (dir.exists() == false) {
			dir.mkdirs();
		}
		return dir.getPath();
	}

	/**
	 * 첨부파일(리소스) 조회 (as Resource), 다운로드, 출력
	 * 
	 * @param file - 첨부파일 상세정보
	 * @return 첨부파일(리소스)
	 */
	public Resource readFileAsResource(final NoticeFileVo file) {

		String uploadedDate = file.getCreatedDate().toLocalDate().format(DateTimeFormatter.ofPattern("yyMMdd"));
		String filename = file.getSaveName();

		// 절대경로 생성
		Path filePath = Paths.get(singuploadPath, uploadedDate, filename);

		try {
			// UrlResource 객체생성 파일에 접근할 uri생성
			Resource resource = new UrlResource(filePath.toUri());
			if (resource.exists() == false || resource.isFile() == false) {
				throw new RuntimeException("file not found : " + filePath.toString());
			}
			return resource;

		} catch (MalformedURLException e) {
			throw new RuntimeException("file not found :" + filePath.toString());
		}

	}

	public Resource readFileAsResource(final AccommodationsFileVo file) {

		String uploadedDate = file.getCreatedDate().toLocalDate().format(DateTimeFormatter.ofPattern("yyMMdd"));
		String filename = file.getSaveName();

		// 절대경로 생성
		Path filePath = Paths.get(singuploadPath, uploadedDate, filename);

		try {
			// UrlResource 객체생성 파일에 접근할 uri생성
			Resource resource = new UrlResource(filePath.toUri());
			if (resource.exists() == false || resource.isFile() == false) {
				throw new RuntimeException("file not found : " + filePath.toString());
			}
			return resource;

		} catch (MalformedURLException e) {
			throw new RuntimeException("file not found :" + filePath.toString());
		}

	}

	// 저장된 파일을 가져오는 메서드
	public Resource loadFileAsResource(String fileName) throws MalformedURLException, FileNotFoundException {
		Path filePath = Paths.get(singuploadPath).resolve(fileName).normalize();
		Resource resource = new UrlResource(filePath.toUri());

		if (resource.exists()) {
			return resource;
		} else {
			throw new FileNotFoundException("파일을 찾을 수 없습니다: " + fileName);
		}
	}

	
}
