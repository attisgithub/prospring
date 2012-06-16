package transaction.bankservice;

import org.springframework.util.Assert;

public class AccountIdentity{
	
	private String sortCode;
	private String number;
	
	public AccountIdentity() {

	}

	public AccountIdentity(String sortCode, String number) {
		Assert.notNull(sortCode, "The 'sortCode' argument must not be null.");
		Assert.notNull(number, "The 'number' argument must not be null.");
		this.sortCode = sortCode;
		this.number = number;
	}

	public String getSortCode() {
		return sortCode;
	}

	public void setSortCode(String sortCode) {
		this.sortCode = sortCode;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	
	
	
}
