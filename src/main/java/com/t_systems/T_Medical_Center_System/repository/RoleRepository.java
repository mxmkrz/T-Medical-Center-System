package com.t_systems.T_Medical_Center_System.repository;

import com.t_systems.T_Medical_Center_System.entity.Role;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {
}