package transaction.bankservice;

import java.math.BigDecimal;

import org.springframework.util.Assert;

public abstract class BankServiceSupport{

	private AccountDao accountDao;
	
	protected abstract static class BalanceMutatorSupport implements BalanceMutator{
		private BigDecimal amount;

		public BalanceMutatorSupport(BigDecimal amount) {
			Assert.notNull(amount, "The 'amount' argument must not be null.");
			this.amount = amount;
		}
		
		protected final BigDecimal getAmount(){
			return this.amount;
		}
		
		protected abstract BigDecimal doMutate(BigDecimal balance);
		
		@Override
		public final BigDecimal mutate(BigDecimal balance){
			return doMutate(balance);
		}
		
	}
	
	protected static class CreditBalanceMutator extends BalanceMutatorSupport{		
		
		public CreditBalanceMutator(BigDecimal amount) {
			super(amount);
		}

		@Override
		protected BigDecimal doMutate(BigDecimal balance) {
			return balance.add(getAmount());
		}
		
	}
	
	protected static class NoOverdraftDebitBalanceMutator extends BalanceMutatorSupport{

		public NoOverdraftDebitBalanceMutator(BigDecimal amount) {
			super(amount);
		}

		@Override
		protected BigDecimal doMutate(BigDecimal balance) {
			BigDecimal result = balance.subtract(getAmount());
			if(result.compareTo(new BigDecimal(0))<0){
				throw new IllegalArgumentException("Account could not be negative!");
			}
			return result;
		}
		
	}
	
	public void doTransfer(AccountIdentity from, AccountIdentity to,
			BigDecimal amount) {
		Account fromAccount =  this.accountDao.getByIdentity(from);
		if(fromAccount==null) throw new IllegalArgumentException("Account could not be null!");
		Account toAccount = this.accountDao.getByIdentity(to);
		if(toAccount==null) throw new IllegalArgumentException("Account could not be null!");
		
		fromAccount.changeBalance(new NoOverdraftDebitBalanceMutator(amount));
		toAccount.changeBalance(new CreditBalanceMutator(amount));
		
		this.accountDao.save(fromAccount);
		this.accountDao.save(toAccount);
	}

	public BigDecimal doGetBalance(AccountIdentity identity) {
		Account account = this.accountDao.getByIdentity(identity);
		if(account==null) throw new IllegalArgumentException("Account could not be null!");
		return account.getBalance();
	}

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

}
