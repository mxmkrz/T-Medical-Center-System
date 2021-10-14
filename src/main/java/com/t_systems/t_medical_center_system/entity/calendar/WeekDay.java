package com.t_systems.t_medical_center_system.entity.calendar;

import com.t_systems.t_medical_center_system.entity.Appointment;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tb_week_day")
@Data
public class WeekDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String day;

    @ManyToOne
    @JoinColumn(name="appointment_id")
    private Appointment appointment;
//    Monday,
//    Tuesday,
//    Wednesday,
//    Thursday,
//    Friday,
//    Saturday,
//    Sunday;
}
