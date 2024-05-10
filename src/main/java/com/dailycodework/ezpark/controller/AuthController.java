package com.dailycodework.ezpark.controller;

import com.dailycodework.ezpark.exception.UsuarioYaExisteException;
import com.dailycodework.ezpark.model.Usuario;
import com.dailycodework.ezpark.request.LoginRequest;
import com.dailycodework.ezpark.response.JwtResponse;
import com.dailycodework.ezpark.security.jwt.JwtUtils;
import com.dailycodework.ezpark.security.usuario.*;
import com.dailycodework.ezpark.service.IUsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final IUsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @PostMapping("/register-user")
    public ResponseEntity<?> registerUser(@RequestBody Usuario usuario) {
        try {
            System.out.println("Usuario:" + usuario);
            usuarioService.registrarUsuario(usuario);
            return ResponseEntity.ok("Registration successful!");
        } catch (UsuarioYaExisteException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getContraseña()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtTokenForUser(authentication);
        ParqueaderoUserDetails userDetails = (ParqueaderoUserDetails) authentication.getPrincipal(); // Cambio aquí
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        return ResponseEntity.ok(new JwtResponse(
                userDetails.getId(),
                userDetails.getEmail(),
                jwt,
                roles));
    }
}
