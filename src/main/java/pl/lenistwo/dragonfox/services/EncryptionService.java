package pl.lenistwo.dragonfox.services;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import pl.lenistwo.dragonfox.commons.PublicKeyResponse;

import java.io.File;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

@Service
public class EncryptionService {

    @SneakyThrows
    public PrivateKey getPrivateKey() {
        byte[] privateKey = Files.readAllBytes(new File("privateKey").toPath());
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKey);
        return KeyFactory.getInstance("RSA").generatePrivate(pkcs8EncodedKeySpec);
    }

    @SneakyThrows
    public PublicKeyResponse getPublicKey() {
        byte[] publicKey = Files.readAllBytes(new File("src\\main\\resources\\publicKey").toPath());
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKey);
        PublicKey publicKeyObject = KeyFactory.getInstance("RSA").generatePublic(x509EncodedKeySpec);
        return new PublicKeyResponse(publicKeyObject.getAlgorithm(), publicKeyObject.getEncoded());
    }
}
