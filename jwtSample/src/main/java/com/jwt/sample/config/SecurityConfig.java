package com.jwt.sample.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jwt.sample.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	  
   @Autowired
   private CustomUserDetailsService userDetailsService;

   
	/*
	 * @Autowired private AuthEntryPointJwt unauthorizedHandler;
	 * 
	 * @Bean public AuthTokenFilter authenticationJwtTokenFilter() { return new
	 * AuthTokenFilter(); }
	 */

   @Bean
   public DaoAuthenticationProvider authenticationProvider() {
       DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        
       authProvider.setUserDetailsService(userDetailsService);
       authProvider.setPasswordEncoder(passwordEncoder());
    
       return authProvider;
   }
   
   
   @Bean
   public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration) throws Exception {
     return authConfiguration.getAuthenticationManager();
   }
   @Bean
   public PasswordEncoder passwordEncoder() {
     return NoOpPasswordEncoder.getInstance();
   }
	/*
	 * @Bean public SecurityFilterChain filterChain(HttpSecurity http) throws
	 * Exception { http.cors().and().csrf().disable()
	 * .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
	 * .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
	 * and() .authorizeRequests().antMatchers("/api/auth/**").permitAll()
	 * .antMatchers("/api/test/**").permitAll() .anyRequest().authenticated();
	 * 
	 * http.authenticationProvider(authenticationProvider());
	 * http.addFilterBefore(authenticationJwtTokenFilter(),
	 * UsernamePasswordAuthenticationFilter.class);
	 * 
	 * return http.build(); }
	 */
}
