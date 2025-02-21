package com.example.crudusuario.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class TemplateController {
    
//    @GetMapping("/registro")
//    public String registro(Model model) {
//        model.addAttribute("usuario", new Usuario());
//        return "user/registro";
//    }

//    @GetMapping("/home")
//    public String home() {
//        return "user/home";
//    }

    @GetMapping("/admin/dashboard")
    public String dashboard() {
        return "admin/dashboard";
    }
}
