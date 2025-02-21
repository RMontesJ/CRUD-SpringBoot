package com.example.crudusuario.controller;
import javax.naming.AuthenticationException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import service.AuthService;

@Controller
public class LoginController {
	
	AuthService authService;
	
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    @PostMapping("/login")
    public String loginAcces(@RequestParam String username, @RequestParam String password) throws AuthenticationException {
    	if(authService.authenticate(username, password)) {
    		
    	}
		return "redirect:/";
    }
    
}
