package com.example.final_project.controller;

import com.example.final_project.dto.FileDto;
import com.example.final_project.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/files")
public class FileController {
    private final FileService fileService;

    @Value("${file.dir}")
    private String fileDir;

    @GetMapping("/imgs")
    public List<FileDto> imgs(Long boardNumber){
        return fileService.findList(boardNumber);
    }

//    서버 컴퓨터에 저장된 파일을 복사하여 넘겨주는 핸들러 메소드
    @GetMapping("/display")
    public byte[] display(String fileFullName) throws IOException{
        return FileCopyUtils.copyToByteArray(new File(fileDir, fileFullName));
    }

    @GetMapping("/download")
//    HttpServletResponse 와 동일하게 ResponseEntity객체는 응답을 나타내는 객체이다.
//    스프링에서 지원하는 응답객체이며 기존의 응답 객체보다 간편하게 설정할 수 있다는 장점이 있다.
    public ResponseEntity<Resource> download(String fileName) throws UnsupportedEncodingException{
//        Resource객체는 말 그대로 자원을 나타내는 객체이다. 스프링에서 지원하는 타입이다.
//        우리는 이미지 파일이라는 자원을 다룬로드 처리하기 위해 사용하고 있으며 File객체보다
//        많은 종류의 리소스를 다룰 수 있고 스프링과의 호환성이 좋다.
//        Resource는 인터페이스이므로 객체를 만들 때는 자식클래스를 사용하면 된다.

//        즉, JSP에서 HttpServletResponse, File 객체를 사용하여 구현했던 것을
//        스프링에서 지원하는, 스프링과 호환이 잘 되는 객체를 사용하여 처리하는 것이다.
        Resource resource = new FileSystemResource(fileDir + fileName);
        HttpHeaders headers = new HttpHeaders();
        String name = resource.getFilename();
//        uuid를 제거하고 다운받도록 처리한다.
        String resultName = name.substring(name.indexOf("_")+1);
//        Content-Disposition 헤더로 설정하여 클라이언트 브라우저가 첨부파일이라는 것을 알게 함
//        파일 이름을 설정할 때 UTF-8을 설정하여 한글 이슈가 없도록 함
        headers.add(HttpHeaders.CONTENT_DISPOSITION,
                "attachment;filename=" + URLEncoder.encode(resultName, "utf-8"));
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

}










