package br.com.rogrs.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.rogrs.domain.Application;
import br.com.rogrs.repository.ApplicationRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReportsEngineServiceTests {

    @Autowired
    private ReportsEngineService reportsEngineService;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Test
    public void testeReportsEngineService() {
        Application testApp = new Application();
        testApp.setName("application");
        testApp.setDescription("application");
        applicationRepository.saveAndFlush(testApp);

        Application app = reportsEngineService.execute("application");

        assertNotNull(app);
        assertThat(app.getName()).isEqualTo(testApp.getName());
    }
}
