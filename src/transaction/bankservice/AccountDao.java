package transaction.bankservice;

public interface AccountDao {
	void save(Account account);
	Account getByIdentity(AccountIdentity accountIdentity);
}
