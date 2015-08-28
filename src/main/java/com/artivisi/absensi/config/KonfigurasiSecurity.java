package com.artivisi.absensi.config;

import com.artivisi.absensi.security.CsrfAngularJsIntegration;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
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

    private static final String SQL_LOGIN 
            = "select username, password, enabled "
            + "from m_user where username=?";
    
    private static final String SQL_PERMISSION 
            = "select username, role_name as authority "
            + "from m_user "
            + "inner join m_role_user on m_user.id = m_role_user.id_user "
            + "inner join m_role on m_role_user.id_role = m_role.id "
            + "where m_user.username=?";
    
    @Autowired private DataSource dataSource;
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        /*
        auth
                .inMemoryAuthentication()
                .withUser("endy")
                .password("123")
                .roles("USER");
        */
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(SQL_LOGIN)
                .authoritiesByUsernameQuery(SQL_PERMISSION);
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .defaultSuccessUrl("/karyawan.html")
                .failureUrl("/error.html")
                .loginPage("/login").permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/**/*.css").permitAll()
                .antMatchers("/**/*.js").permitAll()
                .antMatchers("/halo").permitAll()
                .antMatchers("/karyawan.html").hasRole("ADMIN")
                .antMatchers("/halo").hasAnyRole("ADMIN", "OPERATOR")
                .anyRequest()
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
