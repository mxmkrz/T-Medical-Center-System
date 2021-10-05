package com.t_systems.t_medical_center_system.security;

import com.t_systems.t_medical_center_system.entity.Doctor;
import com.t_systems.t_medical_center_system.entity.Patient;
import com.t_systems.t_medical_center_system.entity.User;
import com.t_systems.t_medical_center_system.repository.DoctorRepository;
import com.t_systems.t_medical_center_system.repository.PatientRepository;
import com.t_systems.t_medical_center_system.service.UserService;
import com.t_systems.t_medical_center_system.service.impl.DoctorServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthProviderImpl implements AuthenticationProvider {


    private PasswordEncoder passwordEncoder;
    private DoctorRepository doctorRepository;
    private PatientRepository patientRepository;

    @Autowired
    public AuthProviderImpl(PasswordEncoder passwordEncoder, DoctorRepository doctorRepository, PatientRepository patientRepository) {
        this.passwordEncoder = passwordEncoder;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        Doctor doctor = doctorRepository.findDoctorByName(name);
        Patient patient = patientRepository.findPatientByName(name);
        System.out.println(name);
        String password = authentication.getCredentials().toString();
        System.out.println(password);





        if (doctor != null){
            if (passwordEncoder.matches(password, doctor.getPassword()) && doctor.getPassword() != null) {
                System.out.println("password" + password + "Enter doctor");
                List<GrantedAuthority> authorities = new ArrayList<>(doctor.getAuthorities());
                return new UsernamePasswordAuthenticationToken(doctor, null, authorities);
        }} else if (passwordEncoder.matches(password, patient.getPassword()) && patient.getPassword() != null) {
            System.out.println("password" + password + " Enter patient");
            List<GrantedAuthority> authorities = new ArrayList<>(patient.getAuthorities());
//            authorities.add(new SimpleGrantedAuthority(patient.getRole().getAuthority()));
            return new UsernamePasswordAuthenticationToken(patient, null, authorities);
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}