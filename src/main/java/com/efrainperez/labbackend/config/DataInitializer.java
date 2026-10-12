package com.efrainperez.labbackend.config;

import com.efrainperez.labbackend.models.Rol;
import com.efrainperez.labbackend.models.Usuario;
import com.efrainperez.labbackend.repository.RolRepository;
import com.efrainperez.labbackend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final RolRepository rolRepository;

    @Override
    public void run(String... args) throws Exception {

        Rol adminRole = rolRepository.findByRol("SUPER-ADMIN-ROLE")
                .orElseGet(()->{
                    Rol nuevoRol = new Rol();
                    nuevoRol.setNombreRol("SUPER-ADMIN-ROLE");
                    nuevoRol.setRol("SUPER-ADMIN-ROLE");
                    return rolRepository.save(nuevoRol);
                });
        Rol userRole = rolRepository.findByRol("USER")
                .orElseGet(()->{
                    Rol nuevoRol = new Rol();
                    nuevoRol.setNombreRol("USER");
                    nuevoRol.setRol("USER");
                    return rolRepository.save(nuevoRol);
                });

        if(usuarioRepository.findByUsername("admin").isEmpty()){
            Usuario admin  = new Usuario();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRoles(Set.of(adminRole));
            usuarioRepository.save(admin);
            System.out.println("Usuario SUPER-ADMIN-ROLE creado con éxito");
        }

        if (usuarioRepository.findByUsername("usuario").isEmpty()){
            Usuario user  = new Usuario();
            user.setUsername("usuario");
            user.setPassword(passwordEncoder.encode("user123"));
            user.setRoles(Set.of(userRole));
            usuarioRepository.save(user);
            System.out.println("Usuario USER creado con éxito");
        }

    }
}
