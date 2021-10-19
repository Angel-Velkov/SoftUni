package com.example.demo.init;

import com.example.demo.dao.PostRepository;
import com.example.demo.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {
        if (postRepository.count() == 0) {

            this.postRepository.saveAll(List.of(
                    new Post("New in Spring", "Spring Boot is fancy ...", "Angel",
                            Set.of("spring", "java", "spring boot")),
                    new Post("Reactive Spring", "Web Flux is here ...", "Angel",
                            Set.of("spring", "java", "reactor")),
                    new Post("Easy to Test", "Web Test Client is easy ...", "Angel",
                            Set.of("spring", "java", "web test client"))
            ));
        }
    }
}
