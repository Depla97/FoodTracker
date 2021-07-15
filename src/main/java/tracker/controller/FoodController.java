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

import tracker.model.entities.Food;
import tracker.model.entities.User;
import tracker.service.FoodService;
import tracker.service.UserService;

@Controller
@RequestMapping("/food")
public class FoodController {

	
	@Autowired
	private FoodService foodService;
	
	@Autowired
	private UserService userService;
	
	
//	public void setFoodService (FoodService foodService) {
//		this.foodService = foodService;
//	}
//	
	
	@GetMapping(value="/add")
	public String addFood (Principal principal, Model model) {
		User u = this.userService.findByUsername(principal.getName());
		
		model.addAttribute("newFood",new Food());
		model.addAttribute("currentUser", u);
		return "addFoodForm";
	}
	
	@PostMapping(value="/saveFood")
	public String submitFoodForm (@ModelAttribute("newFood") Food newFood, Principal principal) {
		User u = this.userService.findByUsername(principal.getName());
		this.foodService.create(u,newFood.getNome(),newFood.getDescrizione(),newFood.getCalorie(),newFood.getPeso());

		//return "redirect:/food/foodList/";
		return "redirect:/food/list";
	}
	
//	@GetMapping(value="/{userId}/list")
//	public String foodList(@PathVariable("userId") String userId, Model model) {
//		
//		List<Food> fList = this.foodService.findAll();//Qui andrà richiamata find by user
//		User u = this.userService.findByUsername(userId);
//		model.addAttribute("foodList", fList);
//		model.addAttribute("currentUser", u);
//		
//		return "list";
//	}
	
	@GetMapping(value="/list")
	public String foodList(Principal principal, Model model) {
		
		User u = this.userService.findByUsername(principal.getName());
		List<Food> fList = this.userService.findFoodByUser(u);//Qui andrà richiamata find by user
		model.addAttribute("foodList", fList);
		model.addAttribute("currentUser", u);
		
		return "list";
	}
	
	public String editFood() {
		return null;
		
	}
	
	public String deleteFood() {
		return null;
	}
	
	
}
