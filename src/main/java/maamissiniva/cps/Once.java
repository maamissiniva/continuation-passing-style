package maamissiniva.cps;

import maamissiniva.function.coc.F1;
import maamissiniva.function.coc.F2;

/**
 * Lazy value that will be computed only once.
 * 
 * @author vrd
 *
 * @param <A>
 */
public class Once<A> implements LazyCPS<A> {

	interface Computation<A> extends F2<Once<A>, F1<A,Step>, Step> {
	}
	
	private Computation<A> c;
	private A value;
	
	@Override
	public Step apply(F1<A, Step> a) {
		if (c != null)
			return () -> c.apply(this,  a);
		return () -> a.apply(value);
	}
	
	/**
	 * Build a once value from a lazy value.
	 * @param <A> value type
	 * @param l   lazy value
	 * @return    once lazy value
	 */
	public static <A> Once<A> once(LazyCPS<A> l) {
		Once<A> once = new Once<>();
		once.c = (o,a) -> () ->
			l.apply(x -> {
				o.c = null;
				o.value = x;
				return a.apply(x);
			});
		return once;
	}
	
}
