package tracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tracker.model.dao.FoodDao;
import tracker.model.entities.Food;

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
	public Food create(Food food) {
		return this.foodRepository.create(food.getNome(),food.getDescrizione(), food.getCalorie());
	}
	
	@Transactional
	public Food update (Food food) {
		return this.foodRepository.update(food);
		
	}
	
	@Transactional
	public void delete (Long fId) {
		Food f = this.foodRepository.findById(fId);
		this.foodRepository.delete(f);
	}
	
}
