package com.board.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http 
        	.csrf().disable()
            .authorizeRequests()
             
            .antMatchers("/", 
            			 "/main", 
            			 "/user/**", 
            			 "/script/**", 
            			 "/css/**").permitAll()
                
            .antMatchers("/myInfo", "/board/**").permitAll()
            .antMatchers("/ES/**", "/admin/**").permitAll()
//            .antMatchers("/admin/**").hasRole("ADMIN")
//          y.antMatchers("/admin/**").hasAuthority("ADMIN")
            .anyRequest().permitAll()
//            .anyRequest().authenticated() // 접근허용 리소스를 설정하고 그 외 나머지 리소스는 인증 후 접근 가능.
        .and()
            .formLogin()
//            .usernameParameter("loginId")
//            .passwordParameter("password")
            .loginPage("/user/login")
            .successForwardUrl("/")
            .failureForwardUrl("/user/login")
            .permitAll()
        .and()
	        .logout()
	        .logoutUrl("/user/logout")
	        .invalidateHttpSession(true).deleteCookies("JSESSIONID");
    }
	@Bean
    public BCryptPasswordEncoder  BcryptpwEncoder() {
        return new BCryptPasswordEncoder();
    }
}