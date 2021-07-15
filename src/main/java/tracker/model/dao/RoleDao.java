package tracker.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import tracker.model.entities.Role;

@Repository("roleDao")
public class RoleDao extends CommonDao{

	public Role findByName(String name) {
		List<Role> allRoles=this.findAll();
		for(Role r : allRoles)
		{
			if(r.getName().equals(name.toUpperCase()))//nomi dei ruoli possono essere solo maiuscoli
				return r;
		}
			return null;
		
	}
	
	public List<Role> findAll() {
		return getSession().
				createQuery("from Role r", Role.class).
				getResultList();

	}

	public Role findById(Long id) {

		return getSession().find(Role.class, id);
	}
	
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
