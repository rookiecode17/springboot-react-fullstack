package com.raven.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PagesController {

    @RequestMapping({ "/", "/add", "/add/**" })
    public String redirect() {
        return "forward:/index.html";
    }
}
