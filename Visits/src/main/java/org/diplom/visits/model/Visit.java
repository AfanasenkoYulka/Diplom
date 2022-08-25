package org.diplom.visits.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "visits")
/**
 * Модель посещений
 */
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "visit_id")
    private Long id;
    // поле пациента id
    @Column(name = "patient_id")
    private Long patientId;
    // поле доктора id
    @Column(name = "doctor_id")
    private Long doctorId;
    // поле дата
    private LocalDateTime date;
    //поле результат
    private String result;
}