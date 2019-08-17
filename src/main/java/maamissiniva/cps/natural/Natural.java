//package maamissiniva.cps.natural;
//
//import maamissiniva.function.coc.C1;
//import maamissiniva.function.coc.C2;
//import maamissiniva.function.coc.CC1;
//import maamissiniva.function.coc.CC2;
//
////
//// Function conversion is from F1<A,B> to C2<A,C1<B>>
//// Looks like the proper thing. This breaks early as we cannot encode
////   f x = x + 1
//// If we encode 1 as a CC of 1.
////
//// Conversion looked nice in the first place but is probably not
//// the correct one that leans toward CCs.
//interface FC0<A>     extends CC1<A>           {}
//interface FC1<A,B>   extends C2<CC1<A>,CC1<B>>     {}
////interface FC2<A,B,C> extends FC1<A,FC1<B,C>> {}
////
////class AddI implements FC2<Integer,Integer,Integer> {
////
////	@Override
////	public void accept(Integer a, C1<FC1<Integer, Integer>> b) throws Exception {
////		FC1<Integer, Integer> c = (x,y) -> y.accept(a + x);
////		b.accept(c);
////	}
////	
////}
////
////class Println<A> implements FC1<A,Void> {
////
////	@Override
////	public void accept(A a, C1<Void> b) throws Exception {
////		System.out.println(a);
////		b.accept(null);
////	}
////	
////}
////
////class End<A> implements FC1<A,Void> {
////
////	@Override
////	public void accept(A a, C1<Void> b) throws Exception {
////	}
////	
////}
//
//// What's a function that returns 1 ? We would assume that it's (void -> 1). 
//
//public class Natural {
//
//	static <A,B,C> FC1<A,C> chain(FC1<A,B> f, FC1<B,C> g) {
//		return (a,cc) -> f.accept(a, b -> g.accept(b, cc));
//	}
//	
//	// What is 1+1 really ? Really, what's 2 ? 2 is not a consumer, it's
//	// a consumer of consumer.
//	static <A,B> C1<B> apply(C2<A,C1<B>> f, A x) {
//		C1<B> y = null;
//		return b -> f.accept(x, y);
//	}
//	
//	
//	public static void ex00() {
//		// 1 is a supplier of 1 but do we have 1 anymore ?
//		CC1<Integer> one = c -> c.accept(1);
//		C2<Integer,CC1<Integer>> plusOne = (i,c) -> c.accept(i+1);
////		FC1<Integer,Integer> 
//		
////		chain(new AddI().accept(1, b););
////		FC1<Integer,Integer> 
////		.accept(1,  new Println
////		f.apply(1, (s,x) -> c.accept(i -> System.out.println(i))));
//	}
//	
//}
