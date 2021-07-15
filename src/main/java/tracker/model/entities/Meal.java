package tracker.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "meals")
public class Meal {

	private Long id;

	private Set<Food> foods = new HashSet<Food>();

	private User user;

	private Date date;

	// private String name;
	private int mealType;// 1=colazione - 2=pranzo - 3=merenda - 4=cena - 5=spuntino
	private int calories;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", updatable = false, nullable = false)
	public Long getId() {
		return id;
	}

	@ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	@JoinTable(name = "food_meal", joinColumns = @JoinColumn(name = "MEAL_ID", nullable = false, updatable = false), inverseJoinColumns = @JoinColumn(name = "FOOD_ID", nullable = false, updatable = false))
	public Set<Food> getFoods() {
		return foods;
	}

	public void setFoods(Set<Food> foods) {
		this.foods = foods;
	}

	public void addFood(Food food) {
		this.foods.add(food);
	}

	@Column(name = "DATE")
	public Date getDate() {
		return date;
	}

	@Column(name = "TYPE")
	public int getMealType() {
		return mealType;
	}

	@Column(name = "CALORIES")
	public int getCalories() {
		return calories;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setMealType(int mealType) {
		this.mealType = mealType;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}



	@ManyToOne
	@JoinColumn(name = "USER")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
