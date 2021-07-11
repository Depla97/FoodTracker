package tracker.model.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import tracker.model.entities.Food;
import tracker.model.entities.Meal;

@Repository("foodDao")
public class FoodDao extends CommonDao{

//	private SessionFactory sessionFactory;
//	private Session session;
//
//	public SessionFactory getSessionFactory() {
//		return sessionFactory;
//	}
//
//	@Resource(name = "sessionFactory")
//	public void setSessionFactory(SessionFactory sessionFactory) {
//		this.sessionFactory = sessionFactory;
//	}
//
//	public void setSession(Session session) {
//		// added to allow a single thread session sharing scheme
//		this.session = session;
//	}
//
//	public Session getSession() {
//		// 1. in case a shared session exists, return it (e.g. data generation script)
//		Session session = this.session;
//		if (session == null) {
//			// 2. otherwise generate a new session using the factory (e.g. Spring MVC)
//			try {
//				session = this.sessionFactory.getCurrentSession();
//			} catch (HibernateException ex) {
//				// error getting current session, try to open a new one
//				session = this.sessionFactory.openSession();
//			}
//		}
//		return session;
//
////		Session curr = sessionFactory.getCurrentSession();
////		return curr;
//	}

/////////////////////////////METODI SPECIFICI PER QUESTO DAO

	public List<Food> findAll() {

		return getSession().
				createQuery("from Food f", Food.class).
				getResultList();

	}

	public Food findById(Long id) {

		return getSession().find(Food.class, id);
	}

	public Food update(Food food) {

		Food updated = (Food)this.getSession().merge(food);
		return updated;
	}

	public void delete(Food food) {
		this.getSession().delete(food);
	}

	public Food create(String nome, String descrizione, int calorie) {
		
		if ((nome == null || nome.length() == 0) && 
				(calorie == 0)) {
			throw new RuntimeException("A food must have a name and calories");
		}
		
		Food f = new Food();
		f.setNome(nome);
		f.setDescrizione(descrizione);
		f.setCalorie(calorie);
		
		//this.getSession().save(f);
		this.getSession().persist(f);
		return f;
	}
	
	public Food createWMacros(String nome, String descrizione, int calories, int carb, int protein, int fats) {	
			return null;
		
	}
}
