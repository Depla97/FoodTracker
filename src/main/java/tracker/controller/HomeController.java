package tracker.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tracker.model.entities.Meal;
import tracker.model.entities.User;
import tracker.service.FoodService;
import tracker.service.MealService;
import tracker.service.UserService;

@Controller
public class HomeController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private FoodService foodService;
	
	@Autowired
	private MealService mealService;
	
	@RequestMapping(value = "/", method=RequestMethod.GET)
	public String home (Principal principal, Model model) {
		
		User u = this.userService.findByUsername(principal.getName());
		List<Meal> mList = this.userService.findMealByUser(u);
		List<Meal> mealsToday= new ArrayList<Meal>();
		String mDate;
		
		for(Meal m: mList) {
			mDate = m.getDate();
			if(this.mealService.compareTodayDate(mDate))
				mealsToday.add(m);
		}
		
		model.addAttribute("mealList", mealsToday);
		
		return "home";
		
	}
	
	
}
