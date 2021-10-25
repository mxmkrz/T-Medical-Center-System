package com.t_systems.t_medical_center_system.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
@Data
@NoArgsConstructor
@Component
public class Filter {

    private String anInt;

    public Filter(String anInt) {
        this.anInt = anInt;
    }
}
