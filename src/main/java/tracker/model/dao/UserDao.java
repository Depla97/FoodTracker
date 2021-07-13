package tracker.model.dao;

import java.util.List;
import java.util.Set;

import tracker.model.entities.Food;
import tracker.model.entities.Meal;
import tracker.model.entities.User;

public interface UserDao {

	Set<Food> getFoods(User user);
	
	Set<Meal> getMeals(User user);
	
}
