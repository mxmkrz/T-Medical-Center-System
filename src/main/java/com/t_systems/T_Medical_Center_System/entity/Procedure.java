package com.t_systems.T_Medical_Center_System.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
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
@Data
@Table(name = "tb_procedures")
public class Procedure {

    @Id
    @SequenceGenerator(name = "procedures_id_generator", sequenceName = "procedures_id_generator", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "procedures_id_generator")
    private Long id;




    @Column(nullable = false)
    private String description;

    @CreationTimestamp
    private LocalDateTime createDataTime;

    @UpdateTimestamp
    private LocalDateTime updateDataTime;
}
