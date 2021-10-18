package com.t_systems.t_medical_center_system.repository;

import com.t_systems.t_medical_center_system.entity.Event;
import com.t_systems.t_medical_center_system.entity.enums.EventStatus;
import org.springframework.data.jpa.repository.Modifying;
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

    @Query("SELECT DISTINCT p FROM Event AS p JOIN FETCH p.patient JOIN FETCH p.appointment  WHERE p.time <:nextHour and p.time >:now and p.date =:today")
    List<Event> findAllForHour(@Param("nextHour") LocalTime nextHour, @Param("now") LocalTime now, @Param("today") Date today);


    @Query("SELECT DISTINCT p FROM Event AS p JOIN FETCH p.patient JOIN FETCH p.appointment WHERE p.date <:tomorrow and p.date >:yesterday")
    List<Event> findAllForDay(@Param("tomorrow") Date nextDay, @Param("yesterday") Date now);


    @Query("SELECT DISTINCT p FROM Event AS p JOIN FETCH p.patient WHERE  p.patient.name LIKE %?1% OR p.patient.surname LIKE %?1%")
    List<Event> findAllBy(String keyword);

    @Modifying
    @Query("UPDATE Event AS p SET p.status =:eventStatus WHERE p.id =:id")
    void updateStatus(EventStatus eventStatus, Long id);

//    @Modifying
//    @Query("UPDATE Event AS p SET p.reasonToCancel =:reasonToCancel WHERE p.id =:id")
//    void updateReasonToCancel(String reasonToCancel, Long id);

}
