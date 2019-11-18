package org.itstep.msk.app.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        String userQ="select username,password,enabled from users where username=?";
        String rolesQ="select u.username,r.role " +
                "from users u " +
                "inner join user_roles ur on u.id=ur.id_user " +
                "inner join roles r on ur.id_role=r.id " +
                "where u.username=?";
        auth.jdbcAuthentication().usersByUsernameQuery(userQ).authoritiesByUsernameQuery(rolesQ).dataSource(dataSource).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/","/login","/registration","/info","/demo").permitAll()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/admin/**").hasAnyAuthority("ADMIN")
                .anyRequest().authenticated();

        http.csrf().disable();//DELETE!

        http.formLogin().loginPage("/login")
                .failureUrl("/login?error=403")
                .defaultSuccessUrl("/")
                .usernameParameter("username")
                .passwordParameter("password");
        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/");
        http.exceptionHandling().accessDeniedPage("/error_403");
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/CSS/**")
                .antMatchers("/js/**")
                .antMatchers("/images/**")
                .antMatchers("/store/**");
    }

}
