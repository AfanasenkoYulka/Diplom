package org.diplom.visits.controller;

import lombok.AllArgsConstructor;
import org.diplom.visits.model.Doctor;
import org.diplom.visits.service.DoctorService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/doctors", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class DoctorController {

    /**
     * Сервис
     */
    private DoctorService service;

    /**
     * Возвращает список всех докторов
     */
    @GetMapping(value = "")
    public List<Doctor> findAll() {
        // вывод всех докторов
        return service.findAll();
    }

    /**
     * Возвращает доктора по ID
     */
    @GetMapping(value = "/{id}")
    public Optional<Doctor> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    /**
     * Сохраняет доктора
     */
    @PostMapping(value = "")
    public Doctor save(@RequestBody Doctor entity) {
        return service.save(entity);
    }

    /**
     * Обновляет доктора по ID или создает нового
     */
    @PutMapping(value = "/{id}")
    public Doctor update(@RequestBody Doctor entity,
                          @PathVariable Long id) {
        return service.findById(id).map(item -> {
            // обновляем данные
            item.setFio(entity.getFio());
            item.setSpeciality(entity.getSpeciality());
            // сохраняем изменения
            return service.save(item);
            // если не нашли, сохраняем как новую
        }).orElseGet(() -> service.save(entity));
    }

    /**
     * Удаляет доктора по ID
     */
    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}
