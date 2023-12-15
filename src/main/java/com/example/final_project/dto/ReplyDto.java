package com.example.final_project.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ReplyDto {
    private Long replyNumber;
    private String replyContent;
    private String replyRegisterDate;
    private String replyUpdateDate;
    private Long boardNumber;
    private Long userNumber;
}
