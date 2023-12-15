package com.example.final_project.service;

import com.example.final_project.dto.ReplyDto;
import com.example.final_project.mapper.ReplyMapper;
import com.example.final_project.vo.Criteria;
import com.example.final_project.vo.ReplyVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyMapper replyMapper;

    public void register(ReplyDto replyDto){
        replyMapper.insert(replyDto);
    }

    public List<ReplyVo> findList(Long boardNumber){
        return replyMapper.selectList(boardNumber);
    }

    public void modify(ReplyDto replyDto){
        replyMapper.update(replyDto);
    }

    public void remove(Long replyNumber){
        replyMapper.delete(replyNumber);
    }

    public List<ReplyVo> findListPage(Criteria criteria, Long boardNumber){
        return replyMapper.selectListPage(criteria, boardNumber);
    }

    public int findTotal(Long boardNumber) {
        return replyMapper.selectTotal(boardNumber);
    }
}










