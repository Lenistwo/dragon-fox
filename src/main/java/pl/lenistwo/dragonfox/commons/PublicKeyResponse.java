package pl.lenistwo.dragonfox.commons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublicKeyResponse {
    private String algorithm;
    private byte[] encode;
}
