package pl.lenistwo.dragonfox.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.SessionManagementFilter;
import pl.lenistwo.dragonfox.constants.JwtConstants;
import pl.lenistwo.dragonfox.cors.CorsFilter;
import pl.lenistwo.dragonfox.document.Role;
import pl.lenistwo.dragonfox.security.jwt.JwtBaseAuthenticationFilter;
import pl.lenistwo.dragonfox.security.jwt.JwtTokenVerifier;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final MongoUserDetailsService mongoUserDetailsService;
    private final JwtConstants constants;

    public SecurityConfig(MongoUserDetailsService mongoUserDetailsService, JwtConstants constants) {
        this.mongoUserDetailsService = mongoUserDetailsService;
        this.constants = constants;
    }

    @Bean
    public CorsFilter filter() {
        return new CorsFilter();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(mongoUserDetailsService).passwordEncoder(encoder());

    }

    @Override
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(filter(), SessionManagementFilter.class)
                .addFilter(new JwtBaseAuthenticationFilter(authenticationManager(), constants))
                .addFilterAfter(new JwtTokenVerifier(constants), JwtBaseAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/", "/api/posts/**", "/api/login", "/api/encryption")
                .permitAll()
                .antMatchers("/api/user/**").authenticated();
    }

}
