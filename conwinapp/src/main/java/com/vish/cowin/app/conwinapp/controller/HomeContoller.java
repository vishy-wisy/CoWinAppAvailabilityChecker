package com.vish.cowin.app.conwinapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeContoller
{
    @GetMapping("/index")
    public String showLoginPage(Model model)
    {
        return "search";
    }
}
