package com.t_systems.T_Medical_Center_System.service;

import com.t_systems.T_Medical_Center_System.dto.PatientDto;
import com.t_systems.T_Medical_Center_System.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    boolean saveUser(User user);

    void deleteUser(Long id);

    User getUserById(Long id);

}
