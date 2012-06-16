package transaction.bankservice;

import java.math.BigDecimal;

public interface BalanceMutator {
	BigDecimal mutate(BigDecimal balance);
}
