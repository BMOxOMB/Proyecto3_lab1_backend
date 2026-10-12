package com.efrainperez.labbackend.services;

import com.efrainperez.labbackend.dto.JwtResponseDTO;
import com.efrainperez.labbackend.dto.LoginRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    JwtResponseDTO login(LoginRequestDTO loginRequestDTO);
}
