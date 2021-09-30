package com.t_systems.T_Medical_Center_System.repository;


import com.t_systems.T_Medical_Center_System.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    User findByUsername(String username);

    @Query(value = "SELECT c FROM User c")
    List<User> findAllList();
}
