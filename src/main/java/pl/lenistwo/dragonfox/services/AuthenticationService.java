package pl.lenistwo.dragonfox.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import pl.lenistwo.dragonfox.constants.JwtConstants;
import pl.lenistwo.dragonfox.security.jwt.models.AuthenticationResponse;
import pl.lenistwo.dragonfox.security.jwt.models.AuthenticationRequest;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import static pl.lenistwo.dragonfox.constants.DragonFoxConstants.*;

@Service
public class AuthenticationService {

    private final AuthenticationManager authManager;
    private final JwtConstants constants;

    public AuthenticationService(AuthenticationManager authManager, JwtConstants constants) {
        this.authManager = authManager;
        this.constants = constants;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        Authentication authenticate = new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        Authentication authResult = authManager.authenticate(authenticate);
        String token = AUTHORIZATION_HEADER_VALUE + Jwts.builder()
                .setSubject(authResult.getName()).claim(AUTHORITIES, authResult.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(constants.TOKEN_EXPIRE_TIME)))
                .signWith(Keys.hmacShaKeyFor(constants.SECRET.getBytes()))
                .compact();
        return new AuthenticationResponse(authResult.getName(), authResult.getAuthorities(), token);
    }
}
