package com.example.samplewebfluxapp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleRestController {

    @RequestMapping("/")
    public String index(){
        return "Hello, World!";
    }
}
