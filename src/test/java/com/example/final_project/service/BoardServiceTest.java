package com.example.final_project.service;

import com.example.final_project.dto.BoardDto;
import com.example.final_project.mapper.BoardMapper;
import com.example.final_project.vo.BoardVo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BoardServiceTest {
    @Mock
    BoardMapper boardMapper;

    @InjectMocks
    BoardService boardService;

    BoardDto boardDto;
    BoardVo boardVo;

    @BeforeEach
    void setUp() {
        boardDto = new BoardDto();
        boardDto.setBoardTitle("test title");

        boardVo = new BoardVo();
        boardVo.setBoardTitle("test title");
    }

    @Test
    void register() {
        //given
        doNothing().when(boardMapper).insert(any(BoardDto.class));
        //when
        boardService.register(boardDto);
        //then
        verify(boardMapper, times(1)).insert(any());
    }

//    @Test
//    void modify() {
//        // given
//        doNothing().when(boardMapper).update(any());
//        // when
//        boardService.modify(boardDto);
//        // then
//        verify(boardMapper, times(1)).update(any());
//    }

    @Test
    void remove() {
        // given
        doNothing().when(boardMapper).delete(any(Long.class));
        // when
        boardService.remove(1L);
        // then
        verify(boardMapper, times(1)).delete(any());
    }

    @Test
    void find() {
//        given
        doReturn(boardVo).when(boardMapper).select(any());
//        when
        BoardVo foundBoard = boardService.find(1L);
//        then
        assertThat(foundBoard).isEqualTo(boardVo);
    }

//    @Test
//    void findAll() {
//        // given
//        doReturn(List.of(boardVo)).when(boardMapper).selectAll();
//        // when
//        List<BoardVo> boardList = boardService.findAll();
//        // then
//        boardList.contains(boardVo);
//    }
}