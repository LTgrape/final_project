package com.example.final_project.controller;

import com.example.final_project.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/boardList")
    public String boardList(){
        return "board/boardList";
    }

    @GetMapping("/boardView")
    public String boardView(){
        return "board/boardView";
    }

    @GetMapping("/boardWrite")
    public String boardWrite(){
        return "board/boardWrite";
    }
}





