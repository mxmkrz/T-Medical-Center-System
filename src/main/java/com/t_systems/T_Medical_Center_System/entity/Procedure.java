package com.t_systems.T_Medical_Center_System.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * This is my Procedure entity,here I will write the mapping in the database
 *
 * @author Kuryzin Maxim
 * @github mxmkrz
 */

@Entity
@ToString
@Table(name = "tb_procedures")
public class Procedure {
    @Getter
    @Setter
    @Id
    @SequenceGenerator(name = "procedures_id_generator",sequenceName = "procedures_id_generator",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "procedures_id_generator")
    private Long id;
    @Getter
    @Setter
    @Column(nullable = false)
    private String description;
    @Getter
    @Setter
    @CreationTimestamp
    private LocalDateTime createDataTime;
    @Getter
    @Setter
    @UpdateTimestamp
    private LocalDateTime updateDataTime;
}
