package com.t_systems.t_medical_center_system.repository;

import com.t_systems.t_medical_center_system.entity.Doctor;
import com.t_systems.t_medical_center_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);

    @Query("SELECT  p FROM Doctor p JOIN p.user WHERE p.name = ?1")
    User findNameDoctor(String name);


}