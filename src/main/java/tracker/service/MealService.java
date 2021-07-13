package tracker.service;

import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tracker.model.dao.MealDao;
import tracker.model.entities.Meal;
import tracker.model.entities.User;

@Transactional
@Service("mealService")
public class MealService {

	private MealDao mealRepository;

	@Autowired
	public void setMealDao(MealDao mealRepository) {
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
	public Meal create(User user, Date date, int mealType) {
		return this.mealRepository.create(user, date, mealType);
	}

	@Transactional
	public Meal update(Meal meal) {
		return this.mealRepository.update(meal);

	}

	@Transactional
	public void delete(Long mId) {
		Meal m = this.mealRepository.findById(mId);
		this.mealRepository.delete(m);
	}

	@Transactional
	public void delete(Meal meal) {
		this.mealRepository.delete(meal);
	}

}
