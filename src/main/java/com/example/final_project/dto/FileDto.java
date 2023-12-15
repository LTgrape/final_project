package com.example.final_project.dto;

import lombok.Data;
import org.springframework.stereotype.Component;
//FILE_NUMBER NUMBER,
//FILE_NAME VARCHAR2(500),
//FILE_UPLOAD_PATH VARCHAR2(500),
//FILE_UUID VARCHAR2(1000),
//BOARD_NUMBER NUMBER,
@Component
@Data
public class FileDto {
    private Long fileNumber;
    private String fileName;
    private String fileUploadPath;
    private String fileUuid;
    private Long boardNumber;
}






