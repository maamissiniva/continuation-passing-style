package maamissiniva.cps;

import maamissiniva.function.coc.F0;

/**
 * Basic step machine.
 * 
 * @author vrd
 *
 */
public class StepRunner {

	public static final F0<Step> END = () -> null;
	
	static void time(Runnable f, int count) {
		long start = System.currentTimeMillis();
		while (count-- > 0)
			f.run();
		long time = System.currentTimeMillis() - start;
		System.out.println("execution : " + time + "ms");
	}
	
	static int steps = 0;
	
	public static void runTime(Step c) {
		runTime(c,1);
	}
	public static void runTime(Step c, int count) {
		time(() -> {
			run(c);
			System.out.println("steps : " + steps);
		},count);
	}
	
	public static void run(Step c) {
		steps = 0;
		while (c != null) {
			c = c.execute();
			steps ++;
		}
	}
	
}
