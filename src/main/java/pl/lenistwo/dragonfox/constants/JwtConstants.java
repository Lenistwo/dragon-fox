package pl.lenistwo.dragonfox.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConstants {

    @Value("${app.secret}")
    public String SECRET;

    @Value("${app.token.expire.time}")
    public Integer TOKEN_EXPIRE_TIME;
}
