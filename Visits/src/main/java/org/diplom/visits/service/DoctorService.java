package org.diplom.visits.service;

import lombok.AllArgsConstructor;
import org.diplom.visits.model.Doctor;
import org.diplom.visits.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DoctorService {

    private DoctorRepository repository;

    /**
     * Возвращает список всех элементов сущности
     */
    public List<Doctor> findAll() {
        return repository.findAll();
    }

    /**
     * Возвращает сущность по ID
     */
    public Optional<Doctor> findById(Long id) {
        return repository.findById(id);
    }

    /**
     * Создает сущность
     */
    public Doctor save(Doctor entity) {
        return repository.save(entity);
    }

    /**
     * Удаляет сущность по ID
     */
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
