package settings;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTKey {
	// For development environment only.
	private static final String JWT_KEY = "1zBeoHCN04EWCxhS3xuDunLf2VPplkPqzM5YQGPbYrRXqU5IP2qWt1c8ZNeFE0Igz/zH0r58SFoS9UiVMqjKQQ==";
	private static final int minutes = 60 * 600000;
	
	public static Key getKey() {
		byte[] decodedKey = Base64.getDecoder().decode(JWT_KEY);
		SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
		return originalKey;
	}
	
	public static String getToken(HashMap<String, Object> claims) {
		String compactJws = Jwts.builder()
				.setClaims(claims)
				.setExpiration(new Date(System.currentTimeMillis() + minutes))
				.signWith(SignatureAlgorithm.HS512, JWT_KEY)
				.compact();
		return compactJws;
	}
		
	public static String refreshToken(String token) {
		Claims claims = Jwts.parser().setAllowedClockSkewSeconds(10).setSigningKey(JWTKey.getKey()).parseClaimsJws(token).getBody();
		String refreshToken = Jwts.builder()
				.setClaims(claims)
				.setExpiration(new Date(System.currentTimeMillis() + minutes))
				.signWith(SignatureAlgorithm.HS512, JWTKey.getKey())
				.compact();
		return refreshToken;
	}
	
}
