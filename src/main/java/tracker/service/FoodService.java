package tracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tracker.model.dao.FoodDao;
import tracker.model.entities.Food;
import tracker.model.entities.User;

@Transactional
@Service("foodService")
public class FoodService {
	
	private FoodDao foodRepository;
	
	@Autowired
	public void setFoodDao (FoodDao foodRepository) {
		this.foodRepository = foodRepository;
	}
	
	@Transactional
	public List<Food> findAll() {
		return this.foodRepository.findAll();
	}
	
	@Transactional
	public Food findById(Long id) {
		return this.foodRepository.findById(id);
	}
	
	@Transactional
	public Food create(User user, String nome, String descrizione, int calorie, int peso) {
		int cal = (int)(calorie * ((float)peso / 100));//calcola in automatico le calorie della porzione
		return this.foodRepository.create(user,nome,descrizione,cal,peso);
	}
	
	@Transactional
	public Food update (Food food) {
		int cal = (int)(food.getCalorie() * ((float)food.getPeso() / 100));
		food.setCalorie(cal);
		return this.foodRepository.update(food);
		
	}
	
	@Transactional
	public void delete (Food food) {
		this.foodRepository.delete(food);
	}
	
	@Transactional
	public void delete (Long fId) {
		Food f=this.foodRepository.findById(fId);
		this.foodRepository.delete(f);
	}
	
}
