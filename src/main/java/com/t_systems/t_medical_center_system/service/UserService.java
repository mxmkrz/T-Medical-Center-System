package com.t_systems.t_medical_center_system.service;

import com.t_systems.t_medical_center_system.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAll();

    User getOne(String name);

    void add(User user);
}