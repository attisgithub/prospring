package transaction.programmatic;

import java.math.BigDecimal;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import transaction.bankservice.AccountIdentity;
import transaction.bankservice.BankService;
import transaction.bankservice.BankServiceSupport;

public class ProgrammaticTxBankService2 extends BankServiceSupport implements
		BankService {

	private TransactionTemplate transactionTemplate;

	@Override
	public void transfer(final AccountIdentity from, final AccountIdentity to,
			final BigDecimal balance) throws Throwable {
		this.transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			
			@Override
			protected void doInTransactionWithoutResult(
					TransactionStatus paramTransactionStatus) {
				doTransfer(from, to, balance);
			}
		});
	}

	@Override
	public BigDecimal getBalance(AccountIdentity identity) {
		return doGetBalance(identity);
	}

	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionTemplate = new TransactionTemplate(transactionManager);
	}

}
