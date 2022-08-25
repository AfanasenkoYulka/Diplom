package org.diplom.visits.controller;

import lombok.AllArgsConstructor;
import org.diplom.visits.model.Patient;
import org.diplom.visits.service.PatientService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/patients", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class PatientController {

    /**
     * Сервис
     */
    private PatientService service;

    /**
     * Возвращает список всех пациентов
     */
    @GetMapping(value = "")
    public List<Patient> findAll() {
        // вывод всех пациентов
        return service.findAll();
    }

    /**
     * Возвращает пациента по ID
     */
    @GetMapping(value = "/{id}")
    public Optional<Patient> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    /**
     * Сохраняет пациента
     */
    @PostMapping(value = "")
    public Patient save(@RequestBody Patient patient) {
        return service.save(patient);
    }

    /**
     * Обновляет пациента по ID или создает нового
     */
    @PutMapping(value = "/{id}")
    public Patient update(@RequestBody Patient patient,
                          @PathVariable Long id) {
        return service.findById(id).map(item -> {
            // обновляем данные
            item.setFio(patient.getFio());
            item.setSex(patient.getSex());
            item.setDateOfBirth(patient.getDateOfBirth());
            // сохраняем изменения
            return service.save(item);
            // если не нашли, сохраняем как новую
        }).orElseGet(() -> service.save(patient));
    }

    /**
     * Удаляет пациента по ID
     */
    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}
