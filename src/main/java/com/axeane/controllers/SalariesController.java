package com.axeane.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SalariesController {

    @RequestMapping("/")
    public String index() {
        return "votre controller SalariesController.java a été bien créé";
    }
}
