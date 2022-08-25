package org.diplom.visits.repository;

import org.diplom.visits.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Декорируем интерфейс JPA для класса
 */
@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}