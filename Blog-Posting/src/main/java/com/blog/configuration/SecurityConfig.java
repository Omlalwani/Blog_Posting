package com.blog.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.blog.filter.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig {
	
	@Autowired
	JwtAuthenticationFilter jwtAuthenticationFilter;
	@Bean 
	PasswordEncoder passwordEncoder() 
	{ 
		return new BCryptPasswordEncoder(); 
	}

	
	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder)
	{
		UserDetails user = User.withUsername("user")
				.password(passwordEncoder.encode("user"))
				.build();
		
		return new InMemoryUserDetailsManager(user);
	}
	
	
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception 
	{
		http
        .csrf(csrf -> csrf.disable()) // Updated csrf disabling
        .authorizeRequests(authorizeRequests ->
            authorizeRequests
                .requestMatchers("/user/register", "/user/loginUser", "/blogs/**").permitAll()
                
                .anyRequest().authenticated()
        )
        
//        .requestMatchers("/user/blogs").authenticated()
        // Updated httpBasic() configuration
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
	}
	
	
	
	  @Bean 
	  AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception 
	  { 
		  return authenticationConfiguration.getAuthenticationManager(); 
	  }
	 

	/*
	 * @Bean BCryptPasswordEncoder userPasswordEncoder() { return new
	 * BCryptPasswordEncoder(); }
	 * 
	 * @Bean PasswordEncoder securityPasswordEncoder() { return new
	 * BCryptPasswordEncoder(); }
	 */
	
	/*
	 * @Bean AuthenticationProvider authenticationProvider() {
	 * DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	 * provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
	 * 
	 * return provider; }
	 */
}
