package tracker.model.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import tracker.model.entities.Food;
import tracker.model.entities.Meal;
import tracker.model.entities.User;
@Repository
public class MyUserDetailsDao extends CommonDao implements UserDao{
	
		@Autowired
		private PasswordEncoder passwordEncoder;

		@Override
		public User findUserByUsername(String username) {
			return this.getSession().get(User.class, username);
		}

		@Override
		public User create(String username, String password, boolean isEnabled) {
			User u = new User();
			u.setUsername(username);
			u.setPassword(encryptPassword(password));
			u.setEnabled(isEnabled);
			this.getSession().save(u);

			return u;
		}
		

		@Override
		public User update(User user) {
			return (User) this.getSession().merge(user);
		}

		@Override
		public void delete(User user) {
			this.getSession().delete(user);
		}

		@Override
		public String encryptPassword(String password) {
			return this.passwordEncoder.encode(password);
		}
		
		@Override
		public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
			this.passwordEncoder = passwordEncoder;
		}

		@Override
		public PasswordEncoder getpasswordEncoder() {
			return this.passwordEncoder;
		}

		public List<Food> getFoods(User user) {
			Query q = this.getSession().createQuery("from Food f JOIN FETCH f.user WHERE f.user = :user", Food.class);
			
			return new ArrayList<Food>(q.setParameter("user", user).getResultList());
		}
		
		public List<Meal> getMeals(User user) {
			Query q = this.getSession().createQuery("from Meal m JOIN FETCH m.user WHERE m.user = :user", Meal.class);
			
			return new ArrayList<Meal>(q.setParameter("user", user).getResultList());
		}


		
		
}
	
