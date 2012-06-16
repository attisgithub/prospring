package taskscheduling.timer;

import java.util.Timer;

/**
 * Fixed Delay.The delay refers to the last execution time.
 * 
 * @author Attis
 *
 */
public class FixedDelayScheduling {

	public static void main(String[] args) {
		Timer t = new Timer();
		t.schedule(new HelloWorldTask(), 1000, 3000);
	}

}
