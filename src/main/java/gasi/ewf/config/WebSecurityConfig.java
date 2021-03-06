package gasi.ewf.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import gasi.ewf.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
    CustomUserDetailsService customUserDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

    /*
	@Autowired
    public void globalUserDetails(final AuthenticationManagerBuilder auth) throws Exception {
    	auth.inMemoryAuthentication()
			.withUser("john").password(passwordEncoder().encode("123")).roles("USER").and()
			.withUser("tom").password(passwordEncoder().encode("111")).roles("ADMIN").and()
			.withUser("user1").password(passwordEncoder().encode("pass")).roles("USER").and()
			.withUser("admin").password(passwordEncoder().encode("nimda")).roles("ADMIN");
    }
    */
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(customUserDetailsService)
    		.passwordEncoder(passwordEncoder());
	}

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
    	http
    		.authorizeRequests()
    			.antMatchers("/login").permitAll()
    			.antMatchers("/oauth/**").permitAll()
    			.antMatchers("/tokens/**").permitAll()
    			.antMatchers("/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/**").permitAll()
    			.anyRequest().permitAll()
    			
    		.and()
    			.formLogin().permitAll()
    		.and()
    			.csrf().disable();
    }
        
}
