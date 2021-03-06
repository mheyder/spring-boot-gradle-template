package com.github.snowdream.web.template.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/helloworld/**", "/resources/**", "/", "/home").permitAll()
                .anyRequest().authenticated();
        http.formLogin().defaultSuccessUrl("/hello").loginPage("/login")
                .permitAll().and().logout().permitAll().and().rememberMe();
    }

    @Autowired
    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.inMemoryAuthentication()
            .withUser("snowdream").password("123456").roles("USER").and()
            .withUser("admin").password("123456").roles("USER", "ADMIN");
    }
}
