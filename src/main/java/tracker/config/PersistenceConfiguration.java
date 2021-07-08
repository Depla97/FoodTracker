package tracker.config;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
//import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//Classe che dichiara i Bean necessari ad accedere al DB e rendere persistenti le entità tramite ORM Hibernate
//Due versioni possibili, con database embedded, quindi senza MySQL acceso, e con accesso a DBMS MySQL
//LA VERSIONE COMMENTATA E' QUELLA PER IL DBMS MySQL


@Configuration

@EnableTransactionManagement
public class PersistenceConfiguration {

	//Versione del DataSource per MySQL
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		
		ds.setDriverClassName(com.mysql.cj.jdbc.Driver.class.getName());
		ds.setUrl("jdbc:mysql://localhost:3306/singerDB?createDatabaseIfNotExist=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false");
		ds.setUsername("root");
		ds.setPassword("rootPwd");
		return ds;
		
	}
	
	
	/*@Bean
	public DataSource dataSource() {
		EmbeddedDatabaseBuilder dbBuilder =new EmbeddedDatabaseBuilder();
		return dbBuilder.setType(
				EmbeddedDatabaseType.H2).addScripts(
						"classpath:sql/schema.sql","classpath:sql/test-data.sql"
						).build();
	}
	*/
	
	@Bean //dipendenza per la sessionfactory, risolta dall'autowired di sessionfactory
	public Properties hibernateProperties() {
		Properties hibProp = new Properties();
		
		//hibProp.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");//per versione MySQL
		hibProp.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");//per versione Embedded
		hibProp.put("hibernate.format_sql", true);
		hibProp.put("hibernate.use_sql_comments", true);
		hibProp.put("hibernate.show_sql", true);
		hibProp.put("hibernate.max_fetch_depth", 3);
		hibProp.put("hibernate.jdbc.batch_size", 10);
		hibProp.put("hibernate.jdbc.fetch_size", 50);
		
		hibProp.put("javax.persistence.schema-generation.database.action", "none");
		
		return hibProp;
	}
	
	
	@Bean
	@Autowired
	public SessionFactory sessionFactory(DataSource dataSource, Properties hibernateProperties) throws IOException
	{
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		
		sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource);
		sessionFactoryBean.setPackagesToScan("tracker.model.entities");//è tipo la component scan ma sulle entity
		sessionFactoryBean.setHibernateProperties(hibernateProperties);
		sessionFactoryBean.afterPropertiesSet();//Può lanciare l'IOException quindi c'ha bisogno del throws all'inizio
		
		return sessionFactoryBean.getObject(); //La FactoryBean produce oggetti del tipo specificato dalla classe Bean a quanto pare
		
		
	}
	
	@Bean
	@Autowired
	public PlatformTransactionManager transactionManager(SessionFactory sessionFactory) throws IOException
	{
		return new HibernateTransactionManager(sessionFactory);
		
	}
	
}