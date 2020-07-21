package pl.lenistwo.dragonfox.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.lenistwo.dragonfox.constants.JwtConstants;
import pl.lenistwo.dragonfox.security.jwt.models.AuthenticationRequest;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

import static pl.lenistwo.dragonfox.constants.DragonFoxConstants.*;

public class JwtBaseAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    private final AuthenticationManager manager;
    private final JwtConstants constants;

    public JwtBaseAuthenticationFilter(AuthenticationManager manager, JwtConstants constants) {
        this.manager = manager;
        this.constants = constants;
    }

    @Override
    @SneakyThrows
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        AuthenticationRequest authenticationRequest = new ObjectMapper().readValue(request.getInputStream(), AuthenticationRequest.class);
        Authentication authenticate = new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        return manager.authenticate(authenticate);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String token = Jwts.builder()
                .setSubject(authResult.getName()).claim(AUTHORITIES, authResult.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(constants.TOKEN_EXPIRE_TIME)))
                .signWith(Keys.hmacShaKeyFor(constants.SECRET.getBytes()))
                .compact();
        response.addHeader(AUTHORIZATION_HEADER, AUTHORIZATION_HEADER_VALUE.concat(token));
    }
}
