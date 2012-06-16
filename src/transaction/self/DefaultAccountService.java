package transaction.self;

import java.math.BigDecimal;
import java.util.Random;

import transaction.bankservice.Account;
import transaction.bankservice.AccountDao;
import transaction.bankservice.AccountIdentity;
import transaction.bankservice.Grinch;

public class DefaultAccountService implements AccountService {

	private AccountDao accountDao;
	private WorkerFactory workerFactory;

	@Override
	public AccountIdentity create() {

		Random random = new Random();
		StringBuilder number = new StringBuilder(8);
		for (int i = 0; i < 8; i++) {
			number.append(random.nextInt(9));
		}

		AccountIdentity ai = new AccountIdentity("011001", number.toString());
		Account account = new Account();
		account.setId(System.currentTimeMillis());
		account.setIdentity(ai);
		account.setBalance(BigDecimal.ZERO);

		Worker worker = this.workerFactory.create();
		worker.work(10);
		this.accountDao.save(account);
		worker.work(20);
		Grinch.ruin();

		return null;
	}

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public void setWorkerFactory(WorkerFactory workerFactory) {
		this.workerFactory = workerFactory;
	}

}
