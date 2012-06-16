package transaction.programmatic;

import java.math.BigDecimal;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import transaction.bankservice.AccountIdentity;
import transaction.bankservice.BankService;
import transaction.bankservice.BankServiceSupport;

public class ProgrammaticTxBankService extends BankServiceSupport implements
		BankService {

	private PlatformTransactionManager transactionManager;

	@Override
	public void transfer(AccountIdentity from, AccountIdentity to,
			BigDecimal balance) throws Throwable {
		TransactionDefinition transactionDefinition = new DefaultTransactionDefinition(
				TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus transactionStatus = this.transactionManager
				.getTransaction(transactionDefinition);
		try {
			doTransfer(from, to, balance);
			this.transactionManager.commit(transactionStatus);
		} catch (Throwable t) {
			this.transactionManager.rollback(transactionStatus);
			throw t;
		}

	}

	@Override
	public BigDecimal getBalance(AccountIdentity identity) {
		return doGetBalance(identity);
	}

	public void setTransactionManager(
			PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

}
