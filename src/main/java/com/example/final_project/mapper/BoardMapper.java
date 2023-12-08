package com.example.final_project.mapper;

import com.example.final_project.dto.BoardDto;
import com.example.final_project.vo.BoardVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    //    등록
    public void insert(BoardDto boardDto);

    //    수정
    public void update(BoardDto boardDto);

    //    삭제
    public void delete(Long boardNumber);

    //    상세 정보 조회
    public BoardVo select(Long boardNumber);

    //    리스트 조회
    public List<BoardVo> selectAll();
}












