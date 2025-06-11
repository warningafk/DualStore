package com.dualstore.tienda.service;

import com.dualstore.tienda.entity.Usuario;
import com.dualstore.tienda.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service("adminUserDetailsService")
public class AdminUserDetailsService implements UserDetailsService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("===== ADMIN AUTH DEBUG =====");
        System.out.println("Trying to authenticate admin user: " + username);
        
        // Solo buscar en tabla usuarios (que ahora solo tiene admins)
        Usuario usuario = usuarioRepository.findAll().stream()
                .filter(u -> u.getCorreo().equalsIgnoreCase(username))
                .filter(u -> u.getEstado() == 1) // Solo usuarios activos
                .findFirst()
                .orElseThrow(() -> {
                    System.out.println("Admin user not found: " + username);
                    return new UsernameNotFoundException("Administrador no encontrado: " + username);
                });

        System.out.println("Admin user found: " + usuario.getNombreCompleto());
        System.out.println("Admin password hash: " + usuario.getContrasena());
        
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ADMIN");
        
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
            usuario.getCorreo(),
            usuario.getContrasena(),
            Collections.singletonList(authority)
        );
        
        System.out.println("Admin UserDetails created with authorities: " + userDetails.getAuthorities());
        System.out.println("===== END ADMIN AUTH DEBUG =====");
        
        return userDetails;
    }
}
