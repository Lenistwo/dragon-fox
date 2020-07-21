package pl.lenistwo.dragonfox.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.lenistwo.dragonfox.document.User;
import pl.lenistwo.dragonfox.exceptions.UserNotFoundException;
import pl.lenistwo.dragonfox.repository.UserRepository;

import java.util.Collection;
import java.util.Collections;

@Service
public class MongoUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public MongoUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        Collection<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole().toString()));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
