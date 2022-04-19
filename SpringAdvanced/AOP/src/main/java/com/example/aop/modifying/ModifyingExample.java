package com.example.aop.modifying;

import com.example.aop.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "examples.modifying.enabled", havingValue = "true")
public class ModifyingExample implements CommandLineRunner {

    private final Student student;

    @Autowired
    public ModifyingExample(Student student) {
        this.student = student;
    }

    @Override
    public void run(String... args) {
        String result = student.concat("LO", "VE");
        System.out.println(result);
    }
}