package test.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ProjectConfiguration {

	@Bean
	public List<Account> getAllAccounts() {
		final List<Account> allAccounts = new ArrayList<>();
		return allAccounts;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeHttpRequests((requests) -> requests
				.antMatchers("/")
				.permitAll())
		.formLogin((form) -> form
				.loginPage("/login")
				.permitAll()
				)
		.logout((logout) -> logout
				.permitAll()
				.deleteCookies("JSESSIONID"));

		//		.anyRequest().authenticated()

		return http.build();
	}

	//	public void addViewControllers(ViewControllerRegistry registry) {
	//		registry.addViewController("/home").setViewName("home");
	//		registry.addViewController("/login").setViewName("login");
	//	}

	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		UserDetails user = User.withUsername("user")
				.password(passwordEncoder().encode("user1Pass"))
				.roles("USER")
				.build();

		UserDetails admin = User.withUsername("admin")
				.password(passwordEncoder().encode("adminPass"))
				.roles("ADMIN")
				.build();

		return new InMemoryUserDetailsManager(user, admin);
	}

	@Bean 
	public PasswordEncoder passwordEncoder() { 
		return new BCryptPasswordEncoder(); 
	}
}