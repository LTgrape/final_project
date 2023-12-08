package com.example.final_project.service;

import com.example.final_project.dto.BoardDto;
import com.example.final_project.mapper.BoardMapper;
import com.example.final_project.vo.BoardVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardMapper boardMapper;

    //    등록
    public void register(BoardDto boardDto){
        boardMapper.insert(boardDto);
    }

    //    수정
    public void modify(BoardDto boardDto){
        boardMapper.update(boardDto);
    }

    //    삭제
    public void remove(Long boardNumber){
        boardMapper.delete(boardNumber);
    }

    //    상세 정보 조회
    public BoardVo find(Long boardNumber){
        return Optional.ofNullable(boardMapper.select(boardNumber))
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 게시물 번호!"));
    }

    //    리스트 조회
    public List<BoardVo> findAll(){
        return boardMapper.selectAll();
    }
}







