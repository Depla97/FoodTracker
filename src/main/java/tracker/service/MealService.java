package tracker.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tracker.model.dao.MealDao;
import tracker.model.entities.Food;
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
	public Meal create(User user, String date, int mealType) {
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

	@Transactional
	public Meal calculateCalories(Meal m) {
		int sum = 0;
		for (Food f : m.getFoods()) {
			sum = sum + f.getCalorie();
		}
		m.setCalories(sum);
		return this.mealRepository.update(m);
	}
	
	@Transactional
	public Meal convertDate(Meal m) {//Imposta la data a oggi, ieri o domani in base al valore passato dal controller
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
		Calendar c = Calendar.getInstance(); 
		Date date = new Date();//contiene la data di oggi
		c.setTime(date); 
		switch(m.getDate()) {
		case "1"://Oggi
			m.setDate(formatter.format(date));
			return this.mealRepository.update(m);
		case "2"://Ieri
			c.add(Calendar.DATE, -1);
			date = c.getTime();
			m.setDate(formatter.format(date));
			return this.mealRepository.update(m);
			
		case "3"://Domani
			c.add(Calendar.DATE, 1);
			date = c.getTime();
			m.setDate(formatter.format(date));
			return this.mealRepository.update(m);
		}
		return null;//In caso non sia ne uno nè due nè 3 ritorna un pasto null
	}
	
	public boolean compareTodayDate(String sdate) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
		Date date = new Date();//contiene la data di oggi
		String comparative = formatter.format(date);
		
		if(sdate.equals(comparative))
			return true;
		else
			return false;
	}
}
