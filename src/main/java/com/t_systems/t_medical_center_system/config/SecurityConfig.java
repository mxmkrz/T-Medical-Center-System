package com.t_systems.t_medical_center_system.config;

import com.t_systems.t_medical_center_system.service.impl.MedicalStaffServiceImp;
import com.t_systems.t_medical_center_system.service.impl.MySimpleUrlAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final MedicalStaffServiceImp medicalStaffServiceImp;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public SecurityConfig(MedicalStaffServiceImp medicalStaffServiceImp, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.medicalStaffServiceImp = medicalStaffServiceImp;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                .antMatchers("/login","/login/reset","/login/change").anonymous()
                .antMatchers("/doctor/**").hasAnyRole("DOCTOR","ADMIN")
                .antMatchers("/nurse/**").hasAnyRole("NURSE","ADMIN")
                .antMatchers("/menu/**").hasRole("ADMIN")


                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login/process")
                .usernameParameter("email").passwordParameter("password")
                .failureUrl("/login?error=true")
                .successHandler(myAuthenticationSuccessHandler())
                .and()
                .exceptionHandling()
                .accessDeniedPage("/")

                .and()
                .logout()
                .deleteCookies("JSESSIONID")

                .and()
                .csrf()
                .disable();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(medicalStaffServiceImp).passwordEncoder(bCryptPasswordEncoder);
    }


    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
        return new MySimpleUrlAuthenticationSuccessHandler();
    }


    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(medicalStaffServiceImp);
        authProvider.setPasswordEncoder(bCryptPasswordEncoder);
        return authProvider;
    }

}