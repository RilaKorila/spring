package com.example.sample1app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SampleService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    PostRepository repository;

    private String baseurl = "https://jsonplaceholder.typicode.com/posts";
    public Post getDummyPost(){
        return new Post(0, 0, "dummy", "Serviceで作ってみたよ");
    }

    public Post[] getAllPosts(){
        return restTemplate.getForObject(baseurl, Post[].class);
    }

    public Post getPost(int id){
        return restTemplate.getForObject(baseurl + "/" + id, Post.class);
    }

    public Object[] getLocalPosts(){
        return repository.findAll().toArray();
    }

    public Post getAndSavePost(int id){
        Post post = restTemplate.getForObject(baseurl+"/"+id, Post.class);
        repository.save(post);
        return post;
    }


}
