package pl.lenistwo.dragonfox.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.lenistwo.dragonfox.commons.PublicKeyResponse;
import pl.lenistwo.dragonfox.services.EncryptionService;

@RestController
@RequestMapping("/api/encryption")
public class EncryptionController {

    private final EncryptionService encryptionService;

    public EncryptionController(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    @GetMapping
    public PublicKeyResponse getPublicKey() {
        return encryptionService.getPublicKey();
    }

}
