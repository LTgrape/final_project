package com.example.final_project.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Criteria {
    private int page; // 현재 페이지
    private int amount; // 한 페이지 당 게시물 수

//    컨트롤러의 매개변수로 Criteria 객체를 사용하면
//    스프링 컨테이너가 Criteria객체를 new Criteria() 하여 주입해준다.
//    이 때 사용되는 생성자는 기본 생성자이므로
//    우리가 기본생성자에 page와 amount의 기본 값을 설정해주면 첫 페이지를 볼 수 있다.

    public Criteria() {
        this.page = 1;
        this.amount = 12;
    }
}














