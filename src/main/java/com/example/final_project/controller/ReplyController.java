package com.example.final_project.controller;

import com.example.final_project.dto.ReplyDto;
import com.example.final_project.service.ReplyService;
import com.example.final_project.vo.Criteria;
import com.example.final_project.vo.PageVo;
import com.example.final_project.vo.ReplyVo;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/replies")
public class ReplyController {
    private final ReplyService replyService;

    @PostMapping
    public void write(@RequestBody ReplyDto replyDto,
                      HttpServletRequest req){
        Long userNumber = (Long) req.getSession().getAttribute("userNumber");

        replyDto.setUserNumber(userNumber);
        System.out.println("replyDto = " + replyDto);
        replyService.register(replyDto);
    }

//    url로 데이터를 넘겨받아 조회가 가능하다.
//    경로상의 데이터를 받기 위해서는 @PathVariable 을 사용한다.
    @GetMapping("/list/{boardNumber}/{page}")
    public Map<String, Object> replyList(@PathVariable("boardNumber") Long boardNumber,
                                   @PathVariable("page") int page){
        Criteria criteria = new Criteria(page, 10);
        int total = replyService.findTotal(boardNumber);

        PageVo pageVo = new PageVo(total, criteria);
        List<ReplyVo> replyList = replyService.findListPage(criteria, boardNumber);

        Map<String, Object> replyMap= new HashMap<>();
        replyMap.put("pageVo", pageVo);
        replyMap.put("replyList", replyList);

        return replyMap;
    }

    @PatchMapping("/{replyNumber}")
    public void replyModify(@PathVariable("replyNumber") Long replyNumber,
                            @RequestBody ReplyDto replyDto){
        System.out.println("replyNumber = " + replyNumber + ", replyDto = " + replyDto);
        replyDto.setReplyNumber(replyNumber);
        replyService.modify(replyDto);
    }

    @DeleteMapping("/{replyNumber}")
    public void replyRemove(@PathVariable("replyNumber") Long replyNumber) {
        replyService.remove(replyNumber);
    }
}









