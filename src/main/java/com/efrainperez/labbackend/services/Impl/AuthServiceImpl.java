package com.efrainperez.labbackend.services.Impl;

import com.efrainperez.labbackend.dto.JwtResponseDTO;
import com.efrainperez.labbackend.dto.LoginRequestDTO;
import com.efrainperez.labbackend.models.Rol;
import com.efrainperez.labbackend.models.Usuario;
import com.efrainperez.labbackend.security.JwtUtils;
import com.efrainperez.labbackend.services.AuthService;
import lombok.RequiredArgsConstructor;
import com.efrainperez.labbackend.repository.RolRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.efrainperez.labbackend.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;
    private  final  RolRepository rolRepository;


    @Override
    public JwtResponseDTO login(LoginRequestDTO loginRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(),
                        loginRequestDTO.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtUtils.generateToken(userDetails);

        String role = userDetails.getAuthorities().stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElse("USER");

        return new JwtResponseDTO(token, role);
    }

    @Override
    public void registrar(Usuario usuario){
        String passwordEncriptada = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(passwordEncriptada);
        Rol rolUser = rolRepository.findByRol("USER").orElseThrow();
        usuario.setRoles(Collections.singleton(rolUser));
        usuarioRepository.save(usuario);
    }

}
