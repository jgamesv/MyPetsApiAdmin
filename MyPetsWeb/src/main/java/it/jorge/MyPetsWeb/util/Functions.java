package it.jorge.MyPetsWeb.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import it.jorge.MyPetsWeb.model.Employee;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


public class Functions {

    public Functions(){

    }
    private final String SECRET = "mySecretKey";



    public static String getJWTToken(Employee employee) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");
        Claims claims = Jwts.claims();
        claims.put("rol",employee.getRol());

        String token = Jwts
                .builder()
                .setId("JWT")
                .setClaims(claims)
                .setSubject("USER TOKEN")
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return token;
    }
    public String encodePass (String pass){

        return ENCODER.encode(pass);
    }
    public Claims getClaims (String token){
        return Jwts.parser().setSigningKey(SECRET.getBytes()).parseClaimsJws(token).getBody();
    }

    public static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();
}
