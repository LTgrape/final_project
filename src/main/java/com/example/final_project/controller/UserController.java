package com.example.final_project.controller;

import com.example.final_project.dto.UserDto;
import com.example.final_project.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    public String login(){
        return "user/login";
    }

    @GetMapping("/join")
    public String join(){
        return "user/join";
    }

//    회원 가입 처리
    @PostMapping("/join")
    public RedirectView join(UserDto userDto){
        userService.register(userDto);
        return new RedirectView("/user/login");
    }

//    로그인 처리
    @PostMapping("/login")
    public RedirectView login(String userId, String userPassword, HttpServletRequest req){
        Long userNumber = userService.findUserNumber(userId, userPassword);
        HttpSession session = req.getSession();
        session.setAttribute("userNumber", userNumber);

        // TODO 2023-12-08 -itj : 보드 리스트 완료후 리다이렉트와 포워드 차이 확인하기
        return new RedirectView("/board/list");
    }
//    로그아웃 처리
    @GetMapping("/logout")
    public RedirectView logout(HttpServletRequest req){
        req.getSession().invalidate();
        return new RedirectView("/user/login");
    }
}







