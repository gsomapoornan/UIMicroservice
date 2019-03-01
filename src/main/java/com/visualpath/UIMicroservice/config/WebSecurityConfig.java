package com.visualpath.UIMicroservice.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        
		http.authorizeRequests().anyRequest().authenticated()
                .and()
                .oauth2Login().defaultSuccessUrl("/welcome");
        
        /*http.authorizeRequests()
        .antMatchers("/login")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .oauth2Login()
        .loginPage("/login")
        .defaultSuccessUrl("/welcome");
                */
    }

	
	/*@Autowired
	private AuthUserDetailsService authUserDetailsService;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/images/**").permitAll()
            .antMatchers("/css/**").permitAll()
            .antMatchers("/h2-console/**").permitAll()
            //.antMatchers("/user-search").hasAnyRole("ADMIN")         
            //.anyRequest().permitAll();
            .anyRequest().authenticated()
            //.and().httpBasic();
             .and().formLogin()
             .loginPage("/login").permitAll().defaultSuccessUrl("/")
              .and()               
              .logout().permitAll();
        
        

        http.csrf().disable();
        http.headers().frameOptions().disable();
   }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth
          .inMemoryAuthentication()
          .withUser("user").password("{noop}password").roles("USER").and()
          .withUser("admin").password("{noop}admin").roles("ADMIN");
    	auth.
    	userDetailsService(authUserDetailsService).
        passwordEncoder(getPasswordEncoder());
       
       
    }

	private PasswordEncoder getPasswordEncoder() {
		// TODO Auto-generated method stub
		return new PasswordEncoder() {

			@Override
			public String encode(CharSequence rawPassword) {
				// TODO Auto-generated method stub
				return rawPassword.toString();
			}

			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				// TODO Auto-generated method stub
				return true;
			}
			
		};
	}*/
}