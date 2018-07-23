package br.com.rogrs.services;

import static org.junit.Assert.assertEquals;
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

	private String APPLICATION = "application";

	private Application createApplication(String name) {

		Application app = new Application();
		app.setName(name);

		return applicationRepository.save(app);
	}

	@Test
	public void testeReportsEngineService() {

		Application app1 = createApplication(APPLICATION);

		Application comp1 = reportsEngineService.execute(APPLICATION);

		System.out.println(comp1.toString());

		assertNotNull(comp1);
	}

}
