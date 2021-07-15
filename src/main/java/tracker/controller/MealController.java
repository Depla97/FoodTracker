package tracker.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tracker.model.entities.Food;
import tracker.model.entities.Meal;
import tracker.model.entities.User;
import tracker.service.FoodService;
import tracker.service.MealService;
import tracker.service.UserService;

@Controller
@RequestMapping(value = "/meal")
public class MealController {

	@Autowired
	private MealService mealService;

	@Autowired
	private FoodService foodService;

	@Autowired
	private UserService userService;

	private List<Food> aggiunti = new ArrayList<Food>();

	@GetMapping("/add")
	public String addMeal(Principal principal, Model model) {

		User u = this.userService.findByUsername(principal.getName());

		model.addAttribute("newMeal", new Meal());
		model.addAttribute("currentUser", u);
		model.addAttribute("selectedFood", new HashSet<Food>());
		return "addMealForm";
	}

	@PostMapping("/createMeal")
	public String submitFirstMealForm(@ModelAttribute("newMeal") Meal newMeal, Principal principal, Model model) {
		// metodo che crea un nuovo pasto e reindirizza a un secondo form per aggiungere
		// gli alimenti
		User u = this.userService.findByUsername(principal.getName());
		Meal m = this.mealService.create(u, newMeal.getDate(), newMeal.getMealType());
		List<Food> fList = this.userService.findFoodByUser(u);
		this.aggiunti.clear();
		model.addAttribute("mealId", m.getId());
		model.addAttribute("currentUser", u);
		model.addAttribute("foodList", fList);
		model.addAttribute("aggiunti", this.aggiunti);
		return "mealAddFoodForm";
	}

	@RequestMapping(value = "/keepAdding", method = { RequestMethod.GET, RequestMethod.POST })
	public String keepAdding(@RequestParam(value = "mealId", required = true, defaultValue = "-1") Long mId,
			@RequestParam(value = "foodId", required = false, defaultValue = "-1") Long fId, Principal principal,
			Model model) {
		
		User u = this.userService.findByUsername(principal.getName());
		List<Food> fList = this.userService.findFoodByUser(u);
		Meal m = this.mealService.findById(mId);
		if (fId != -1) {
			Food f = this.foodService.findById(fId);
			m.addFood(f);
			m = this.mealService.update(m);
			this.aggiunti.add(f);

			model.addAttribute("mealId", mId);
			model.addAttribute("aggiunti", this.aggiunti);
			model.addAttribute("foodList", fList);
		}
		return "mealAddFoodForm";
	}

	@GetMapping("/completeMeal")
	public String completeMeal(@RequestParam(value = "mealId", required = true, defaultValue = "-1") Long mId) {
		Meal m = this.mealService.findById(mId);
		m = this.mealService.calculateCalories(m);//ritorna già l'update, non serve che si rifaccia
		
		return "redirect:/meal/list";
	}

	@GetMapping("/list")
	public String mealList(Principal principal, Model model) {

		User u = this.userService.findByUsername(principal.getName());
		List<Meal> mList = this.userService.findMealByUser(u);// Qui andrà richiamata find by user
		model.addAttribute("mealList", mList);
		model.addAttribute("currentUser", u);

		return "mealList";
	}

}