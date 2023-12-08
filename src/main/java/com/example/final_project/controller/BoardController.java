package com.example.final_project.controller;

import com.example.final_project.service.BoardService;
import com.example.final_project.vo.BoardVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/list")
    public String boardList(Model model){
        List<BoardVo> boardList = boardService.findAll();
        model.addAttribute("boards", boardList);

        return "board/boardList";
    }

    @GetMapping("/view")
    public String boardView(){
        return "board/boardView";
    }

    @GetMapping("/write")
    public String boardWrite(){
        return "board/boardWrite";
    }
}





