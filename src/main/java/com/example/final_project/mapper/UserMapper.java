package com.example.final_project.mapper;

import com.example.final_project.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
//    회원 등록
    void insert(UserDto userDto);
//    회원 번호 조회(아이디, 패스워드)
    Long selectUserNumber(String userId, String userPassword);
}








