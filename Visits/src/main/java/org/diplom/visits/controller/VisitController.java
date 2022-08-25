package org.diplom.visits.controller;

import lombok.AllArgsConstructor;
import org.diplom.visits.model.Visit;
import org.diplom.visits.service.VisitService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/visits", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class VisitController {

    /**
     * Сервис
     */
    private VisitService service;

    /**
     * Возвращает список всех визитов
     */
    @GetMapping(value = "")
    public List<Visit> findAll() {
        // вывод всех сущностей
        return service.findAll();
    }

    /**
     * Возвращает визит по ID
     */
    @GetMapping(value = "/{id}")
    public Optional<Visit> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    /**
     * Сохраняет визит
     */
    @PostMapping(value = "")
    public Visit save(@RequestBody Visit entity) {
        return service.save(entity);
    }

    /**
     * Обновляет визит по ID или создает новый
     */
    @PutMapping(value = "/{id}")
    public Visit update(@RequestBody Visit entity,
                        @PathVariable Long id) {
        return service.findById(id).map(item -> {
            // обновляем данные
            item.setDoctorId(entity.getDoctorId());
            item.setPatientId(entity.getPatientId());
            item.setDate(entity.getDate());
            item.setResult(entity.getResult());
            // сохраняем изменения
            return service.save(item);
            // если не нашли, сохраняем как новую
        }).orElseGet(() -> service.save(entity));
    }

    /**
     * Удаляет визит по ID
     */
    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}
