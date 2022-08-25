package org.diplom.visits.service;

import lombok.AllArgsConstructor;
import org.diplom.visits.model.Visit;
import org.diplom.visits.repository.VisitRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VisitService {

    private VisitRepository repository;

    /**
     * Возвращает список всех элементов сущности
     */
    public List<Visit> findAll() {
        return repository.findAll();
    }

    /**
     * Возвращает сущность по ID
     */
    public Optional<Visit> findById(Long id) {
        return repository.findById(id);
    }

    /**
     * Создает сущность
     */
    public Visit save(Visit entity) {
        return repository.save(entity);
    }

    /**
     * Удаляет сущность по ID
     */
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
