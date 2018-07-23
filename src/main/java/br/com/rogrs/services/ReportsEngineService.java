package br.com.rogrs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rogrs.domain.Application;
import br.com.rogrs.repository.ApplicationRepository;
import br.com.rogrs.web.rest.errors.BadRequestAlertException;

@Service
public class ReportsEngineService {
	
	
	@Autowired
	private  ApplicationRepository applicationRepository;
	
	
	public Application execute(String application) {
		
		final Application  app = applicationRepository.findByName(application);
		
		if (app == null) {
			throw new BadRequestAlertException("Application não encontrado!", "application", "notfound");
		}
		
		return app;
	}

}
