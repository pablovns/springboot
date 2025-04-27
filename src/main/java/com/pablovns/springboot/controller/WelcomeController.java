package com.pablovns.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping("/gerentes")
    public String managers() {
        return "Gerente autorizado";
    }
}
