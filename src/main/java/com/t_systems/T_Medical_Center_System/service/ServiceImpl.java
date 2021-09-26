package com.t_systems.T_Medical_Center_System.service;

import java.util.List;

public interface ServiceImpl<T,K> {

    List<T> findAll();

    T findById(Long id);

    K save(T obj);

    void update(T obj);

    void deleteById(Long id);

}
