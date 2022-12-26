package com.ensa.client_ms.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        BCryptPasswordEncoder bcpe=getBCPE();
//
//        //{noop}:spring boot use the by default the passeword encoder, and we tell him do not use it
//        auth.inMemoryAuthentication().withUser("admin").password(bcpe.encode("1234")).roles("ADMIN","USER");
//        auth.inMemoryAuthentication().withUser("user1").password(bcpe.encode("1234")).roles("USER");
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //si on desactive cette ligne, y aura pas de system d identification par defaut
        //super.configure(http);

        //disable csrf token
        http.csrf().disable();

        //http.formLogin();

        //STATELESS: pas la peine d utiliser les sessions,utiliser token
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/client/**").permitAll();
        http.authorizeRequests().antMatchers("/client/**").hasAnyAuthority("ADMIN");
        //http.authorizeRequests().antMatchers("/client/**").hasAnyAuthority("USER");
        //ttes les requetes necessitent authentication
        http.authorizeRequests().anyRequest().authenticated();


        //1er filter au 1er plan
        //UsernamePasswordAuthenticationFilter:type de la classe
        http.addFilterBefore(new JWTAuthorizaionFilter(), UsernamePasswordAuthenticationFilter.class);


    }

}