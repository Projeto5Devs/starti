package br.com.starti.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import br.com.starti.security.jwt.JwtConfigurer;
import br.com.starti.security.jwt.JwtProvider;


@Configuration
public class SecurityConfig {

	@Autowired
	private JwtProvider jwtProvider;

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	
	 @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        http.cors().and()
	        .httpBasic()
	        .and()
	        .authorizeRequests()
	        .antMatchers(HttpMethod.GET, "/vaga/v1").permitAll()
	        .antMatchers("/auth/signin", "swagger-ui.html**", "/api-docs/**", "/pessoafisica/v1", "/empresas/v1","/inscricao/v1").permitAll()
	        .anyRequest().authenticated()
	        .and()
	        .csrf().disable()
	        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//	        .and().formLogin();
	        .and()
			.apply(new JwtConfigurer(jwtProvider));
	        
			return http.build();
	    }
	 
	 public static void main(String[] args) {
		System.out.println(new BCryptPasswordEncoder().encode("admin1234"));
	}
}
