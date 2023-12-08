package com.example.final_project.service;

import com.example.final_project.dto.UserDto;
import com.example.final_project.mapper.UserMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock UserMapper userMapper;

    @InjectMocks UserService userService;

    UserDto userDto;

    @BeforeEach
    void setUp() {
        userDto = new UserDto();
        userDto.setUserId("aaa");
        userDto.setUserPassword("1234");
        userDto.setUserGender("M");
        userDto.setUserEmail("aaa@naver.com");
        userDto.setUserAddress("서울시");
    }

    @Test
    void register() {
        // given
        doNothing().when(userMapper).insert(any());
        // when
        userService.register(userDto);
        // then
        verify(userMapper, times(1)).insert(any());
    }

    @Test
    void findUserNumber() {
        // given
        doReturn(1L).when(userMapper).selectUserNumber(any(), any());
        // when
        Long foundNumber = userService.findUserNumber("test", "1234");
        // then
        assertThat(foundNumber).isEqualTo(1L);
    }

}







