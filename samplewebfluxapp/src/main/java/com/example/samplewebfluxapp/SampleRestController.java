package com.example.samplewebfluxapp;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;

@RestController
public class SampleRestController {
    @Autowired
    PostRepository repository;

    @PostConstruct
    public void init(){
        Post p1 = new Post(1, 1, "Hello", "今日は涼しそう");
        Post p2 = new Post(2, 2, "Coffee", "そろそろコーヒー豆届くかな");
        Post p3 = new Post(3, 3, "Waffle", "今日も良い日になりそう！わーい！");

        repository.saveAndFlush(p1);
        repository.saveAndFlush(p2);
        repository.saveAndFlush(p3);
    }

    @RequestMapping("/")
    public String index(){
        return "Hello, World!";
    }

    @RequestMapping("/mono")
    public Mono<String> mono(){
        return Mono.just("Hello, World! (Mono)");
    }

    @RequestMapping("/flux")
    public Flux<String> flux(){
        return Flux.just("Hello, World! (Flux)", "-- Flux Sample");
    }

    @RequestMapping("posts")
    public Flux<Object> posts(){
        return Flux.fromArray(repository.findAll().toArray());
    }
//    @RequestMapping("/posts")
//    public Mono<List<Post>> findAll(){
//        return Mono.just((List<Post>) repository.findAll());
//    }

    @RequestMapping("/post/{id}")
    public Mono<Post> find(@PathVariable int id){
        return Mono.just(repository.findById(id));
    }

    @RequestMapping("/file")
    public Mono<String>file(){
        String result = "";
        try{
            ClassPathResource cr = new ClassPathResource("sample.txt");
            InputStream is = cr.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "utf-8");
            BufferedReader br = new BufferedReader((isr));
            String line;

            while((line = br.readLine()) != null){
                result += line;
            }
        }catch (IOException e){
            result = e.getMessage();
        }
        return Mono.just(result);


    }
}
