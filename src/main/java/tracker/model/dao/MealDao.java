package tracker.model.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import tracker.model.entities.Food;
import tracker.model.entities.Meal;

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

	public Meal create(Date date, int mealType, Set<Food> foods) {
		
		Meal m = new Meal();
		m.setDate(date);
		m.setMealType(mealType);
		for(Food f : foods) {
			m.addFood(f);
		}
		
		m.calculateCalories(foods);
		//this.getSession().save(m);
		this.getSession().persist(m);
		return m;
	}
	


}
