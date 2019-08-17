package maamissiniva.cps;

/**
 * This is a step which returns the next step when executed so that
 * the execution runner uses the heap instead of the stack.
 */
public interface Step {
	
	Step execute();
	
}
