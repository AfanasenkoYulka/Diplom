package org.diplom.visits;

import org.diplom.visits.model.Patient;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PatientsTest extends BaseTest {

    /**
     * Вывод списка всех пациентов
     */
    @Test
    @Order(1)
    public void getAllPatients() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        // путь к сущности
                        .get("/patients")
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
                .andExpect(jsonPath("$[0].fio", equalTo("Иванов Иван Иванович")))
                // проверяем дату рождения
                .andExpect(jsonPath("$[0].dateOfBirth", equalTo("1988-10-01")))
                // проверяем пол
                .andExpect(jsonPath("$[0].sex", equalTo("M")));
    }

    /**
     * Вывод пациента по ID
     */
    @Test
    @Order(2)
    public void getPatient() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        // путь к сущности и параметр
                        .get("/patients/2")
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
                .andExpect(jsonPath("$.fio", equalTo("Петров Петр Петрович")))
                // проверяем дату рождения
                .andExpect(jsonPath("$.dateOfBirth", equalTo("1968-11-21")))
                // проверяем пол
                .andExpect(jsonPath("$.sex", equalTo("M")));
    }

    /**
     * Создание нового пациента
     */
    @Test
    @Order(3)
    public void createPatient() throws Exception {
        // создаем экземпляр класса Patient
        Patient patient = new Patient();
        // заполняем данные
        //patient.setId(3L);
        patient.setFio("Тестовый Тест Тестович");
        patient.setDateOfBirth(LocalDate.of(1914, 7, 28));
        patient.setSex("M");

        mockMvc.perform(MockMvcRequestBuilders
                        // путь к сущности
                        .post("/patients")
                        // передаем в контенте созданный экземпляр
                        .content(asJsonString(patient))
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
                .andExpect(jsonPath("$.fio", equalTo(patient.getFio())))
                // проверяем дату рождения
                .andExpect(jsonPath("$.dateOfBirth", equalTo(patient.getDateOfBirth().toString())))
                // проверяем пол
                .andExpect(jsonPath("$.sex", equalTo(patient.getSex())));
    }

    /**
     * Обновление пациента по ID
     */
    @Test
    @Order(4)
    public void updatePatient() throws Exception {
        // создаем экземпляр класса Patient
        Patient patient = new Patient();
        // заполняем данные
        patient.setFio("Заменова Замена Заменовна");
        patient.setDateOfBirth(LocalDate.of(1980, 11, 24));
        patient.setSex("F");

        mockMvc.perform(MockMvcRequestBuilders
                        // путь к сущности
                        .put("/patients/2")
                        // передаем в контенте созданный экземпляр
                        .content(asJsonString(patient))
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
                .andExpect(jsonPath("$.fio", equalTo(patient.getFio())))
                // проверяем дату рождения
                .andExpect(jsonPath("$.dateOfBirth", equalTo(patient.getDateOfBirth().toString())))
                // проверяем пол
                .andExpect(jsonPath("$.sex", equalTo(patient.getSex())));
    }

    /**
     * Удаление пациента по ID
     */
    @Test
    @Order(5)
    public void deletePatient() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        // путь к сущности
                        .delete("/patients/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                // проверяем успешный ответ
                .andExpect(status().isOk());
    }
}