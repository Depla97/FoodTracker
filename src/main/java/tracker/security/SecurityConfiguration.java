package tracker.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import tracker.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
@ComponentScan(basePackages = "tracker")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new MyUserDetailsService();
	}

	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService()).passwordEncoder(this.passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests().antMatchers("/login").permitAll();
		http.authorizeRequests().antMatchers("/register").permitAll();
		http.authorizeRequests().antMatchers("/registerUser").permitAll();//Url di post per registrare un nuovo utente
		http.authorizeRequests().antMatchers("/**").hasAnyRole("USER");

		http.formLogin().loginPage("/login");
		http.formLogin().defaultSuccessUrl("/");
		http.formLogin().failureUrl("/login?error=true");
		http.formLogin().permitAll();
	
		http.logout().logoutSuccessUrl("/login");
		http.logout().invalidateHttpSession(true);
		http.logout().permitAll();
	
		http.csrf().disable();
	}
	
	@Override
	//Ignora i link ai css per valutare i permessi di accesso, cos?? non blocca l'accesso ai link stessi
	//e non reindirizza alla pagina css dopo login
	public void configure(WebSecurity web){
	    web.ignoring().antMatchers("/css/**");
	}
	
}
