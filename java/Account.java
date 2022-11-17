package test.test;

import java.util.ArrayList;
import java.util.List;

public class Account {

	private String name;
	private String surname;
	private String balance;
	private String customerID;
	private long initialCredit;
	private boolean isCurrentAccount;
	private List<String> transactions = new ArrayList<>();

	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public long getInitialCredit() {
		return initialCredit;
	}
	public void setInitialCredit(long initialCredit) {
		this.initialCredit = initialCredit;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public boolean isCurrentAccount() {
		return isCurrentAccount;
	}
	public void setCurrentAccount(boolean isCurrentAccount) {
		this.isCurrentAccount = isCurrentAccount;
	}
	public List<String> getTransactions() {
		return transactions;
	}
}