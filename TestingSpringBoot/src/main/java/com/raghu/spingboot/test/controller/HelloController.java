package com.raghu.spingboot.test.controller;

import com.raghu.spingboot.test.service.NameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HelloController {
    @Autowired
    private NameService nameService;

    @GetMapping
    @ResponseBody
    public String hello() {
        return "Hello " + nameService.getName();
    }
}
