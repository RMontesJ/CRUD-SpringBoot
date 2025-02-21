package com.example.crudusuario.controller;

import com.example.crudusuario.service.AuthService;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/controller")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/custom-login")
    public void login(@RequestParam String username, 
                      @RequestParam String password, 
                      HttpServletResponse response) throws IOException {
        if (authService.authenticate(username, password)) {
            response.sendRedirect("/user/home"); // Redirige a home si es exitoso
        } else {
            response.sendRedirect("/login?error=true"); // Si falla, redirige al login con error
        }
    }
}
