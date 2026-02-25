package com.typeB.helloapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HelloController {

    @GetMapping("/hello-world")
    public ResponseEntity<?> helloWorld(@RequestParam(value = "name", required = false) String name) {
        //Basic validation - check if name exists and isn't just spaces
        if (name == null || name.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid Input"));
        }

        String input = name.trim();
        char firstChar = Character.toLowerCase(input.charAt(0));

        //Range A-M: return the greeting
        if (firstChar >= 'a' && firstChar <= 'm') {
            //Capitalize the first letter since it is greeting + name
            String greetingName = input.substring(0, 1).toUpperCase() + input.substring(1);
            return ResponseEntity.ok(Map.of("message", "Hello " + greetingName));
        }

        //Everything else (N-Z, numbers, symbols) is invalid
        return ResponseEntity.badRequest().body(Map.of("error", "Invalid Input"));
    }
}
