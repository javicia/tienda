package com.javi.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.context.annotation.Bean; 
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain; 




@Configuration
@EnableWebSecurity
public class SpringBootSecurityConfig  {
	
	@Autowired
	@Lazy
	private UserDetailsService userDetailService;
	
	
	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
	
	/**
	 * Configuración de seguridad para proteger rutas y habilitar el inicio de sesión basado en un formulario.
	 * - Deshabilita la protección CSRF.
	 * - Requiere que los usuarios con el rol "ADMIN" accedan a rutas que comienzan con "/administrador/" y "/productos/".
	 * - Redirige a la página de inicio de sesión ("/usuario/login") para usuarios no autenticados en rutas protegidas.
	 * - Tras el inicio de sesión exitoso, redirige a "/usuario/acceder".
	 */
	@SuppressWarnings("deprecation")
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeRequests(requests -> requests
                        .requestMatchers("/administrador/**").hasRole("ADMIN")
                        .requestMatchers("/productos/**").hasRole("ADMIN"))
                .formLogin(login -> login.loginPage("/usuario/login")
                .permitAll().defaultSuccessUrl("/usuario/acceder"));
        return http.build();
	}
	
	
	@Bean
	public BCryptPasswordEncoder getEncoder() {
		return new BCryptPasswordEncoder();
	}
	

}