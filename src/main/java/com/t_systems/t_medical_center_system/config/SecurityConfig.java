package com.t_systems.t_medical_center_system.config;

import com.t_systems.t_medical_center_system.entity.Role;
import com.t_systems.t_medical_center_system.service.impl.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserServiceImp userDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public SecurityConfig(UserServiceImp userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }





    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//                .csrf()
//                .disable()
//                .authorizeRequests()
//                .antMatchers("/").permitAll()
//                .anyRequest()
//                .authenticated()
////                .antMatchers("/menu").permitAll()
////                .antMatchers("/add").permitAll()
//                //Доступ только для не зарегистрированных пользователей
////                .antMatchers("/**").not().fullyAuthenticated()
//                //Доступ только для пользователей с ролью Администратор
////                .antMatchers("/admin/**").hasRole("ADMIN")
////                .antMatchers("/news").hasRole("USER")
//                //Доступ разрешен всем пользователей
////                .antMatchers("/").permitAll()
//                //Все остальные страницы требуют аутентификации
////                .anyRequest().authenticated()
//                .and()
//                //Настройка для входа в систему
//                .formLogin()
//                .loginPage("/login")
//                .loginProcessingUrl("/authenticating")
//                .usernameParameter("name").passwordParameter("password")
//                //Перенарпавление на главную страницу после успешного входа
//                .defaultSuccessUrl("/login")
//                .permitAll()
//                .and()
//                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
//                .permitAll()
//                .logoutSuccessUrl("/login");
        httpSecurity.authorizeRequests()
                .antMatchers("/login","/sign_up").permitAll()
                .antMatchers("/allPatients").hasRole("PATIENT")
                .antMatchers("/allDoctors").hasRole("DOCTOR")
                .and().csrf().disable()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login/process")
                .usernameParameter("name")
                .defaultSuccessUrl("/allDoctors")
                .failureUrl("/login?fail=true")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/")
                .and().logout();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }


    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(bCryptPasswordEncoder);
        return authProvider;
    }

}