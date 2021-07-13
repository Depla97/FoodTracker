package tracker.model.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="roles")
public class Role {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
 
    @Column
    private String name;
    
    @ManyToMany(mappedBy = "roles",fetch=FetchType.EAGER)
    private Set<User> users = new HashSet<User>();
 
    public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
    public void addUser(User u) {
    	this.users.add(u);
    }

	public String getName() {
    	return this.name;
    }
    
    public void setName(String name) {
    	this.name = name;
    }
    

}
