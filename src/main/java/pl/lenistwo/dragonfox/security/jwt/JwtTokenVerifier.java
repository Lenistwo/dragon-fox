package pl.lenistwo.dragonfox.security.jwt;

import com.google.common.base.Strings;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import pl.lenistwo.dragonfox.constants.JwtConstants;
import pl.lenistwo.dragonfox.exceptions.BadTokenException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static pl.lenistwo.dragonfox.constants.DragonFoxConstants.*;

public class JwtTokenVerifier extends OncePerRequestFilter {

    private final JwtConstants constants;

    public JwtTokenVerifier(JwtConstants constants) {
        this.constants = constants;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = httpServletRequest.getHeader(AUTHORIZATION_HEADER);
        if (Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith(AUTHORIZATION_HEADER_VALUE)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        String token = authorizationHeader.replace(AUTHORIZATION_HEADER_VALUE, EMPTY_STRING);
        try {
            JwtParser build = Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(constants.SECRET.getBytes())).build();
            Jws<Claims> claims = build.parseClaimsJws(token);
            Claims body = claims.getBody();
            String username = body.getSubject();
            List<Map<String, String>> authorities = (List<Map<String, String>>) body.get(AUTHORITIES);
            Set<SimpleGrantedAuthority> simpleGrantedAuthorities = authorities.stream().map(authority -> new SimpleGrantedAuthority(authority.get(AUTHORITY))).collect(Collectors.toSet());
            Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, simpleGrantedAuthorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } catch (JwtException e) {
            throw new BadTokenException();
        }
    }
}
