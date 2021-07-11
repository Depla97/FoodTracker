package tracker.model.dao;

import org.springframework.stereotype.Repository;

import tracker.model.entities.Role;

@Repository("roleDao")
public class RoleDao extends CommonDao{

	
	public Role create(String name) {
		Role r = new Role();
		r.setName(name.toUpperCase());//I nomi dei ruoli saranno tutti maiuscoli
		this.getSession().save(r);
		
		return r;
	}

	
	public Role update(Role role) {
		return (Role)this.getSession().merge(role);
	}

	
	public void delete(Role role) {
		this.getSession().delete(role);
	}
	
}
