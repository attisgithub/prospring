package transaction.declare;

import java.math.BigDecimal;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import transaction.bankservice.AccountIdentity;
import transaction.bankservice.BankService;

public class Main {

	public static void main(String[] args) {
		ApplicationContext actx = new ClassPathXmlApplicationContext(
				new String[] {
						"classpath:transaction/bankservice/dao-context.xml",
						"classpath:transaction/declare/context-dtx.xml" });
		
		BankService bankService = (BankService) actx.getBean("bankService");
		final AccountIdentity a1 = new AccountIdentity("011001", "12345678");
		final AccountIdentity a2 = new AccountIdentity("011001", "10203040");
		
		System.out.println("Before");
		System.out.println(a1 + ": " + bankService.getBalance(a1));
		System.out.println(a2 + ": " + bankService.getBalance(a2));
		
		try {
			bankService.transfer(a1, a2, new BigDecimal(200.00));
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		System.out.println("After");
		System.out.println(a1 + ": " + bankService.getBalance(a1));
		System.out.println(a2 + ": " + bankService.getBalance(a2));
		
	}

}
