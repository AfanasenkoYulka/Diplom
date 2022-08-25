package org.diplom.visits.service;

import lombok.AllArgsConstructor;
import org.diplom.visits.model.Patient;
import org.diplom.visits.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PatientService {

    private PatientRepository patientRepository;

    /**
     * Возвращает всех список пациентов
     */
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    /**
     * Возвращает пациента по ID
     */
    public Optional<Patient> findById(Long id) {
        return patientRepository.findById(id);
    }

    /**
     * Создает пациента
     */
    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    /**
     * Удаляет пациента по ID
     */
    public void deleteById(Long id) {
        patientRepository.deleteById(id);
    }
}
