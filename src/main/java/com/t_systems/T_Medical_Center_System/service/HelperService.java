package com.t_systems.T_Medical_Center_System.service;

import java.util.List;

public interface HelperService<T> {

    List<T> findAll();

    T findById(Long id);

    void save(T obj);

    void update(T obj);

    void deleteById(Long id);

}
