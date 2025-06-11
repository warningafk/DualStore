package com.dualstore.tienda.service;

import com.dualstore.tienda.entity.Usuario;
import com.dualstore.tienda.entity.Rol;
import com.dualstore.tienda.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findAll().stream()
                .filter(u -> u.getCorreo().equalsIgnoreCase(username))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
        Rol rol = usuario.getRol();
        String nombreRol = (rol != null) ? rol.getNombre() : "cliente";
        GrantedAuthority authority = new SimpleGrantedAuthority(nombreRol);
        // UserDetails personalizado para exponer el correo como username
        return new org.springframework.security.core.userdetails.User(
            usuario.getCorreo(), // username será el correo
            usuario.getContrasena(),
            Collections.singletonList(authority)
        );
    }
}
