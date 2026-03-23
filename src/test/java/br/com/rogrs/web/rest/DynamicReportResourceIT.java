package br.com.rogrs.web.rest;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
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
public class DynamicReportResourceIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldBlockProtectedEndpointWithoutToken() throws Exception {
        mockMvc.perform(get("/api/reporting/templates"))
            .andExpect(status().isUnauthorized())
            .andExpect(content().string(containsString("Token inválido ou ausente")));
    }

    @Test
    public void shouldListTemplatesWithToken() throws Exception {
        mockMvc.perform(get("/api/reporting/templates")
            .header("Authorization", "Bearer reports-api-token-123"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].code").value("reports/templates/dynamic-list.jrxml"));
    }

    @Test
    public void shouldGeneratePdfFromJsonCollection() throws Exception {
        String payload = "{" +
            "\"reportName\":\"resumo-executivo\"," +
            "\"template\":{\"sourceType\":\"CLASSPATH\",\"value\":\"reports/templates/executive-summary.jrxml\"}," +
            "\"parameters\":{\"REPORT_TITLE\":\"Resumo executivo\",\"SUMMARY_TEXT\":\"Indicadores enviados\"}," +
            "\"data\":[{" +
                "\"label\":\"Pedidos\",\"value\":\"20\"},{\"label\":\"Receita\",\"value\":\"R$ 100\"}]}";

        mockMvc.perform(post("/api/reporting/render")
            .header("Authorization", "Bearer reports-api-token-123")
            .contentType(MediaType.APPLICATION_JSON)
            .content(payload))
            .andExpect(status().isOk())
            .andExpect(header().string("Content-Disposition", containsString("resumo-executivo.pdf")))
            .andExpect(content().contentType(MediaType.APPLICATION_PDF));
    }
}
