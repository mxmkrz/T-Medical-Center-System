package com.t_systems.t_medical_center_system.service.impl;

import com.t_systems.t_medical_center_system.entity.WeekDay;
import com.t_systems.t_medical_center_system.repository.WeekDayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WeekDayService {
    private WeekDayRepository weekDayRepository;

    @Autowired
    public WeekDayService(WeekDayRepository weekDayRepository) {
        this.weekDayRepository = weekDayRepository;
    }

    @Transactional
    public void delete(List<WeekDay> weekDayList) {
        weekDayRepository.deleteAll(weekDayList);
    }
}
