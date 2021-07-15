package tracker.test.dao;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import tracker.config.PersistenceConfiguration;

@Configuration
@ComponentScan(basePackages = { "tracker.model" })
@EnableTransactionManagement
public class PersistenceTestConfiguration extends PersistenceConfiguration{
	
	@Bean
	@Override
	public Properties hibernateProperties() {
		Properties hibernateProp = super.hibernateProperties();
		hibernateProp.put("javax.persistence.schema-generation.database.action", "drop-and-create");
		return hibernateProp;
	}
}
