package transaction.aop;

import java.math.BigDecimal;

import org.springframework.transaction.annotation.Transactional;

import transaction.bankservice.AccountIdentity;
import transaction.bankservice.BankService;
import transaction.bankservice.BankServiceSupport;

public class DeclarativeTxBankService extends BankServiceSupport implements
		BankService {

	@Transactional
	@Override
	public void transfer(AccountIdentity from, AccountIdentity to,
			BigDecimal balance) throws Throwable {
		doTransfer(from, to, balance);
	}

	@Transactional
	@Override
	public BigDecimal getBalance(AccountIdentity identity) {
		return doGetBalance(identity);
	}

}
