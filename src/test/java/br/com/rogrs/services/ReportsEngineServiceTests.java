package br.com.rogrs.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.rogrs.domain.Application;
import br.com.rogrs.domain.Database;
import br.com.rogrs.repository.ApplicationRepository;
import br.com.rogrs.repository.DatasourceRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReportsEngineServiceTests {

	@Autowired
	private ReportsEngineService reportsEngineService;

	@Autowired
	private ApplicationRepository applicationRepository;
	
	@Autowired
	private DatasourceRepository datasourceRepository;

	private String APPLICATION = "application";
	
	
	private Set<Database> createDatabases(Application application){
		Set<Database> datasources = new HashSet<Database>();
		
		
		Database db = new Database();
		db.setName("MYSQL");
		db.setDriver("com.mysql.jdbc.Driver");
		db.setUrl("jdbc:mysql://localhost:3306/mysqltutorial?useSSL=false");
		db.setUsername("root");
		db.setPassword("#masterjedi1");
		db.setDialect("org.hibernate.dialect.MySQL5Dialect");
	    db.setAplication(application);
		datasources.add(datasourceRepository.saveAndFlush(db));
	

		return datasources;
	}

	
	private Application createApplication(String name) {

		Application app = new Application();
		app.setName(name);
		app.setDescription(name);
	
		return applicationRepository.saveAndFlush(app);
	}

	@Test
	public void testeReportsEngineService() {

	    Application testApp = createApplication(APPLICATION);
		//createDatabases(testApp);

		Application app = reportsEngineService.execute(APPLICATION);

		System.out.println(app.toString());

		assertNotNull(app);
		assertThat(app.getName()).isEqualTo(testApp.getName());
		//Assert.assertNotNull(app);
		//Assert.assertEquals(testApp.getName(),app.));

	}

}
