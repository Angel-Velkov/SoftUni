package com.example.demo.web;

import com.example.demo.dao.PostRepository;
import com.example.demo.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostRepository postRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post addPost(@RequestBody Post post) {
        return this.postRepository.save(post);
    }

    @GetMapping("/{id}")
    public Post getPost(@PathVariable long id) {
        return this.postRepository.findById(id).orElseThrow();
    }

    @GetMapping
    public Collection<Post> getAllPosts() {
        return this.postRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable long id) {
        this.postRepository.delete(this.postRepository.getById(id));
    }
}
