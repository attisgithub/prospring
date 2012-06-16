package transaction.nontrans;

import java.math.BigDecimal;

import transaction.bankservice.AccountIdentity;
import transaction.bankservice.BankService;
import transaction.bankservice.BankServiceSupport;

public class DefaultBankService extends BankServiceSupport implements
		BankService {

	@Override
	public void transfer(AccountIdentity from, AccountIdentity to,
			BigDecimal amount) {
		doTransfer(from, to, amount);
	}

	@Override
	public BigDecimal getBalance(AccountIdentity identity) {
		return doGetBalance(identity);
	}

}
