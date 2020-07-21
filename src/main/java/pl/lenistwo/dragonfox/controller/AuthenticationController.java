package pl.lenistwo.dragonfox.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.lenistwo.dragonfox.security.jwt.models.AuthenticationResponse;
import pl.lenistwo.dragonfox.security.jwt.models.AuthenticationRequest;
import pl.lenistwo.dragonfox.services.AuthenticationService;

@RestController
@RequestMapping("/api/login")
public class AuthenticationController {

    private final AuthenticationService authService;

    public AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
    }

    @PostMapping
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest request) {
        return authService.authenticate(request);
    }

}
