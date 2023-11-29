package Reservista.example.Backend.Services.Login;

import Reservista.example.Backend.Config.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private DBUserDetailsService userDetailsService;

    @Autowired
    private JWTUtil jwtUtil;

    public String authenticate(String username, String password) throws BadCredentialsException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));
        return jwtUtil.generateToken(userDetailsService.loadUserByUsername(username));
    }
}
