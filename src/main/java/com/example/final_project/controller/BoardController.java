package com.example.final_project.controller;

import com.example.final_project.dto.BoardDto;
import com.example.final_project.service.BoardService;
import com.example.final_project.service.FileService;
import com.example.final_project.vo.BoardVo;
import com.example.final_project.vo.Criteria;
import com.example.final_project.vo.PageVo;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.List;

@Controller @Slf4j
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    private final FileService fileService;

    @GetMapping("/list")
    public String boardList(Criteria criteria, Model model){
        List<BoardVo> boardList = boardService.findAll(criteria);
        model.addAttribute("boards", boardList);
        model.addAttribute("pageInfo", new PageVo(boardService.findTotal(), criteria));

        return "board/boardList";
    }

    @GetMapping("/view")
    public String boardView(Long boardNumber, Model model){
        log.info("boardNumber : {}", boardNumber);
        BoardVo boardVo = boardService.find(boardNumber);
        model.addAttribute("board", boardVo);
        return "board/boardView";
    }

//    @GetMapping("/write")
//    public String boardWrite(HttpServletRequest req){
//        Long userNumber = (Long) req.getSession().getAttribute("userNumber");
//        return userNumber == null ? "user/login" : "board/boardWrite";
//    }

    @GetMapping("/write")
    public String boardWrite(@SessionAttribute(value = "userNumber", required = false) Long userNumber){
        return userNumber == null ? "user/login" : "board/boardWrite";
    }

//    글 작성 완료
    @PostMapping("/write")
    public RedirectView boardWrite(BoardDto boardDto,
                                   @SessionAttribute("userNumber") Long userNumber,
                                   RedirectAttributes redirectAttributes,
                                   @RequestParam("boardFile") List<MultipartFile> files){
//        RedirectAttributes가 리다이렉트용 Model이라고 생각하면 된다.
        boardDto.setUserNumber(userNumber);
        boardService.register(boardDto);
        Long boardNumber = boardDto.getBoardNumber();

        try {
            fileService.registerAndSaveFiles(files, boardNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }


//        리다이렉트로 화면을 전환할 때 데이터를 가지고 가고 싶다면
//        1. 쿼리스트링 : 컨트롤러로 데이터를 전송하고 싶을 때
//        redirectAttributes.addAttribute("boardNumber", boardDto.getBoardNumber());
//        2. Flash : 화면으로 데이터를 전송하고 싶을 때
        redirectAttributes.addFlashAttribute("boardNumber", boardDto.getBoardNumber());

        return new RedirectView("/board/list");
    }

    @GetMapping("/modify")
    public String modify(Long boardNumber, Model model){
        BoardVo boardVo = boardService.find(boardNumber);
        model.addAttribute("board", boardVo);
        return "board/boardModify";
    }

    @PostMapping("/modify")
    public RedirectView modify(BoardDto boardDto,
                               RedirectAttributes redirectAttributes,
                               @RequestParam("boardFile") List<MultipartFile> files){
        try {
            boardService.modify(boardDto, files);
        } catch (IOException e) {
            e.printStackTrace();
        }
        redirectAttributes.addAttribute("boardNumber", boardDto.getBoardNumber());
        return new RedirectView("/board/view");
    }

    @GetMapping("/remove")
    public RedirectView remove(Long boardNumber){
        boardService.remove(boardNumber);
        return new RedirectView("/board/list");
    }
}





