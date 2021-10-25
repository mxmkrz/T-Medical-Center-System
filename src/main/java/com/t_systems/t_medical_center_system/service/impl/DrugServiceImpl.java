package com.t_systems.t_medical_center_system.service.impl;

import com.t_systems.t_medical_center_system.entity.Drug;
import com.t_systems.t_medical_center_system.repository.DrugRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Slf4j
@Service
public class DrugServiceImpl {
    private final DrugRepository drugRepository;


    @Autowired
    public DrugServiceImpl(DrugRepository drugRepository) {
        this.drugRepository = drugRepository;
    }

    @Transactional
    public void deleteDrugs(List<Drug> drugs){
        drugRepository.deleteAll(drugs);
        log.info("The list of medicines has been deleted");
    }
}
