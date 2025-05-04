package co.edu.udes.backend.controllers;

import co.edu.udes.backend.dto.AuthRequestDTO;
import co.edu.udes.backend.dto.AuthResponseDTO;
import co.edu.udes.backend.services.ProfileUDetailServiceImpl;
import co.edu.udes.backend.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ProfileUDetailServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO authRequest) {
        // Auth the credentials
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUserName(),
                        authRequest.getPassword()
                )
        );

        // get the user
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUserName());

        // generate the token
        final String jwt = jwtUtil.generateToken(userDetails);

        // get the token on a DTO
        return ResponseEntity.ok(new AuthResponseDTO(jwt));
    }
}
