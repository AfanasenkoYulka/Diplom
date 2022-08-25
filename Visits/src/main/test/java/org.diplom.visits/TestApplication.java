package org.diplom.visits;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TestApplication {

    /**
     * Ссылка на объект интерфейса тестов
     */
    @Autowired
    private MockMvc mockMvc;

    /**
     * Тестирование стартовой страницы
     */
    @Test
    public void index() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/hello")
                        .accept(MediaType.APPLICATION_JSON))
                // проверяем успешный ответ
                .andExpect(status().isOk())
                // проверяем содержимое страницы
                .andExpect(content().string(equalTo("Hello World!")));
    }
}
