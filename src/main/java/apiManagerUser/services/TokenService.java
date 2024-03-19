package apiManagerUser.services;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import apiManagerUser.domain.User;

@Service
public class TokenService {

	public String gerarToken(User user) {
		return JWT.create()
				.withIssuer("Usuários")
				.withSubject(user.getEmail())
				.withClaim("id", user.getId())
				.withExpiresAt(LocalDateTime.now()
						.plusMinutes(30)
						.toInstant(ZoneOffset.of("-03:00")))
				.sign(Algorithm.HMAC256("essaéapalavrasecreta"));
	}

	public String getSubject(String token) {
		return JWT.require(Algorithm.HMAC256("essaéapalavrasecreta"))
				.withIssuer("Usuários")
				.build().verify(token).getSubject();
	}

}
