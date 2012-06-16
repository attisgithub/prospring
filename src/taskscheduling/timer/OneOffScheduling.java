package taskscheduling.timer;

import java.util.Timer;

/**
 * one off scheduling
 * 
 * @author Attis
 *
 */
public class OneOffScheduling {

	public static void main(String[] args) {
		Timer t = new Timer();
		t.schedule(new HelloWorldTask(), 1000);
	}

}
