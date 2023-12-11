package com.example.final_project.mapper;

import com.example.final_project.dto.BoardDto;
import com.example.final_project.vo.BoardVo;
import com.example.final_project.vo.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BoardMapper {
    //    등록
    public void insert(BoardDto boardDto);

    //    수정
    public void update(BoardDto boardDto);

    //    삭제
    public void delete(Long boardNumber);

    //    상세 정보 조회
    public Optional<BoardVo> select(Long boardNumber);

    //    리스트 조회
    public List<BoardVo> selectAll(Criteria criteria);

//    총 게시물 수 조회
    public int selectTotal();
}












