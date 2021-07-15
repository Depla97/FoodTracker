package tracker.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tracker.model.dao.MyUserDetailsDao;
import tracker.model.dao.RoleDao;
import tracker.model.entities.Food;
import tracker.model.entities.Meal;
import tracker.model.entities.Role;
import tracker.model.entities.User;

@Service
public class UserService {

	@Autowired
	private MyUserDetailsDao userRepository;
	
	@Autowired
	private RoleDao roleRepository;
	
	@Transactional
	public User create(String username, String password, boolean isEnabled)
	{
		if(this.userRepository.findUserByUsername(username) == null)
			return this.userRepository.create(username,password,isEnabled);
		else
			return null;
	}
	
	@Transactional
	public User findByUsername(String username) {
		return this.userRepository.findUserByUsername(username);
	}
	
	@Transactional
	public User update (User user) {
		return this.userRepository.update(user);
	}
	
	@Transactional
	public void delete(String username) {
		User u = this.userRepository.findUserByUsername(username);
		this.userRepository.delete(u);
	}
	
	@Transactional
	public void delete(User user) {		
		this.userRepository.delete(user);
	}
	
	@Transactional
	public Role createRole(String name) {
		return this.roleRepository.create(name);
	}
	
	@Transactional
	public Role findRoleByName(String name) {
		return this.roleRepository.findByName(name);
	}
	@Transactional
	public void encryptPassword(User user) {
		user.setPassword(this.userRepository.encryptPassword(user.getPassword()));
	}
	
	@Transactional
	public List<Food> findFoodByUser(User user){
		return this.userRepository.getFoods(user);
	}
	
	@Transactional
	public Food findSingleFoodByUser(User user, Long foodId){
		List<Food> fList = this.userRepository.getFoods(user);
		for(Food f : fList)
			if(f.getId() == foodId)
				return f;
			else
				return null;
		return null;//se non trova il cibo cercato ritorna null
	}
	
	@Transactional
	public List<Meal> findMealByUser(User user){
		return this.userRepository.getMeals(user);
	}
}
