package pl.lenistwo.dragonfox.security.jwt.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
    private String username;
    private Collection<? extends GrantedAuthority> authorityCollections;
    private String token;
}
