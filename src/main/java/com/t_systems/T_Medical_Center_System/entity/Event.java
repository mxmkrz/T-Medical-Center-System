package com.t_systems.T_Medical_Center_System.entity;


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
@ToString
@Table(name = "tb_events")
public class Event {
    @Getter
    @Setter
    @Id
    @SequenceGenerator(name = "events_id_generator",sequenceName = "events_id_generator",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "events_id_generator")
    private Long id;
    @Getter
    @Setter
    @ManyToOne ///attention pliz
    @JoinColumn(name = "event_id",referencedColumnName = "id")
    private Patient patient;
    @Getter
    @Setter
    private LocalDateTime eventDateTime;
    @Getter
    @Setter
    @Column(nullable = false)
    private String status;
    @Getter
    @Setter
    @CreationTimestamp
    private LocalDateTime createDataTime;
    @Getter
    @Setter
    @UpdateTimestamp
    private LocalDateTime updateDataTime;

    public Event(){}

}
