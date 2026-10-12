package com.efrainperez.labbackend.controllers;

import com.efrainperez.labbackend.dto.JwtResponseDTO;
import com.efrainperez.labbackend.dto.LoginRequestDTO;
import com.efrainperez.labbackend.models.Usuario;
import com.efrainperez.labbackend.services.AuthService;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    //@GetMapping("/X")
     private final AuthService authService;


     @PostMapping("/login")
     public ResponseEntity<JwtResponseDTO> login(@Valid @RequestBody LoginRequestDTO request){
         JwtResponseDTO jwtResponseDTO = authService.login(request);
         return ResponseEntity.ok(jwtResponseDTO);
     }

}
