package org.diplom.visits;

import org.diplom.visits.model.Visit;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class VisitsTest extends BaseTest {

    /**
     * Вывод списка всех посещений
     */
    @Test
    @Order(1)
    public void getAllVisits() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        // путь к сущности
                        .get("/visits")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                // проверяем успешный ответ
                .andExpect(status().isOk())
                // тело ответа должно быть массивом
                .andExpect(jsonPath("$").isArray())
                // массив должен состоять из двух элементов
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                // проверяем правильность ответа
                // проверяем ID
                .andExpect(jsonPath("$[0].id", equalTo(1)))
                // проверяем пациента
                .andExpect(jsonPath("$[0].patientId", equalTo(1)))
                // проверяем врача
                .andExpect(jsonPath("$[0].doctorId", equalTo(1)))
                // проверяем дату посещения
                .andExpect(jsonPath("$[0].date", equalTo(LocalDateTime
                        .of(2022, 9, 17, 15, 0, 0)
                        .format(DateTimeFormatter.ofPattern("uuuu-MM-dd'T'H:mm:ss")))))
                // проверяем результат
                .andExpect(jsonPath("$[0].result", equalTo("Кэмбербектович спас мультивселенную")));
    }

    /**
     * Вывод посещения по ID
     */
    @Test
    @Order(2)
    public void getVisit() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        // путь к сущности и параметр
                        .get("/visits/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                // проверяем успешный ответ
                .andExpect(status().isOk())
                // тело ответа должно быть пустым
                .andExpect(jsonPath("$").isNotEmpty())
                // проверяем правильность ответа
                // проверяем ID
                .andExpect(jsonPath("$.id", equalTo(2)))
                // проверяем пациента
                .andExpect(jsonPath("$.patientId", equalTo(2)))
                // проверяем врача
                .andExpect(jsonPath("$.doctorId", equalTo(2)))
                // проверяем дату посещения
                .andExpect(jsonPath("$.date", equalTo(LocalDateTime
                        .of(2022, 2, 5, 18, 30, 0)
                        .format(DateTimeFormatter.ofPattern("uuuu-MM-dd'T'H:mm:ss")))))
                // проверяем результат
                .andExpect(jsonPath("$.result", equalTo("Терапевт на теропевтил")));
    }

    /**
     * Создание нового посезения
     */
    @Test
    @Order(3)
    public void createVisit() throws Exception {
        // создаем экземпляр класса Patient
        Visit visit = new Visit();
        // заполняем данные
        visit.setPatientId(1L);
        visit.setDoctorId(2L);
        visit.setDate(LocalDateTime.of(2022, 10, 11, 20, 30, 0));
        visit.setResult("Создал посещение");

        mockMvc.perform(MockMvcRequestBuilders
                        // путь к сущности
                        .post("/visits")
                        // передаем в контенте созданный экземпляр
                        .content(asJsonString(visit))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                // проверяем успешный ответ
                .andExpect(status().isOk())
                // тело ответа должно быть пустым
                .andExpect(jsonPath("$").isNotEmpty())
                // проверяем правильность ответа
                // проверяем пациента
                .andExpect(jsonPath("$.patientId", equalTo(visit.getPatientId().intValue())))
                // проверяем врача
                .andExpect(jsonPath("$.doctorId", equalTo(visit.getDoctorId().intValue())))
                // проверяем дату посещения
                .andExpect(jsonPath("$.date", equalTo(visit.getDate()
                        .format(DateTimeFormatter.ofPattern("uuuu-MM-dd'T'H:mm:ss")))))
                // проверяем результат
                .andExpect(jsonPath("$.result", equalTo(visit.getResult())));
    }

    /**
     * Обновление посещения по ID
     */
    @Test
    @Order(4)
    public void updateVisit() throws Exception {
        // создаем экземпляр класса Patient
        Visit visit = new Visit();
        // заполняем данные
        visit.setDoctorId(1L);
        visit.setPatientId(2L);
        visit.setDate(LocalDateTime.of(2023, 10, 11, 20, 30, 0));
        visit.setResult("В будущем приняли");

        mockMvc.perform(MockMvcRequestBuilders
                        // путь к сущности
                        .put("/visits/2")
                        // передаем в контенте созданный экземпляр
                        .content(asJsonString(visit))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                // проверяем успешный ответ
                .andExpect(status().isOk())
                // тело ответа должно быть пустым
                .andExpect(jsonPath("$").isNotEmpty())
                // проверяем правильность ответа
                // проверяем ID
                .andExpect(jsonPath("$.id", equalTo(visit.getPatientId().intValue())))
                // проверяем пациента
                .andExpect(jsonPath("$.patientId", equalTo(visit.getPatientId().intValue())))
                // проверяем врача
                .andExpect(jsonPath("$.doctorId", equalTo(visit.getDoctorId().intValue())))
                // проверяем дату посещения
                .andExpect(jsonPath("$.date", equalTo(visit.getDate()
                        .format(DateTimeFormatter.ofPattern("uuuu-MM-dd'T'H:mm:ss")))))
                // проверяем результат
                .andExpect(jsonPath("$.result", equalTo(visit.getResult())));
    }

    /**
     * Удаление посещение по ID
     */
    @Test
    @Order(5)
    public void deleteVisit() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        // путь к сущности
                        .delete("/visits/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                // проверяем успешный ответ
                .andExpect(status().isOk());
    }
}