package com.companheirosautocenter.appautocenter.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * Classe responsavel pela configuracao de seguranca
 * 
 * @author wendel.anchieta
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userDetailsService;
		
	@Autowired
	private Environment env;
	
	private static final String[] PUBLIC_MATCHERS = {
			"/h2-console/**"
	};
	
	private static final String[] PUBLIC_MATCHERS_GETS = {
			"/produtos/**",
			"/categorias/**"
	};
	
	private static final String[] PUBLIC_MATCHERS_POST = {
			"/clientes",
			"/auth/forgot/**"
	};
	
	/**
	 * Definindo as configurações básicas das URL's que necessitam ou não de <strong>autenticação/autorização</strong>
	 * 
	 * @param httpSecurity
	 * @throws Exception
	 */
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		
		if(Arrays.asList(env.getActiveProfiles()).contains("test")) {
			httpSecurity.headers().frameOptions().disable();
		}
		
		/*httpSecurity.cors().and().csrf().disable();
		httpSecurity.authorizeRequests()
			.antMatchers(HttpMethod.POST,PUBLIC_MATCHERS_POST).permitAll()
			.antMatchers(HttpMethod.GET,PUBLIC_MATCHERS_GETS).permitAll()
			.antMatchers(PUBLIC_MATCHERS).permitAll()
			.anyRequest().authenticated();
		//Assegurando que o backend não cria sessão
		httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);*/
		
		httpSecurity.authorizeRequests()
        .anyRequest()
        .permitAll()
        .and().csrf().disable();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}
	
	/**
	 * Metodo que permite que multiplas fontes realizem Request explicitamente
	 * 
	 * @return UrlBasedCorsConfigurationSource source
	 * 
	 * @see {@link https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/cors/CorsConfiguration.html}
	 */
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;		
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
