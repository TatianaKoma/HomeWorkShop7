package com.example.homeworkshop7.config;

import com.example.homeworkshop7.exception.MissedAuthenticationPoint;
import com.example.homeworkshop7.exception.UnsuitableRoleHandler;
import com.example.homeworkshop7.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PersonService personService;

    @Autowired
    private MissedAuthenticationPoint unauthorizedHandler;

    @Autowired
    private UnsuitableRoleHandler accessDeniedHandler;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/registration").not().fullyAuthenticated()
                .antMatchers("/", "/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler).and()
                .logout()
                .permitAll()
                .logoutSuccessUrl("/");
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(personService).passwordEncoder(bCryptPasswordEncoder());
    }
}
