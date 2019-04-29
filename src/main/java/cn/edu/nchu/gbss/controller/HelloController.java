package cn.edu.nchu.gbss.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String HelloWorld() {
        return "Hello World!";
    }
}