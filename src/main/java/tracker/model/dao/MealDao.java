package tracker.model.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import tracker.model.entities.Food;
import tracker.model.entities.Meal;
import tracker.model.entities.User;

@Repository("mealDao")
public class MealDao extends CommonDao{
	
	public List<Meal> findAll() {
		return getSession().
				createQuery("from Meal m", Meal.class).
				getResultList();
	}

	public Meal findById(Long id) {

		return getSession().find(Meal.class, id);
	}

	public Meal update(Meal meal) {

		Meal updated = (Meal)this.getSession().merge(meal);
		return updated;
	}

	public void delete(Meal meal) {
		this.getSession().delete(meal);
	}

	public Meal create(User user, Date date, int mealType) {
		
		Meal m = new Meal();
		m.setUser(user);
		m.setDate(date);
		m.setMealType(mealType);
		this.getSession().save(m);
		return m;
	}
	


}
