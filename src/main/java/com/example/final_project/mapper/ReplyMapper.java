package com.example.final_project.mapper;

import com.example.final_project.dto.ReplyDto;
import com.example.final_project.vo.Criteria;
import com.example.final_project.vo.ReplyVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReplyMapper {
    void insert(ReplyDto replyDto);
    List<ReplyVo> selectList(Long boardNumber);
    void update(ReplyDto replyDto);
    void delete(Long replyNumber);

    List<ReplyVo> selectListPage(@Param("criteria") Criteria criteria,
                                 @Param("boardNumber") Long boardNumber);
    int selectTotal(Long boardNumber);
}












