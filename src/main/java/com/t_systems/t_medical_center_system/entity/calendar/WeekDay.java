package com.t_systems.t_medical_center_system.entity.calendar;

import com.t_systems.t_medical_center_system.entity.Appointment;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tb_week_day")
@Data
@NoArgsConstructor
public class WeekDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String day;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="appointment_id")
    private Appointment appointment;


    public WeekDay(String day) {
        this.day = day;
    }


}
