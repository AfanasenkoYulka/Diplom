package org.diplom.visits;

import org.diplom.visits.model.Doctor;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DoctorsTest extends BaseTest {

    /**
     * Вывод списка всех докторов
     */
    @Test
    @Order(1)
    public void getAllDoctors() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        // путь к сущности
                        .get("/doctors")
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
                // проверяем фио
                .andExpect(jsonPath("$[0].fio", equalTo("Доктор Стрэнж Кэмбербектович")))
                // проверяем специальность
                .andExpect(jsonPath("$[0].speciality", equalTo("Доктор мультивселенной")));
    }

    /**
     * Вывод доктора по ID
     */
    @Test
    @Order(2)
    public void getDoctor() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        // путь к сущности и параметр
                        .get("/doctors/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                // проверяем успешный ответ
                .andExpect(status().isOk())
                // тело ответа должно быть пустым
                .andExpect(jsonPath("$").isNotEmpty())
                // проверяем правильность ответа
                // проверяем ID
                .andExpect(jsonPath("$.id", equalTo(2)))
                // проверяем фио
                .andExpect(jsonPath("$.fio", equalTo("Терапевтов Терапевт Терапевтович")))
                // проверяем специальность
                .andExpect(jsonPath("$.speciality", equalTo("Терапевт")));
    }

    /**
     * Создание нового доктора
     */
    @Test
    @Order(3)
    public void createDoctor() throws Exception {
        // создаем экземпляр класса Patient
        Doctor doctor = new Doctor();
        // заполняем данные
        doctor.setFio("Тестовый Тест Тестович");
        doctor.setSpeciality("Тестовет");

        mockMvc.perform(MockMvcRequestBuilders
                        // путь к сущности
                        .post("/doctors")
                        // передаем в контенте созданный экземпляр
                        .content(asJsonString(doctor))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                // проверяем успешный ответ
                .andExpect(status().isOk())
                // тело ответа должно быть пустым
                .andExpect(jsonPath("$").isNotEmpty())
                // проверяем правильность ответа
                // проверяем ID
                //.andExpect(jsonPath("$.id", equalTo(3)))
                // проверяем фио
                .andExpect(jsonPath("$.fio", equalTo(doctor.getFio())))
                // проверяем специальность
                .andExpect(jsonPath("$.speciality", equalTo(doctor.getSpeciality())));
    }

    /**
     * Обновление доктора по ID
     */
    @Test
    @Order(4)
    public void updateDoctor() throws Exception {
        // создаем экземпляр класса Patient
        Doctor doctor = new Doctor();
        // заполняем данные
        doctor.setFio("Заменова Замена Заменовна");
        doctor.setSpeciality("Заменист");

        mockMvc.perform(MockMvcRequestBuilders
                        // путь к сущности
                        .put("/doctors/2")
                        // передаем в контенте созданный экземпляр
                        .content(asJsonString(doctor))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                // проверяем успешный ответ
                .andExpect(status().isOk())
                // тело ответа должно быть пустым
                .andExpect(jsonPath("$").isNotEmpty())
                // проверяем правильность ответа
                // проверяем ID
                .andExpect(jsonPath("$.id", equalTo(2)))
                // проверяем фио
                .andExpect(jsonPath("$.fio", equalTo(doctor.getFio())))
                // проверяем специальность
                .andExpect(jsonPath("$.speciality", equalTo(doctor.getSpeciality())));
    }

    /**
     * Удаление доктора по ID
     */
    @Test
    @Order(5)
    public void deleteDoctor() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        // путь к сущности
                        .delete("/doctors/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                // проверяем успешный ответ
                .andExpect(status().isOk());
    }
}