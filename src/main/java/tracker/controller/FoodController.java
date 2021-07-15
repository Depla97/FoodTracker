package tracker.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tracker.model.entities.Food;
import tracker.model.entities.Meal;
import tracker.model.entities.User;
import tracker.service.FoodService;
import tracker.service.MealService;
import tracker.service.UserService;

@Controller
@RequestMapping("/food")
public class FoodController {

	@Autowired
	private FoodService foodService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private MealService mealService;


	@GetMapping("/")
	public String foodRoot() {
		return "redirect:/food/list";
	}

	@GetMapping(value = "/add")
	public String addFood(Model model,
			@RequestParam(value = "error", required = false, defaultValue = "") String error) {

		model.addAttribute("errorCode", error);
		model.addAttribute("newFood", new Food());
		return "addFoodForm";
	}

	@PostMapping(value = "/saveFood")
	public String submitFoodForm(@ModelAttribute("newFood") Food newFood, Principal principal) {
		User u = this.userService.findByUsername(principal.getName());
		this.foodService.create(u, newFood.getNome(), newFood.getDescrizione(), newFood.getCalorie(),
				newFood.getPeso());

		return "redirect:/food/list";
	}

	@GetMapping(value = "/list")
	public String foodList(Principal principal, Model model) {

		User u = this.userService.findByUsername(principal.getName());
		List<Food> fList = this.userService.findFoodByUser(u);// Qui andrà richiamata find by user
		model.addAttribute("foodList", fList);

		return "list";
	}

	@GetMapping("/edit")
	public String editFood(Model model, Principal principal,
			@RequestParam(value = "foodId", required = true, defaultValue = "-1") Long fId) {
		User u = this.userService.findByUsername(principal.getName());
		Food f = this.userService.findSingleFoodByUser(u, fId);
		if (f != null) {
			model.addAttribute("fEdit", f);
			model.addAttribute("foodId", fId);
		}
		return "editFoodForm";

	}

	@PostMapping("/edit/saveFood")
	public String editFood(Model model, Principal principal, @ModelAttribute("fEdit") Food f,
			@RequestParam(value = "foodId", required = true, defaultValue = "-1") Long fId) {
		User u = this.userService.findByUsername(principal.getName());
		f.setId(fId);//Occorre questo passaggio altrimenti l'oggetto passato dal model ha ID null
		f.setUser(u);
		f = this.foodService.update(f);
		
		List<Meal> pastiUtente = this.userService.findMealByUser(u);
		for(Meal m: pastiUtente) {
			for(Food fo : m.getFoods() )
			{
				if(fo.getId() == fId)
					m=this.mealService.calculateCalories(m);
			}
		}
		return "redirect:/food/list";

	}

	@GetMapping("/delete")
	public String deleteFood(Principal principal, @RequestParam(value = "foodId", required = true, defaultValue = "-1") Long fId) {
	
		Food f = this.foodService.findById(fId);
		if(f.getUser().getUsername().equals(principal.getName()))//Controllo di sicurezza
			this.foodService.delete(f);
		
		return "redirect:/food/list";
	}

}
