package maamissiniva.cps;

public class Example01 {
	
	public static void main(String[] args) {
		LazyCPS<Integer> one = LazyCPS.of(1);
		FC1<LazyCPS<Integer>,LazyCPS<Integer>> po   = CPS.closure(new LAddI(), one);
		LazyCPS<Integer>                       opo  = CPS.lazy(po, one);
		LazyCPS<Integer>                       two  = Once.once(opo);
		LazyCPS<Integer>                       four = Once.once(CPS.lazy(CPS.closure(new LAddI(),two),two));
		LazyCPS<Integer>                       oooo = CPS.lazy(CPS.closure(new LAddI(),opo),opo);
		
		Step prg0 = four.apply(new LPI());
		Step prg1 = oooo.apply(new LPI());
		// Visual clue that the once thing works.
		StepRunner.runTime(prg0);
		StepRunner.runTime(prg1);
	}
	
}
