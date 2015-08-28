package com.artivisi.absensi.config;

import com.artivisi.absensi.security.CsrfAngularJsIntegration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class KonfigurasiSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("endy")
                .password("123")
                .roles("USER");
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin().and()
                .authorizeRequests()
                .antMatchers("/login.html").permitAll().anyRequest()
                .authenticated().and()
                .addFilterAfter(new CsrfAngularJsIntegration(), CsrfFilter.class)
                .csrf().csrfTokenRepository(csrfTokenRepository());
    }
    
    private CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-XSRF-TOKEN");
        return repository;
    }
}
