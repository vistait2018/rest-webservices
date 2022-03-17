package com.perspective.restwebservices.controllers;

import com.perspective.restwebservices.model.Greet;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping("api/v1/")
@RestController
public class HelloWorldController {

    @GetMapping("greet")
    public String greet(){
        return "Hello World" ;
    }
    @GetMapping("greet-int/{id}")
    public String greet(@PathVariable int id){
        return "Hello World" +id;
    }

    @GetMapping("greet-bean")
    public Greet greetBean(){
        return new Greet("Hello-World");
    }

    @GetMapping("greet/{name}")
    public String greetStringPathVariable(@PathVariable String name){
        return "Hello World " +name;
    }
}
