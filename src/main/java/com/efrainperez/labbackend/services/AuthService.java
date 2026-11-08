package com.efrainperez.labbackend.services;

import com.efrainperez.labbackend.dto.JwtResponseDTO;
import com.efrainperez.labbackend.dto.LoginRequestDTO;
import com.efrainperez.labbackend.models.Usuario;

public interface AuthService {
    JwtResponseDTO login(LoginRequestDTO loginRequestDTO);
    void registrar(Usuario usuario);
}
