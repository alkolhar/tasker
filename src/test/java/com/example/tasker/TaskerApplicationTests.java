package com.example.tasker;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class TaskerApplicationTests {
    private final static String ACCOUNT_ENDPOINT = "/api/accounts";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateAccount() throws Exception {
        mockMvc.perform(post(ACCOUNT_ENDPOINT)
                        .content("{" +
                                "\"name\": \"Name\"," +
                                "\"firstName\": \"FirstName\"," +
                                "\"login\": \"Login2\"," +
                                "\"email\": \"email@mail.ch\"" +
                                "}")
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void shouldFindAccountWithValidId() throws Exception {
        mockMvc.perform(get(ACCOUNT_ENDPOINT + "/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Name"))
                .andExpect(jsonPath("$.firstName").value("FirstName"))
                .andExpect(jsonPath("$.login").value("Login2"))
                .andExpect(jsonPath("$.email").value("email@mail.ch"))
                .andReturn();
    }

    @Test
    void shouldFindAccountWithValidSearchText() throws Exception {
        mockMvc.perform(get(ACCOUNT_ENDPOINT + "/search")
                        .param("searchText", "ogin"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
}
