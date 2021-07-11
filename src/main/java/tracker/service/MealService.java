package tracker.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tracker.model.dao.MealDao;
import tracker.model.entities.Food;
import tracker.model.entities.Meal;

@Transactional
@Service("mealService")
public class MealService {
	
	private MealDao mealRepository;
	
	@Autowired
	public void setMealDao (MealDao mealRepository) {
		this.mealRepository = mealRepository;
	}
	
	@Transactional
	public List<Meal> findAll() {
		return this.mealRepository.findAll();
	}
	
	@Transactional
	public Meal findById(Long id) {
		return this.mealRepository.findById(id);
	}
	
	@Transactional
	public Meal create(Date date, int mealType, Set<Food> foods) {
		return this.mealRepository.create(date,mealType,foods);
	}
	
	@Transactional
	public Meal update (Meal meal) {
		return this.mealRepository.update(meal);
		
	}
	
	@Transactional
	public void delete (Long mId) {
		Meal m = this.mealRepository.findById(mId);
		this.mealRepository.delete(m);
	}

}
