package br.com.rogrs.web.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthResourceIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnTokenForValidClient() throws Exception {
        mockMvc.perform(post("/api/auth/token")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"clientId\":\"report-client\",\"clientSecret\":\"report-secret\"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.accessToken").value("reports-api-token-123"))
            .andExpect(jsonPath("$.tokenType").value("Bearer"));
    }

    @Test
    public void shouldRejectInvalidCredentials() throws Exception {
        mockMvc.perform(post("/api/auth/token")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"clientId\":\"invalid\",\"clientSecret\":\"invalid\"}"))
            .andExpect(status().isBadRequest());
    }
}
