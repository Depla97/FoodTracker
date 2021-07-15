package tracker.test.dao;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import tracker.model.dao.MyUserDetailsDao;
import tracker.model.entities.User;

public class TestUserDao {

	private AnnotationConfigApplicationContext ctx;
	private MyUserDetailsDao userDao;
	private SessionFactory sessionFactory;

	@BeforeEach
	void openContext() {
		/**
		 * Ciascun test ha bisogno di un contesto applicativo e un singerDao
		 */
		System.out.println("Apro lo Spring Context per questo test");

		ctx = new AnnotationConfigApplicationContext(PersistenceTestConfiguration.class);

		userDao = ctx.getBean("userDao", MyUserDetailsDao.class);

		sessionFactory = ctx.getBean("sessionFactory", SessionFactory.class);
	}

	@AfterEach
	void closeContext() {
		System.out.println("Chiudo il contesto");

		ctx.close();
	}

	@Test
	void testCreateUserNoUsername() {
		Session s = sessionFactory.openSession();

		userDao.setSession(s);

		try {
			userDao.create("", "", false);// Provo a creare un alimento senza utente proprietario
			fail("Non dovrebbe essere possibile creare alimenti senza utente");

		} catch (RuntimeException rte) {
			assertTrue(true);// se lancia l'eccezione il test passa
		}
	}

	@Test
	void testCreateUserNoPassword() {
		Session s = sessionFactory.openSession();

		userDao.setSession(s);

		try {
			userDao.create("mario", "", false);// Provo a creare un alimento senza utente proprietario
			fail("Non dovrebbe essere possibile creare alimenti senza utente");

		} catch (RuntimeException rte) {
			assertTrue(true);// se lancia l'eccezione il test passa
		}
	}

	@Test
	void testCreateUserSameUsername() {
		// Username è chiave primaria, non posso creare due utenti con lo stesso nome
		Session s = sessionFactory.openSession();

		userDao.setSession(s);

		userDao.create("mario", "mario", true);

		try {
			userDao.create("mario", "luigi", true);
			fail("non si dovrebbe poter creare un secondo utente con lo stesso nome");
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	void testEncryptedPassword() {
		Session s = sessionFactory.openSession();
		userDao.setSession(s);
		
		User u = userDao.create("mario",userDao.encryptPassword("luigi"),true);
		
		assert(u.getPassword().equals("luigi")==false);
		
	}

}
