package co.edu.udes.backend.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/v1/test")
public class TestAuthController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("hello-secure")
    public String helloSecure(){
        return "hello secure";
    }

}