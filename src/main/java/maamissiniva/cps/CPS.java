package maamissiniva.cps;

import maamissiniva.function.coc.F1;

class LAddI implements FC2<LazyCPS<Integer>,LazyCPS<Integer>,LazyCPS<Integer>> { 

	@Override
	public Step apply(LazyCPS<Integer> a, F1<FC1<LazyCPS<Integer>, LazyCPS<Integer>>, Step> b) {
		return () -> b.apply(new LAddI_1(a));
	}
	
}

class LAddI_1 implements FC1<LazyCPS<Integer>, LazyCPS<Integer>> {
	
	LazyCPS<Integer> a;
	
	public LAddI_1(LazyCPS<Integer> a) {
		this.a = a;
	}

	@Override
	public Step apply(LazyCPS<Integer> b, F1<LazyCPS<Integer>, Step> c) {
		return () -> c.apply(new LAddI_2(a,b));
	}
	
}

class LAddI_2 implements LazyCPS<Integer> {
	LazyCPS<Integer> a;
	LazyCPS<Integer> b;
	public LAddI_2(LazyCPS<Integer> a, LazyCPS<Integer> b) {
		this.a = a;
		this.b = b;
	}
	@Override
	public Step apply(F1<Integer, Step> c) {
		return new LAddI_3(a,b,c);
	}

}

class LAddI_3 implements Step {
	LazyCPS<Integer> a;
	LazyCPS<Integer> b;
	F1<Integer, Step> c;
	// locals
	Integer sa;
	Integer sb;
	public LAddI_3(LazyCPS<Integer> a, LazyCPS<Integer> b, F1<Integer, Step> c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
	@Override
	public Step execute() {
		return () -> a.apply(this::s0);
	}

	Step s0(Integer sa) {
		this.sa = sa;
		return () -> b.apply(this::s1);
	}
	
	Step s1(Integer sb) {
		this.sb = sb;
		return () -> {
			System.out.println("plus " + sa + " " + sb);
			return c.apply(sa+sb);
		};
	}
	
}

class Identity {
	
	// This is a function so we need it to be evaluated once and to evaluate
	// the parameter once. As we have built the once construction 
	static FC1<LazyCPS<Integer>,LazyCPS<Integer>> idInteger =
			(i,c) -> {
				throw new RuntimeException();
			};
	
}


class LPI implements F1<Integer,Step> {

	@Override
	public Step apply(Integer a) {
		System.out.println(a);
		return () -> null;
	}
	
}

class LPrintInteger implements F1<LazyCPS<Integer>,Step> {

	@Override
	public Step apply(LazyCPS<Integer> a) {
		return new LPrintInteger_1(a);
	}
	
}

class LPrintInteger_1 implements Step {
	private LazyCPS<Integer> a;
	public LPrintInteger_1(LazyCPS<Integer> a) {
		this.a = a;
	}
	@Override
	public Step execute() {
		return () -> a.apply(this::s0);
	}
	Step s0(Integer sa) {
		System.out.println(sa);
		return () -> null;
	}
}

public class CPS {
	static <A> LazyCPS<A> promote(F1<F1<A,Step>,Step> f) {
		return x -> f.apply(x);
	}
	
	// interface FC1<A,B> extends F2<A,F1<B,Continuation>,Continuation>
	static <A,B,C> FC1<B,C> closure(FC1<A,FC1<B,C>> f, A a) {
		return (B b, F1<C,Step> c) -> { 
			F1<FC1<B,C>,Step> o = x -> () -> x.apply(b,c);	
			return () -> f.apply(a,o);
		};
	}
	
	static <A,B> LazyCPS<B> lazy(FC1<A,LazyCPS<B>> f, A a) {
		return (F1<B,Step> c) -> {
			F1<LazyCPS<B>,Step> o = lb -> lb.apply(c);
			return f.apply(a, o);
		};
	}
	
	public static void main(String[] args) {
		// This is a LazyInteger but the typing is off. This is a general thing as LazyCPS
		// not a type alias so we cannot come back from the explicit type which is the
		// proper typing. The type signature properly explodes if we do not use compact
		// signatures.
		LazyCPS<Integer> one = LazyCPS.of(1);
		FC1<LazyCPS<Integer>,LazyCPS<Integer>> po =	closure(new LAddI(), one);
		LazyCPS<Integer> opo = lazy(po, one);
		LazyCPS<Integer> two = Once.once(opo);
		LazyCPS<Integer> four = Once.once(lazy(closure(new LAddI(),two),two));
//		Continuation prg = opo.apply(new LPI());
		Step prg = four.apply(new LPI());
//				// (LazyCPS<Integer>i, F1<LazyCPS<Integer>,Continuation> c) -> 
//		        //new LAddI()::apply;
//				(LazyCPS<Integer> i, F1<LazyCPS<Integer>,Continuation> c) 
//				-> () 
//				-> new LAddI().apply(i, c);
//		LazyCPS<Integer> opo = 
//				new LAddI()
//				.apply(one);
		
//		Continuation prg = 
//				new LAddI().apply(LazyInteger.of(1)).apply(LazyInteger.of(1)).apply(new LPrintInteger());
//		Continuation prg = 
//				new LAddI().apply(opo).apply(opo).apply(new LPrintInteger());
		StepRunner.runTime(prg);
	}
	
}
