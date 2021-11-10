package com.t_systems.t_medical_center_system.service.impl;

import com.t_systems.t_medical_center_system.entity.EventTime;
import com.t_systems.t_medical_center_system.repository.EventTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventTimeServiceImp {

    private final EventTimeRepository eventTimeRepository;

    @Autowired
    public EventTimeServiceImp(EventTimeRepository eventTimeRepository) {
        this.eventTimeRepository = eventTimeRepository;
    }

    @Transactional
    public void delete(List<EventTime> eventTimes){
        eventTimeRepository.deleteAll(eventTimes);
    }

}
