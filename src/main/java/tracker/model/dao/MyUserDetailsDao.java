package tracker.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import tracker.model.entities.User;
@Repository
public class MyUserDetailsDao extends CommonDao implements UserDetailsDao{
	
		@Autowired
		private PasswordEncoder passwordEncoder;

		@Override
		public User findUserByUsername(String username) {
			return this.getSession().get(User.class, username);
		}

		@Override
		public User create(String username, String password, boolean isEnabled) {
			User u = new User();
			u.setUsername(username);
			u.setPassword(password);
			u.setEnabled(isEnabled);
			this.getSession().save(u);

			return u;
		}

		@Override
		public User update(User user) {
			return (User) this.getSession().merge(user);
		}

		@Override
		public void delete(User user) {
			this.getSession().delete(user);
		}

		@Override
		public String encryptPassword(String password) {
			return this.passwordEncoder.encode(password);
		}
		
		@Override
		public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
			this.passwordEncoder = passwordEncoder;
		}

		@Override
		public PasswordEncoder getpasswordEncoder() {
			return this.passwordEncoder;
		}
}
	
