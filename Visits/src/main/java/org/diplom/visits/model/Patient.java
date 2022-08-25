package org.diplom.visits.model;

import java.time.LocalDate;
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
@Table(name = "patients")
/**
 * Модель пациента
 */
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    // поле id пациента
    private Long id;
    // поле ФИО пациента
    private String fio;
    // поле дата рождения пациента
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    // поле пол пациента
    private String sex;
}
