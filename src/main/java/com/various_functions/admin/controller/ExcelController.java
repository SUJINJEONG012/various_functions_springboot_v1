package com.various_functions.admin.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.sl.usermodel.Sheet;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExcelController {

	@GetMapping("/downloadExcel")
    public void downloadExcel(HttpServletResponse response) throws IOException {
        // 엑셀 파일 생성
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Notice Data");

        // 데이터 조회 및 엑셀에 쓰기
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("제목");
        headerRow.createCell(1).setCellValue("내용");
        // ... 추가적인 열들 추가

        // 엑셀 파일 다운로드 설정
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=notice_data.xlsx");

        // 엑셀 파일 출력
        workbook.write(response.getOutputStream());
        workbook.close();
    }
}
