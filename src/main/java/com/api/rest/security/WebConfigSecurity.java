package com.api.rest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.api.rest.servico.ImplementacaoUserDetailsService;

/*Mapeia URL, endereco, autoriza ou bloqueia acesso a URL*/
@Configuration
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private ImplementacaoUserDetailsService implementacaoUserDetailsService;

	/* Configura as solicitações de acesso por http */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/* Ativando a proteção contra usuário que não estão validados por token */
		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
		
		/*Ativando a permissão para acesso a página inicial do sistema EX: sistema.com.br/index.html*/
		.disable().authorizeRequests().antMatchers("/").permitAll()
		.antMatchers("/index").permitAll()
		
		/*URL de Logout - Redireciona após o user deslogar do sistema*/
		.anyRequest().authenticated().and().logout().logoutSuccessUrl("/index")
		
		/*Mapeia URL de logout  e invalida o usuário*/
		
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
		
		/*Filtra requisições de login para autenticação*/
		
		/*Filtra demais requisições para verificar a presenção do token JWT no header HTTP*/
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/* Service que irá consultar o usuário no banco de dados */
		auth.userDetailsService(implementacaoUserDetailsService)
				/* Padrão de codigitação de senha */
				.passwordEncoder(new BCryptPasswordEncoder());

	}
}
