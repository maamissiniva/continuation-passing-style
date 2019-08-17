package maamissiniva.cps;

/**
 * Lazy thing but not a once thing.
 */
public interface LazyCPS<A> extends FC0<A> {	
	
	/**
	 * Build a lazy value from a regular value.
	 * @param <A> value type
	 * @param a   value
	 * @return    lazy value
	 */
	static <A> LazyCPS<A> of(A a) {
		return c -> () -> c.apply(a);
	}

}
