package com.example.jsonprocessing.web;

import com.example.jsonprocessing.service.PostService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
public class GsonPostController {

    private final PostService postService;
    private final Gson gson;

    @Autowired
    public GsonPostController(PostService postService) {
        this.postService = postService;
        this.gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
    }

    @GetMapping
    public String getPosts() {
        return this.gson.toJson(postService.getAllPosts());
    }
}
