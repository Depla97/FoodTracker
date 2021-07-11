package tracker.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tracker.model.entities.Food;
import tracker.model.entities.Meal;
import tracker.service.FoodService;
import tracker.service.MealService;

@Controller
public class MealController {
	
	
	private MealService mealService;
	private FoodService foodService;
	
	@Autowired
	public void setMealService(MealService mealService) {
		this.mealService = mealService;
	}
	
	@Autowired
	public void setFoodService(FoodService foodService) {
		this.foodService = foodService;
	}
	
	@GetMapping("/addMeal")
	public String addMeal(Model model) {
		
		List<Food> foodList = foodService.findAll();
		
		
		model.addAttribute("newMeal",new Meal());
		model.addAttribute("foodList", foodList);
		
		return "/addMealForm";
	}

	@PostMapping("/saveMeal")
	public String submitMealForm(@ModelAttribute("newMeal") Meal newMeal) {
		
		this.mealService.create(newMeal.getDate(),newMeal.getMealType(),newMeal.getFoods());
		
		
		return "redirect:/";
	}
	
	@GetMapping("/addMeal/addToList")
	public String addFoodToList(@RequestParam(value="id") Long fId,  @ModelAttribute("newMeal") Meal newMeal) {
		
		System.out.println("Sto provando ad aggiungere un alimento al pasto");
		
		newMeal.addFood(this.foodService.findById(fId));
		
		return "redirect:/addMeal";
		
	}
	
}