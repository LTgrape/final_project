package com.example.final_project.service;

import com.example.final_project.dto.UserDto;
import com.example.final_project.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserMapper userMapper;

    //    회원 등록
    public void register(UserDto userDto){
        userMapper.insert(userDto);
    }

    //    회원 번호 조회(아이디, 패스워드)
    public Long findUserNumber(String userId, String userPassword){
        return userMapper.selectUserNumber(userId, userPassword)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원 정보!"));
    }
}








