package com.example.sample1app;

import org.springframework.stereotype.Service;

@Service
public class SampleService {

    public Post getPost(){
        return new Post(0, 0, "dummy", "Serviceで作ってみたよ");
    }
}
