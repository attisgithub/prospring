package transaction.bankservice;

import java.math.BigDecimal;

public class Account {

	private Long id;
	private AccountIdentity identity;
	private BigDecimal balance;
	
	public void changeBalance(BalanceMutator mutator){
		this.balance = mutator.mutate(this.balance);
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public AccountIdentity getIdentity() {
		return identity;
	}
	public void setIdentity(AccountIdentity identity) {
		this.identity = identity;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	
}
