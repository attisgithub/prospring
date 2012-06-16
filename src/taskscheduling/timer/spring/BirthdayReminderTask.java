package taskscheduling.timer.spring;

import java.util.TimerTask;

public class BirthdayReminderTask extends TimerTask {

	private String who;

	public void setWho(String who) {
		this.who = who;
	}

	@Override
	public void run() {
		System.out.println("Don't forget it is " + who + "'s birthday in 7days");
	}

}
