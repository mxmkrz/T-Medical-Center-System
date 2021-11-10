package com.t_systems.t_medical_center_system.repository;

import com.t_systems.t_medical_center_system.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;


import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Repository
public interface EventRepository extends PagingAndSortingRepository<Event, Long> {
    List<Event> findAllByAppointmentId(Long id);


    List<Event> findAllByPatientId(Long id);


    @Query("SELECT DISTINCT p FROM Event AS p JOIN FETCH p.patient JOIN FETCH p.appointment WHERE p.date <:tomorrow and p.date >:yesterday")
    List<Event> findAllForDay(@Param("tomorrow") Date nextDay, @Param("yesterday") Date now);


    @Query("SELECT DISTINCT p FROM Event AS p JOIN  p.patient JOIN  p.appointment  WHERE p.time <:nextHour and p.time >:now and p.date =:today")
    Page<Event> findAllForHour(@Param("nextHour") LocalTime nextHour, @Param("now") LocalTime now, @Param("today") Date today, Pageable pageable);


    @Query("SELECT DISTINCT p FROM Event AS p JOIN  p.patient JOIN  p.appointment WHERE p.date <:tomorrow and p.date >:yesterday")
    Page<Event> findAllForDay(@Param("tomorrow") Date nextDay, @Param("yesterday") Date now, Pageable pageable);


    @Query("SELECT DISTINCT p FROM Event AS p JOIN  p.patient WHERE  p.patient.name LIKE %?1% OR p.patient.surname LIKE %?1%")
    Page<Event> findAllBy(String keyword, Pageable pageable);

}
