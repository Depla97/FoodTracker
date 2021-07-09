package tracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tracker.model.dao.FoodDao;

@Transactional
@Service("foodService")
public class FoodService {
	
	private FoodDao foodRepository;
	
	@Autowired
	public void setFoodDao (FoodDao foodRepository) {
		this.foodRepository = foodRepository;
	}
	
	
	
}
