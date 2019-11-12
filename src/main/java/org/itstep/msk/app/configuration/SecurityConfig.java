package org.itstep.msk.app.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {
    @Autowired
    DataSource dataSource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select login,password, enabled from users where login=?")
                .authoritiesByUsernameQuery("select login, nameRole from userRoles where login=?");
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/").permitAll()
//                .antMatchers("/info").permitAll()
//                .antMatchers("/resources/**").permitAll()
//                .antMatchers("/images/**").permitAll()
//                .antMatchers("/CSS/**").permitAll()
//                .antMatchers("/admin/*").hasRole("admin")
//                .antMatchers("/demo/*").authenticated()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login").usernameParameter("user").usernameParameter("password")
//                .defaultSuccessUrl("/demo").failureUrl("/login")
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll();
        http.authorizeRequests().antMatchers("/","info","resoursec/**","/images/**","login").permitAll();
        http.authorizeRequests().antMatchers("admin").access("hasRole('admin')");
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
        http.authorizeRequests().and().formLogin()//
                // Submit URL of login page.
                .loginPage("/login")//
                .defaultSuccessUrl("/demo")//
                .failureUrl("/login?error=true")//
                .usernameParameter("username")//
                .passwordParameter("password")
                // Config for Logout Page
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful");
    }
}
