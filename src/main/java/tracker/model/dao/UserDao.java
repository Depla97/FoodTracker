package tracker.model.dao;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;

import tracker.model.entities.Food;
import tracker.model.entities.Meal;
import tracker.model.entities.User;

public interface UserDao {
	
	User findUserByUsername(String username);
	
	User create(String username, String password, boolean isEnabled);

	User update(User user);

	void delete(User user);

	public String encryptPassword(String password);

	void setPasswordEncoder(PasswordEncoder passwordEncoder);

	PasswordEncoder getpasswordEncoder();

	List<Food> getFoods(User user);
	
	List<Meal> getMeals(User user);
	
}
