package com.example.springedu2.controller;

import com.example.springedu2.entity.Visitor;
import com.example.springedu2.repository.VisitorRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class VisitorController {

    // 1번) @Autowired 대신 생성자 주임
    // @Autowired
    // private VisitorRepository visitorRepository;

    // 2번) 생성자 주임 : 요즘 방식
    //    private VisitorRepository visitorRepository;
    //    public VisitorController(VisitorRepository visitorRepository) {
    //        this.visitorRepository = visitorRepository;
    //    }

    // 3) 생성자 주임 다른 방법
    // @RequiredArgsConstrunctor 필수 : Lombok 필수
    private final VisitorRepository visitorRepository;


    @GetMapping("/vlist")
    public ModelAndView vlist(){
        return null;
    }

    @GetMapping("/vsearch")
    public ModelAndView vsearch() {
        return null;
    }

    // @Valid : form 태그에서 넘어온 자료를 @Entity 에 있는 설정
    // 설정(@Id, @NotBlank, @Column(nullable=false)
    // 과 비교해서 입력 data 를 검증한다
    @PostMapping("/vinsert")
    @Transactional
    public String vinsert(@Valid Visitor visitor
            , BindingResult bindingResult
            , Model model) {

        System.out.println("visitor:" + visitor);
        System.out.println("bindingResult:" + bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("msg", "이름과 내용을 모두 입력하세요");
            return "visitorView";
        }
        visitorRepository.save(visitor); // entity type

        return "redirect:/vlist";
    }

}
