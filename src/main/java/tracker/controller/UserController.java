package tracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import tracker.model.entities.Food;
import tracker.model.entities.Meal;
import tracker.model.entities.User;
import tracker.service.FoodService;
import tracker.service.MealService;
import tracker.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private FoodService foodService;
	@Autowired
	private MealService mealService;
	
	
	
	
}
