package com.example.homeworkshop7.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = {"/index", "/", ""})
public class MyController {
    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "index";
    }
}
