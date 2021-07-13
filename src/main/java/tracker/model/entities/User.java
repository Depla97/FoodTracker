package tracker.model.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@Column(name = "USERNAME")
	private String username;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "ENABLED")
	private boolean enabled;
	
	@OneToMany(mappedBy = "user", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Set<Food> cibi = new HashSet<Food>();
	
	@OneToMany(mappedBy = "user", fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	private Set<Meal> pasti = new HashSet<Meal>();
	
	@ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	@JoinTable(name = "users_roles", 
		      joinColumns = @JoinColumn(
		    	        name = "username", referencedColumnName = "username"), 
		    	      inverseJoinColumns = @JoinColumn(
		    	        name = "role_id", referencedColumnName = "id"))
	private Set<Role> roles = new HashSet<Role>();

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String passowrd) {
		this.password = passowrd;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<Food> getCibi() {
		return cibi;
	}

	public void setCibi(Set<Food> cibi) {
		this.cibi = cibi;
	}

	public Set<Meal> getPasti() {
		return pasti;
	}

	public void setPasti(Set<Meal> pasti) {
		this.pasti = pasti;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public void addFood(Food food) {
		food.setUser(this);
		this.cibi.add(food);
	}
	public void addMeal(Meal meal) {
		meal.setUser(this);
		this.pasti.add(meal);
	}
	public void addRole(Role role) {
		role.getUsers().add(this);
		this.roles.add(role);
	}
	
	
}
