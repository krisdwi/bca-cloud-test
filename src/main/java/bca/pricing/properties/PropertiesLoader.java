package bca.pricing.properties;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertiesLoader {
	Logger logger = LogManager.getLogger(PropertiesLoader.class);

	@Autowired
	AiniProperties ainiProperties;
	
	@PostConstruct
	public void setReloadableAppProperties() throws Exception {
		logger.info("Start load properties . . .");
		ainiProperties.setAiniReloadableProperties();
		
		logger.info("Finish load properties . . .");
	}
}
