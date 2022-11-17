package test.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

	@Autowired
	List<Account> allAccounts;

	@Autowired
	InMemoryUserDetailsManager userManager;

	@PostMapping
	public ResponseEntity<Boolean> save(@RequestBody Account account) {

		if(account != null) {

			if(userManager.userExists(account.getCustomerID())) {
				UserDetails user = userManager.loadUserByUsername(account.getCustomerID());
				if(user != null) {
					userManager.updatePassword(user, "new Password");
					userManager.updateUser(user);
					updateAccount(account);
				}
			} else {
				saveAccount(account);

				UserDetails newUser = User.withUsername(account.getCustomerID())
						.password(new BCryptPasswordEncoder().encode("adminPass"))
						.roles("ADMIN")
						.build();

				userManager.createUser(newUser);
				allAccounts.add(account);
			}
		}

		return new ResponseEntity<Boolean>(HttpStatus.OK);
	}

	@GetMapping()
	public List<Account> getAllAccounts() {
		return allAccounts; 
	}

	private void saveAccount(Account account){

		long number = System.currentTimeMillis();

		account.setBalance("balance" + number);
		account.setCurrentAccount(true);
		account.setName("name" + number);
		account.setSurname("surname" + number);

		if(account.getInitialCredit() != 0) {
			account.getTransactions().add("transaction" + number);
		}

		if(allAccounts != null && !allAccounts.isEmpty()) {
			allAccounts.forEach(ac -> {
				if(ac != null && ac.getCustomerID() != null && ac.getCustomerID().equals(account.getCustomerID())) {
					ac.setCurrentAccount(false);
				}
			});
		}	
	}

	private void updateAccount(Account account){

		allAccounts.forEach(ac-> {
			if(ac != null && ac.getCustomerID() != null && ac.getCustomerID().equals(account.getCustomerID())) {
				ac.setInitialCredit(account.getInitialCredit());
				if(ac.getInitialCredit() != 0) {
					ac.getTransactions().add("transaction" + System.currentTimeMillis());
				}
			}
		});	
	}
}