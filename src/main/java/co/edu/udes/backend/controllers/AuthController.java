package co.edu.udes.backend.controllers;

import co.edu.udes.backend.dto.AuthRequestDTO;
import co.edu.udes.backend.dto.AuthResponseDTO;
import co.edu.udes.backend.services.ProfileUDetailServiceImpl;
import co.edu.udes.backend.services.ProfileUService;
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

    private final ProfileUService profileUService;
    private final AuthenticationManager authenticationManager;
    private final ProfileUDetailServiceImpl userDetailsService;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthController(
            ProfileUService profileUService,
            AuthenticationManager authenticationManager,
            ProfileUDetailServiceImpl userDetailsService,
            JwtUtil jwtUtil
    ) {
        this.profileUService = profileUService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUserName(),
                        authRequest.getPassword()
                )
        );

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUserName());
        final String jwt = jwtUtil.generateToken(userDetails);
        final String fullName = profileUService.getFullname(authRequest.getUserName());
        final Long id = profileUService.getIdByUsername(authRequest.getUserName());
        final String role = String.valueOf(profileUService.getRoleName(id));

        return ResponseEntity.ok(new AuthResponseDTO(jwt, fullName, role, id));
    }
}
