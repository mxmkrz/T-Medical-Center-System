package com.t_systems.T_Medical_Center_System.entity;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * This is my Event entity,here I will write the mapping in the database
 *
 * @author Kuryzin Maxim
 * @github mxmkrz
 */


@Entity
@Data
@Table(name = "tb_events")
public class Event {

    @Id
    @SequenceGenerator(name = "events_id_generator",sequenceName = "events_id_generator",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "events_id_generator")
    private Long id;

    @ManyToOne ///attention pliz
    @JoinColumn(name = "event_id",referencedColumnName = "id")
    private Patient patient;

    @CreationTimestamp
    private LocalDateTime eventDateTime;

    @Column(nullable = false)
    private String status;

    @CreationTimestamp
    private LocalDateTime createDataTime;

    @UpdateTimestamp
    private LocalDateTime updateDataTime;

    public Event(){}

}
