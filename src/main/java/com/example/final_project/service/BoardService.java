package com.example.final_project.service;

import com.example.final_project.dto.BoardDto;
import com.example.final_project.mapper.BoardMapper;
import com.example.final_project.vo.BoardVo;
import com.example.final_project.vo.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {
    private final BoardMapper boardMapper;
    private final FileService fileService;

    //    등록
    public void register(BoardDto boardDto){
        boardMapper.insert(boardDto);
    }

//    수정
//    트랜잭션을 적용하여 하나의 쿼리에서 오류가 나면 모두 롤백시킨다.
    public void modify(BoardDto boardDto, List<MultipartFile> files) throws IOException {
        fileService.remove(boardDto.getBoardNumber());
        fileService.registerAndSaveFiles(files, boardDto.getBoardNumber());
        boardMapper.update(boardDto);
    }

    //    삭제
    public void remove(Long boardNumber){
        fileService.remove(boardNumber);
        boardMapper.delete(boardNumber);
    }

    //    상세 정보 조회
    @Transactional(readOnly = true)
    public BoardVo find(Long boardNumber){
        return boardMapper.select(boardNumber)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 게시물 번호!"));
    }

    //    리스트 조회
    public List<BoardVo> findAll(Criteria criteria){
        return boardMapper.selectAll(criteria);
    }

    //    총 게시물 수 조회
    public int findTotal(){
        return boardMapper.selectTotal();
    }
}







