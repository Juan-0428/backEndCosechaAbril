/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Application.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class TokenJWT {

    private static final String CLAVE_SECRETA = "HOLA_JUAN_ESTE_ES_MI_SECRETO_SUPER_SEGURO_1234";
    private final Key secretKey;

    public TokenJWT() {
        this.secretKey =(Key) Keys.hmacShaKeyFor(CLAVE_SECRETA.getBytes(StandardCharsets.UTF_8));
    }

    public String GenerateToken(String username){
        try{
            String token = Jwts.builder()
                    .setSubject(username)
                    .setId(username)
                    .setIssuedAt(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()))
                    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 2))
                    .signWith(secretKey)
                    .compact();
            return token;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
   
    }
    
    public String GetUsername(String token){
        try{
           Claims claim = Jwts.parserBuilder()
                   .setSigningKey(secretKey)
                   .build()
                   .parseClaimsJws(token)
                   .getBody();
           return claim.getSubject().toString();

        }
        catch(Exception e){
            e.printStackTrace();
            return null;
    }
    
    }
    
    public Boolean VerifyToken(String token){
        try{
            Claims claim = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            Date date_expiration = claim.getExpiration();
            if(date_expiration == null || date_expiration.before(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()))){
                return false;
            }
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
    }
    }
}
