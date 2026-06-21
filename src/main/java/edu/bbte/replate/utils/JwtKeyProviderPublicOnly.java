package edu.bbte.replate.utils;

import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Component
@Getter
public class JwtKeyProviderPublicOnly {
    // Only public key for services that validate tokens, not produce them
    private final PublicKey publicKey;

    public JwtKeyProviderPublicOnly() throws Exception {
        this.publicKey = loadPublicKey();
    }

    private PublicKey loadPublicKey() throws Exception {

        String key = Files.readString(
                ResourceUtils.getFile(
                        "classpath:keys/public.pem"
                ).toPath()
        );

        key = key
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s+", "");

        byte[] decoded =
                Base64.getDecoder().decode(key);

        X509EncodedKeySpec spec =
                new X509EncodedKeySpec(decoded);

        KeyFactory factory =
                KeyFactory.getInstance("RSA");

        return factory.generatePublic(spec);
    }
}
