package com.pot.app.jenkinsexample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JenkisController {

    @GetMapping("/")
    public String getHello(){
        return "Hello Jenkins";
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}
