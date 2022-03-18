package com.perspective.restwebservices.controllers;

import com.perspective.restwebservices.model.Greet;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RequestMapping("api/v1/")
@AllArgsConstructor
@NoArgsConstructor
@RestController
public class HelloWorldController {

    private MessageSource messageSource;
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


    @GetMapping("greet-bean-internationaly")
    public String greetInternationally(){
        return messageSource.getMessage("greeting",null, LocaleContextHolder.getLocale());
    }

    @GetMapping("greet/{name}")
    public String greetStringPathVariable(@PathVariable String name){
        return "Hello World " +name;
    }
}
