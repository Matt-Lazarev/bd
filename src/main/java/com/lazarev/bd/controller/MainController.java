package com.lazarev.bd.controller;

import com.lazarev.bd.repository.MainRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class MainController {

    private final MainRepository repository;

    @GetMapping("/login")
    public String getLoginPage(){
        return "registration/login";
    }
}
