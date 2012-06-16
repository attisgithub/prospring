package transaction.bankservice;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

public class JdbcAccountDao extends SimpleJdbcDaoSupport implements AccountDao {

	private static final String INSERT_SQL = "insert into t_account (id, sort_code, number, balance) values (?, ?, ?, ?)";
	private static final String UPDATE_SQL = "update t_account set balance=? where id=?";
	private static final String SELECT_SQL = "select id, sort_code, number, balance from t_account where sort_code=? and number=?";

	@Override
	public void save(Account account) {
		Grinch.ruin();
		if (account.getId() == null) {
			getSimpleJdbcTemplate().update(INSERT_SQL, account.getId(),
					account.getIdentity().getSortCode(),
					account.getIdentity().getNumber(), account.getBalance());
		}else{
			getSimpleJdbcTemplate().update(UPDATE_SQL, account.getBalance(),account.getId());
		}

	}

	@Override
	public Account getByIdentity(AccountIdentity accountIdentity) {
		return getSimpleJdbcTemplate().queryForObject(SELECT_SQL, new ParameterizedRowMapper<Account>() {

			@Override
			public Account mapRow(ResultSet rs, int rowNumber) throws SQLException {
				Account account = new Account();
				account.setId(rs.getLong(1));
				AccountIdentity identity = new AccountIdentity(rs.getString(2), rs.getString(3));
				account.setIdentity(identity);
				account.setBalance(rs.getBigDecimal(4));
				return account;
			}
			
			
		}, accountIdentity.getSortCode(),accountIdentity.getNumber());
	}

}
