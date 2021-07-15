package tracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import tracker.model.dao.MyUserDetailsDao;
import tracker.model.entities.Role;
import tracker.model.entities.User;


public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	private MyUserDetailsDao userDetailsDao;

	//Metodo che serve a Spring per "costruire" (build) un utente della classe userdetails
	//che contiene i dettagli di un utente mio persistente che passo utilizzando il mio DAO
	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userDetailsDao.findUserByUsername(username);
		UserBuilder builder = null;
		if (user != null) {

			// qui "mappiamo" uno User della nostra app in uno User di spring
			builder = org.springframework.security.core.userdetails.User.withUsername(username);
			builder.disabled(!user.isEnabled());
			builder.password(user.getPassword());

			String[] roles = new String[user.getRoles().size()];

			int j = 0;
			for (Role r : user.getRoles()) {
				roles[j++] = r.getName();
			}

			builder.roles(roles);
		} else {
			throw new UsernameNotFoundException("User not found.");
		}
		return builder.build();
	}
	
	@Transactional
	public User create(String username, String password, boolean isEnabled)
	{
		return this.userDetailsDao.create(username,password,isEnabled);
	}

}
