package tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tracker.model.entities.Food;
import tracker.service.FoodService;

@Controller
public class FoodController {

	private FoodService foodService;
	
	@Autowired
	public void setFoodService (FoodService foodService) {
		this.foodService = foodService;
	}
	
	@RequestMapping(value="/addFood", method=RequestMethod.GET)
	String addFood (Model model) {
		
		model.addAttribute("newFood",new Food());
		return "/addFoodForm";
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	String submitFoodForm (@ModelAttribute("newFood") Food newFood) {
		
		this.foodService.create(newFood);
		
		//return "redirect:/food/foodList/";
		return "redirect:/";
	}
	
	
}
