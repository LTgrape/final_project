package com.example.final_project.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ReplyVo {
    private Long replyNumber;
    private String replyContent;
    private String replyRegisterDate;
    private String replyUpdateDate;
    private Long boardNumber;
    private Long userNumber;
    private String userId;
}
