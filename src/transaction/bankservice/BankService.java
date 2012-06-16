package transaction.bankservice;

import java.math.BigDecimal;

public interface BankService {
	void transfer(AccountIdentity from, AccountIdentity to, BigDecimal balance) throws Throwable;
	BigDecimal getBalance(AccountIdentity identity);
}
