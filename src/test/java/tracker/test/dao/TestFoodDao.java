package tracker.test.dao;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import tracker.model.dao.FoodDao;
import tracker.model.entities.Food;
import tracker.model.entities.User;

public class TestFoodDao {

	private AnnotationConfigApplicationContext ctx;
	private FoodDao foodDao;
	private SessionFactory sessionFactory;

	@BeforeEach
	void openContext() {
		/**
		 * Ciascun test ha bisogno di un contesto applicativo e un singerDao
		 */
		System.out.println("Apro lo Spring Context per questo test");

		ctx = new AnnotationConfigApplicationContext(PersistenceTestConfiguration.class);

		foodDao = ctx.getBean("foodDao", FoodDao.class);

		sessionFactory = ctx.getBean("sessionFactory", SessionFactory.class);
	}

	@AfterEach
	void closeContext() {
		System.out.println("Chiudo il contesto");

		ctx.close();
	}

	@Test
	void testCreateFoodWithoutUser() {

		Session s = sessionFactory.openSession();

		foodDao.setSession(s);

		try {
			foodDao.create(null, "nome", "descrizione", 0, 0);// Provo a creare un alimento senza utente proprietario
			fail("Non dovrebbe essere possibile creare alimenti senza utente");

		} catch (RuntimeException rte) {
			assertTrue(true);// se lancia l'eccezione il test passa
		}
	}

	@Test
	void testCreateEmptyNameFood() {

		Session s = sessionFactory.openSession();

		foodDao.setSession(s);

		try {
			User u = new User();
			u.setUsername("mario");
			assert (u != null);
			assert (u.getUsername() != null);
			foodDao.create(u, "", "descrizione", 100, 100);// Provo a creare un alimento senza utente proprietario
			fail("Non dovrebbe essere possibile creare alimenti col campo nome e calorie vuoto");

		} catch (RuntimeException rte) {
			assertTrue(true);// se lancia l'eccezione il test passa
		}
	}

	@Test
	void testCreateNoNameFood() {

		Session s = sessionFactory.openSession();

		foodDao.setSession(s);

		try {
			User u = new User();
			u.setUsername("mario");
			assert (u != null);
			assert (u.getUsername() != null);
			foodDao.create(u, null, "descrizione", 100, 100);// Provo a creare un alimento con nome nullo
			fail("Non dovrebbe essere possibile creare alimenti col campo nome nullo");

		} catch (RuntimeException rte) {
			assertTrue(true);// se lancia l'eccezione il test passa
		}

	}

	@Test
	void testCreateZeroCaloriesFood() {

		Session s = sessionFactory.openSession();

		foodDao.setSession(s);

		try {
			User u = new User();
			u.setUsername("mario");
			assert (u != null);
			assert (u.getUsername() != null);
			foodDao.create(u, "nome", "descrizione", 0, 100);// Provo a creare un alimento con 0 calorie
			fail("Non dovrebbe essere possibile creare alimenti col campo nome nullo");

		} catch (RuntimeException rte) {
			assertTrue(true);// se lancia l'eccezione il test passa
		}

	}

	@Test
	void testCreateNoWeightFood() {

		Session s = sessionFactory.openSession();

		foodDao.setSession(s);

		try {
			User u = new User();
			u.setUsername("mario");
			assert (u != null);
			assert (u.getUsername() != null);
			foodDao.create(u, "nome", "descrizione", 100, 0);// Provo a creare un alimento con porzione 0 grammi
			fail("Non dovrebbe essere possibile creare alimenti senza porzione");

		} catch (RuntimeException rte) {
			assertTrue(true);// se lancia l'eccezione il test passa
		}

	}

	@Test
	void testCreateNegativeCaloriesFood() {

		Session s = sessionFactory.openSession();

		foodDao.setSession(s);

		try {
			User u = new User();
			u.setUsername("mario");
			assert (u != null);
			assert (u.getUsername() != null);
			foodDao.create(u, "nome", "descrizione", -1, 100);// Provo a creare un alimento con -1 calorie
			fail("Non dovrebbe essere possibile creare alimenti con calorie negative");

		} catch (RuntimeException rte) {
			assertTrue(true);// se lancia l'eccezione il test passa
		}
	}

	@Test
	void testFindFoodById() {

		Session s = sessionFactory.openSession();

		foodDao.setSession(s);

		User u = new User();
		u.setUsername("mario");
		assert (u != null);
		assert (u.getUsername() != null);

		Food f = foodDao.create(u, "nome", "descrizione", 1, 1);// Popolo un alimento normalmente
		Food ft = foodDao.findById(1l); // I database sono vuoti quindi il primo ID deve essere 1

		assert (ft.getId() == f.getId());

	}

	@Test
	void testFoodSameName() {

		Session s = sessionFactory.openSession();

		foodDao.setSession(s);

		// verifico che due cibi con informazioni uguali siano oggetti diversi (diverso
		// id)
		User u = new User();
		u.setUsername("mario");
		assert (u != null);
		assert (u.getUsername() != null);

		Food f1 = foodDao.create(u, "pizza", "descrizione", 100, 100);
		Food f2 = foodDao.create(u, "pizza", "descrizione", 100, 100);

		assert (f1 != f2);
		assert (f1.getId() != f2.getId());

	}

	@Test
	void testCreateAndUpdate() {
		// Test che verifica il lancio di un'eccezione in caso si aggiorni un oggetto
		// food
		// che non ha nessun utente persistente associato, perchè la relazione mappa
		// persistenza
		// a cascata di tipo ALL (compreso update)
		Session s = sessionFactory.openSession();

		foodDao.setSession(s);

		User u = new User();
		u.setUsername("mario");
		assert (u != null);

		Food f1 = foodDao.create(u, "pizza", "descrizione", 100, 100);

		f1.setNome("nome");

		Food f2 = foodDao.update(f1);

		assert (f2.getNome().equals("nome"));

	}

	@Test
	void testCreateAndDelete() {
		Session s = sessionFactory.openSession();

		foodDao.setSession(s);

		User u = new User();
		u.setUsername("mario");
		assert (u != null);
		
		Food f = foodDao.create(u, "pizza", "descrizione", 100, 100);
		assert(f.getId()!=null);
		
		foodDao.delete(f);
		assert(foodDao.findById(f.getId())==null);


	}

	@Test
	void testFoodFindAll() {

		Session s = sessionFactory.openSession();

		foodDao.setSession(s);

		User u = new User();
		u.setUsername("mario");
		assert (u != null);

		foodDao.create(u, "pizza1", "descrizione", 100, 100);
		foodDao.create(u, "pizza2", "descrizione", 100, 100);
		foodDao.create(u, "pizza3", "descrizione", 100, 100);

		assert (foodDao.findAll().size() == 3);
	}
}
