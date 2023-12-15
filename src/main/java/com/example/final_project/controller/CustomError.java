package com.example.final_project.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomError implements ErrorController {
    @GetMapping("/error")
    public String error(HttpServletRequest req) {
//        HTTP상태 코드를 req에게 받을 수 있다.
//        상태 코드를 얻기 위한 key를 우리가 외어서 쓸 수 없으므로
//        RequestDispatcher가 가진 상수를 이용하여 가져온다.
        Object attribute = req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        
        if (attribute != null) {
            int statusCode = Integer.parseInt(attribute.toString());
            if (statusCode == HttpStatus.NOT_FOUND.value()){
                return "error/404"; // 404 : 요청 페이지를 찾을 수 없음
            }
        }
        
        return "error/500"; // 500 : 서버 오류
    }
}
