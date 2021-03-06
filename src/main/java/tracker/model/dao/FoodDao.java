package tracker.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import tracker.model.entities.Food;
import tracker.model.entities.User;

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
		if ((food.getNome() == null || food.getNome().length() == 0) || 
				(food.getCalorie() <=0) || (food.getPeso()<=0)) {
			throw new RuntimeException("You can not update a food with bad values");
		}
		Food updated = (Food)this.getSession().merge(food);
		return updated;
	}

	public void delete(Food food) {
		this.getSession().delete(food);
	}

	public Food create(User user, String nome, String descrizione, int calorie, int peso) {
		
		
		if ((nome == null || nome.length() == 0) || 
				(calorie <=0) || (peso<=0)) {
			throw new RuntimeException("A food must have a name,calories and weight");
		}
		if (user == null || user.getUsername()==null) {
			throw new RuntimeException("A food must have a user owner");
			
		}
		Food f = new Food();
		//f.setId(id+1);
		f.setNome(nome);
		f.setDescrizione(descrizione);
		f.setCalorie(calorie);
		f.setPeso(peso);
		f.setUser(user);
		this.getSession().save(f);
		return f;
	}
	

}
