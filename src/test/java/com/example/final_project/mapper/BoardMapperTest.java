package com.example.final_project.mapper;

import com.example.final_project.dto.BoardDto;
import com.example.final_project.dto.UserDto;
import com.example.final_project.vo.BoardVo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BoardMapperTest {
    @Autowired BoardMapper boardMapper;
    @Autowired UserMapper userMapper;

    BoardDto boardDto;
    UserDto userDto;

    @BeforeEach
    void setUp() {
        userDto = new UserDto();
        userDto.setUserId("test");
        userDto.setUserPassword("1234");
        userDto.setUserAddress("강남구");
        userDto.setUserEmail("test@naver.com");
        userDto.setUserGender("M");
        userMapper.insert(userDto);

        boardDto = new BoardDto();
        boardDto.setBoardTitle("test title");
        boardDto.setBoardContent("test content");
        boardDto.setUserNumber(userDto.getUserNumber());
    }


    @Test
    void update() {
        // given
            boardMapper.insert(boardDto);
            boardDto.setBoardTitle("update title");
            boardDto.setBoardContent("update content");
        // when
            boardMapper.update(boardDto);
        BoardVo foundBoard = boardMapper.select(boardDto.getBoardNumber());
        // then
        assertThat(foundBoard.getBoardTitle()).isEqualTo(boardDto.getBoardTitle());
    }

    @Test
    void delete() {
        // given
        boardMapper.insert(boardDto);
        // when
        boardMapper.delete(boardDto.getBoardNumber());
        // then
        BoardVo foundBoard = boardMapper.select(boardDto.getBoardNumber());
        assertThat(foundBoard).isNull();
    }

    @Test
    void selectAll() {
        // given
        boardMapper.insert(boardDto);
        // when
        List<BoardVo> boardList = boardMapper.selectAll();
        // then
        assertThat(boardList).isNotEmpty();
    }
}