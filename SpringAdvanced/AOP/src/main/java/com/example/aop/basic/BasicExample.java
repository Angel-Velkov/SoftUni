package com.example.aop.basic;

import com.example.aop.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name ="examples.basic.enabled", havingValue = "true")
public class BasicExample implements CommandLineRunner {

    private final Student student;

    @Autowired
    public BasicExample(Student student) {
        this.student = student;
    }

    @Override
    public void run(String... args){
        student.sayHello();
        student.echo("ANGEL");
        student.concat("LO", "VE");

        try {
            student.boom();
        } catch (IllegalStateException e) {

        }
    }
}
