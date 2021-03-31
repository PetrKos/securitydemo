package com.example.securitydemo.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/")
class TemplateController {

    @GetMapping("login")
    fun getLoginView(): String {
        return "login" //must be the same as in ?.loginPage("/login")
    }
}