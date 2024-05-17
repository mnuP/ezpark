package com.dailycodework.ezpark.security.usuario;

import com.dailycodework.ezpark.model.Usuario;
import com.dailycodework.ezpark.dao.UsuarioDao;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParqueaderoUserDetailsService implements UserDetailsService {
    private final UsuarioDao usuarioDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario user = usuarioDao.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return ParqueaderoUserDetails.buildUserDetails(user);
    }
}
